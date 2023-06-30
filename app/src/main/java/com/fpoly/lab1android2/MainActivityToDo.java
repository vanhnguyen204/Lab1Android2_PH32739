package com.fpoly.lab1android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityToDo extends AppCompatActivity {
    ArrayList<ToDo> list;
    RecyclerView recyclerView;
    int position = TodoAdapter.KEY_POSITION;
    TodoAdapter todoAdapter;
    DbHelper helper;
    EditText txtTitle;
    EditText txtContent;
    EditText txtDate;
    EditText txtType;
    Button btnAdd, btnUpdate, btnDelete, btnClear;

    public void setTextEdt(int position) {
        txtTitle.setText(list.get(position).getTitle());
        txtContent.setText(list.get(position).getConten());
        txtDate.setText(list.get(position).getDate());
        txtType.setText(list.get(position).getType());
    }

    public void finId() {
        recyclerView = findViewById(R.id.recyclerview);
        txtContent = findViewById(R.id.edt_content);
        txtTitle = findViewById(R.id.edt_title);
        txtDate = findViewById(R.id.edt_date);
        txtType = findViewById(R.id.edt_type);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnClear = findViewById(R.id.btn_clear);
    }

    public void setClickButton() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitle.setText("");
                txtContent.setText("");
                txtDate.setText("");
                txtType.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoDAO toDoDAO = new ToDoDAO(MainActivityToDo.this, helper);
                String getTitle = txtTitle.getText().toString().trim();
                String getContent = txtContent.getText().toString().trim();
                String getDate = txtDate.getText().toString().trim();
                String getType = txtType.getText().toString().trim();
                int getStatus;
                if (getType.equals("Dễ")) {
                    getStatus = 0;
                } else {
                    getStatus = 1;
                }
               if (checkValuesSame()){

               }else {
                   ToDo toDo = new ToDo(getTitle, getContent, getDate, getType, getStatus);
                   toDoDAO.insertValues(toDo);
                   setAdapterTodo();
               }

            }
        });
    }
    boolean checkValuesSame(){
        for (int i = 0; i< list.size();i++){
            if (list.get(i).getTitle().equals(txtTitle.getText().toString().trim())){
                Toast.makeText(this, "Title không được trùng nhau !", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return  false;
    }

    public void updateTodo() {


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoDAO toDoDAO = new ToDoDAO(MainActivityToDo.this, helper);
                ToDo toDoOld = list.get(position);

                String getTitle = txtTitle.getText().toString().trim();
                String getContent = txtContent.getText().toString().trim();
                String getDate = txtDate.getText().toString().trim();
                String getType = txtType.getText().toString().trim();
                int getStatus;
                if (getType.equals("Dễ")) {
                    getStatus = 0;
                } else {
                    getStatus = 1;
                }

                ToDo toDo = new ToDo(getTitle, getContent, getDate, getType, getStatus);
                toDoDAO.updateTodo(toDo);
               setAdapterTodo();
            }
        });

    }

    public void deleteTodo() {
        ToDoDAO toDoDAO = new ToDoDAO(MainActivityToDo.this, helper);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDo toDo = list.get(position);
                toDoDAO.deleteTodo(toDo);
                setAdapterTodo();

            }
        });
    }

    void setAdapterTodo() {
        ToDoDAO toDoDAO = new ToDoDAO(MainActivityToDo.this, helper);
        list = toDoDAO.getDS();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivityToDo.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        todoAdapter = new TodoAdapter(MainActivityToDo.this, list);
        recyclerView.setAdapter(todoAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finId();
        helper = new DbHelper(MainActivityToDo.this);
        ToDoDAO toDoDAO = new ToDoDAO(MainActivityToDo.this, helper);
        ToDo toDo = new ToDo("Học C#", "Học Java cơ bản", "28/8/2023", "Dễ", 1);
        ToDo toDo2 = new ToDo("Học C++", "Học C++ cơ bản", "21/2/2023", "Khó", 0);
        ToDo toDo3 = new ToDo("Học Python", "Học Python cơ bản", "22/1/2023", "Dễ", 1);

        setAdapterTodo();

        if (list.size() > 0) {
            ToDo td = list.get(0);
            txtTitle.setText(td.getTitle());
            txtContent.setText(td.getConten());
            txtDate.setText(td.getDate());
            txtType.setText(td.getType());
        }
        setClickButton();
        updateTodo();
        deleteTodo();
    }
}
package com.fpoly.lab1android2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    Activity atv;
    ArrayList<ToDo> list;
    View view;
    public static int KEY_POSITION;

    public TodoAdapter(Activity atv, ArrayList<ToDo> list) {
        this.atv = atv;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = atv.getLayoutInflater();
        view = inflater.inflate(R.layout.edit_recyclerview, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(Color.GREEN);

            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ToDo toDo = list.get(position);
        holder.txtTitle.setText("Title: " + toDo.getTitle());
        holder.txtContent.setText("Content: " + toDo.getConten());
        holder.txtDate.setText("Date: " + toDo.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivityToDo) atv).setTextEdt(position);
                KEY_POSITION = position;
                Toast.makeText(atv, "" + KEY_POSITION, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtContent;
        TextView txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_title);
            txtContent = itemView.findViewById(R.id.txt_content);
            txtDate = itemView.findViewById(R.id.txt_date);

        }

    }
}

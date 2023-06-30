package com.fpoly.lab1android2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoDAO {
    Context context;
    DbHelper dbHelper;

    public ToDoDAO(Context context, DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public void insertValues(ToDo toDo) {
        SQLiteDatabase sql = dbHelper.getReadableDatabase();
        ContentValues pair = new ContentValues();
        pair.put("Title", toDo.getTitle());
        pair.put("Content", toDo.getConten());
        pair.put("Date", toDo.getDate());
        pair.put("Type", toDo.getType());
        pair.put("Status", toDo.getStatus());
        // giá trị cột phải Y XỲ như khi khai báo ban đầu
        long kq = sql.insert("ToDo", null, pair);
        if (kq > 0)
            Toast.makeText(context, "Thành công!",
                    Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Thất bại!",
                Toast.LENGTH_SHORT).show();
    }

    public void updateTodo(ToDo toDo) {
        SQLiteDatabase sql = dbHelper.getReadableDatabase();
        ContentValues pair = new ContentValues();

        pair.put("Title", toDo.getTitle());
        pair.put("Content", toDo.getConten());
        pair.put("Date", toDo.getDate());
        pair.put("Type", toDo.getType());
        pair.put("Status", toDo.getStatus());
        // giá trị cột phải Y XỲ như khi khai báo ban đầu
        long kq = sql.update("ToDo", pair, "Title = ?", new String[]{toDo.getTitle()});
        if (kq > 0)
            Toast.makeText(context, "Thành công!",
                    Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Thất bại! ",
                Toast.LENGTH_SHORT).show();
    }

    public void deleteTodo(ToDo sp) {
        SQLiteDatabase sql = dbHelper.getReadableDatabase();

        long kq = sql.delete("ToDo", "Title = ?", new String[]{sp.getTitle()});

        if (kq > 0)
            Toast.makeText(context, "Thành công!",
                    Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Thất bại!",
                Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ToDo> getDS() {
        ArrayList<ToDo> list = new ArrayList<>();
        SQLiteDatabase sql = dbHelper.getReadableDatabase();
        sql.beginTransaction();
        try {
            Cursor cursor = sql.rawQuery("SELECT * FROM ToDo", null);
            // cursor dùng để chứa kết quả truy vấn từ DB, tự động co giãn độ dài
            // tham số thứ 2 để định nghĩa danh sách cột mình muốn truy vấn
            // lấy tất cả giá trị thì để null

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {
                    int id = cursor.getInt(0);
                    String getTitle = cursor.getString(1);
                    String getContent = cursor.getString(2);
                    String getDate = cursor.getString(3);
                    String getType = cursor.getString(4);
                    int getStatus = cursor.getInt(5);
                    list.add(new ToDo(id, getTitle, getContent, getDate, getType, getStatus));
                } while (cursor.moveToNext());
                // moveToNext: chuyển đến vị trí tiếp theo
                sql.setTransactionSuccessful();
            }else{
                Toast.makeText(context, "Không có gì !", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

        } finally {
            sql.endTransaction();
        }


        return list;
    }

}

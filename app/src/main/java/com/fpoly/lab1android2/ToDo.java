package com.fpoly.lab1android2;

public class ToDo {
    private int ID;
    private String title;
    private String conten;
    private String date;
    private String type;
    private int status;

    public ToDo(String title, String conten, String date, String type, int status) {
        this.title = title;
        this.conten = conten;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public ToDo(int ID, String title, String conten, String date, String type, int status) {
        this.ID = ID;
        this.title = title;
        this.conten = conten;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public ToDo(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

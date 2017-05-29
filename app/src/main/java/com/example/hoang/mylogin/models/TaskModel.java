package com.example.hoang.mylogin.models;

/**
 * Created by Hoang on 5/29/2017.
 */

public class TaskModel {
    private String name;
    private boolean done;
    private float payment_per_hour;
    private String due_date;
    private String local_id;
    private String color;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public float getPayment_per_hour() {
        return payment_per_hour;
    }

    public void setPayment_per_hour(float payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

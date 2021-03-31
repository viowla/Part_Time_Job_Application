package com.example.parttimejobapplication.vacancy;

import com.google.gson.annotations.SerializedName;

public class Vacancy {
    private int id;
    @SerializedName("title")
    private String name;
    @SerializedName("description")
    private String text;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getText() {
        return text;
    }
}

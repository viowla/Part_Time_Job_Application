package com.example.parttimejobapplication.vacancy;

import com.google.gson.annotations.SerializedName;

public class Vacancy {
    private int id;
    private String name;
    private String companyName;
    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getText() {
        return text;
    }
}

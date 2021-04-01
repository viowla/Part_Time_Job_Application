package com.example.parttimejobapplication.company;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}

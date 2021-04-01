package com.example.parttimejobapplication.profile;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("citizenship")
    private String citizenship;

    public int getUserId() {
        return userId;
    }

    public String getCitizenship() {
        return citizenship;
    }
}

package com.example.parttimejobapplication;

import com.example.parttimejobapplication.profile.Profile;
import com.example.parttimejobapplication.vacancy.Vacancy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("vacancy")
    Call<List<Vacancy>> getVacancies();
    @GET("profile")
    Call<List<Profile>> getProfiles();
}

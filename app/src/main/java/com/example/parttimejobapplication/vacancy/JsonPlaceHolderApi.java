package com.example.parttimejobapplication.vacancy;

import com.example.parttimejobapplication.vacancy.Vacancy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("vacancy")
    Call<List<Vacancy>> getVacancies();
}

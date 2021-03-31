package com.example.parttimejobapplication.vacancy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parttimejobapplication.JsonPlaceHolderApi;
import com.example.parttimejobapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VacanciesListActivity extends AppCompatActivity {

    TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancies_list);

        textViewResults = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Vacancy>> call = jsonPlaceHolderApi.getVacancies();

        call.enqueue(new Callback<List<Vacancy>>() {
            @Override
            public void onResponse(Call<List<Vacancy>> call, Response<List<Vacancy>> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code: "+response.code());
                    return;
                }

                List<Vacancy> vacancies = response.body();

                for (Vacancy vacancy:vacancies){
                    String content ="";
                    content+="Company: "+vacancy.getCompanyName()+"\n";
                    content+="Vacancy: "+vacancy.getName()+"\n";
                    content+="Description: "+vacancy.getText()+"\n";

                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Vacancy>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });

    }




}

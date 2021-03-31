package com.example.parttimejobapplication.company;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parttimejobapplication.JsonPlaceHolderApi;
import com.example.parttimejobapplication.R;
import com.example.parttimejobapplication.vacancy.Vacancy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompanyViewActivity extends AppCompatActivity {

    TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        textViewResults = findViewById(R.id.company_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Company>> call = jsonPlaceHolderApi.getCompanies();

        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code: "+response.code());
                    return;
                }

                List<Company> vacancies = response.body();

                for (Company vacancy:vacancies){
                    String content ="";
                    content+="Vacancy: "+vacancy.getName()+"\n";
                    content+="Description: "+vacancy.getAddress()+"\n";
                    content+="Description: "+vacancy.getEmail()+"\n";

                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });

    }
}

package com.example.parttimejobapplication.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parttimejobapplication.R;
import com.example.parttimejobapplication.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileListActivity extends AppCompatActivity {

    TextView profileViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancies_list);

        profileViewResults = findViewById(R.id.profile_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Profile>> call = jsonPlaceHolderApi.getProfiles();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if(!response.isSuccessful()){
                    profileViewResults.setText("Code: "+response.code());
                    return;
                }

                List<Profile> profiles = response.body();

                for (Profile profile:profiles){
                    String content ="";
                    content+="Company: "+profile.getUserId()+"\n";
                    content+="Vacancy: "+profile.getCitizenship()+"\n";

                    profileViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                profileViewResults.setText(t.getMessage());
            }
        });

    }
}

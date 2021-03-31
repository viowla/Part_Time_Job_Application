package com.example.parttimejobapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parttimejobapplication.vacancy.JsonPlaceHolderApi;
import com.example.parttimejobapplication.vacancy.Vacancy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstPageActivity extends AppCompatActivity {

   /* ListView listView;
    String[] vacancy_name={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};
    String[] vacancy_pay={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};
    String[] vacancy_comp={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};*/

    TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

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


    /*class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] vac_name;
        String[] vac_pay;
        String[] vac_comp;

        public MyAdapter(Context c, String[] vac_name, String[] vac_pay, String[] vac_comp){
            super(c, R.layout.vacancies_row,R.id.vacancy_name, vac_name);
            this.context=c;
            this.vac_name=vac_name;
            this.vac_pay=vac_pay;
            this.vac_comp=vac_comp;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.vacancies_row,parent,false);
            TextView vac_name = row.findViewById(R.id.vacancy_name);
            TextView vac_pay = row.findViewById(R.id.vacancy_pay);
            TextView vac_comp = row.findViewById(R.id.vacancy_company);


            vac_name.setText(vacancy_name[position]);
            vac_pay.setText(vacancy_pay[position]);
            vac_comp.setText(vacancy_comp[position]);

            return row;
        }
    }*/
}
package com.example.parttimejobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompanyRegistrationActivity extends AppCompatActivity {

    Button registration_complete;

    final String url_Register= "add/company/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registrion);
        registration_complete = findViewById(R.id.regis_company);

        registration_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompanyRegistrationActivity.this, FirstPageActivity.class));
            }
        });
    }

    public class RegisterUser extends AsyncTask<String,Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String Name= strings[0];
            String Email= strings[1];
            String Password=strings[2];
            String finalurl=url_Register+"?user_name="+Name+
                    "&user_id="+Email+
                    "&user_password="+Password;

            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalurl)
                    .get()
                    .build();


            //checking server response and inserting data

            Response response= null;

            try {
                response= okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result= response.body().string();
                    showToast(result);

                    if(result.equalsIgnoreCase("User registered successfully")){
                        showToast("Register Successfully Please Login");
                        Intent i= new Intent(CompanyRegistrationActivity.this, FirstPageActivity.class);
                        startActivity(i);
                        finish();
                    }

                    else if (result.equalsIgnoreCase("User already exists")){
                        showToast("User Already Exist");

                    }
                    else{
                        showToast("oops! please try again!");
                    }
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CompanyRegistrationActivity.this, Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
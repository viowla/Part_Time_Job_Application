package com.example.parttimejobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, repPassword;
    Button btnRegister, company_registration;
    final String url_Register= "register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        etName= (EditText) findViewById(R.id.fio);
        etEmail= (EditText) findViewById(R.id.email);
        etPassword= (EditText) findViewById(R.id.password);
        repPassword= (EditText) findViewById(R.id.password2);
        btnRegister = findViewById(R.id.regis_user);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name= etName.getText().toString();
                String Email= etEmail.getText().toString();
                String Password=etPassword.getText().toString();

                new RegisterUser().execute(Name, Email, Password);
            }
        });

        company_registration = findViewById(R.id.regis_company);
        company_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, CompanyRegistrationActivity.class));
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
                        Intent i= new Intent(RegistrationActivity.this, FirstPageActivity.class);
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
                Toast.makeText(RegistrationActivity.this, Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
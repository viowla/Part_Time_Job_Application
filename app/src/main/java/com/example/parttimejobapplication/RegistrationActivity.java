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

    EditText etName, etEmail, etPhone, etPassword, repPassword;
    Button btnRegister, company_registration;
    final String url_Register= "register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        etName= (EditText) findViewById(R.id.userFullnameRegistration);
        etEmail= (EditText) findViewById(R.id.userEmailRegistration);
        etPhone= (EditText) findViewById(R.id.userPhoneRegistration);
        etPassword= (EditText) findViewById(R.id.passwordUserRegistration);
        repPassword= (EditText) findViewById(R.id.repeatPasswordUserRegistration);
        btnRegister = findViewById(R.id.registrationUserBtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name= etName.getText().toString();
                String Email= etEmail.getText().toString();
                String Password=etPassword.getText().toString();
                String Phone=etPassword.getText().toString();
                String repPassword=etPassword.getText().toString();

                new RegisterUser().execute(Name, Email, Phone, Password, repPassword);
            }
        });

        company_registration = findViewById(R.id.companyRegistrationPageBtn);
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
            String Phone= strings[2];
            String Password=strings[3];
            String repPassword=strings[4];
            String finalurl=url_Register+"?user_name="+Name+
                    "&user_email="+Email+
                    "&user_phone="+Phone+
                    "&user_password="+Password+
                    "&user_repPassword="+repPassword;

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
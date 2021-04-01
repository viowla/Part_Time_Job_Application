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

public class CompanyRegistrationActivity extends AppCompatActivity {

    Button companyRegistration;
    EditText etCompanyName, etRepresentativeName, etCompanyEmail, etCompanyPhone, etComapnyPassword, repCompanyPassword;

    final String url_Register= "add/company/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registrion);
        companyRegistration = findViewById(R.id.companyRegistrationBtn);
        etCompanyName = findViewById(R.id.companyNameRegistration);
        etRepresentativeName = findViewById(R.id.companyRepresentativeNameRegistration);
        etCompanyEmail = findViewById(R.id.companyEmailRegistration);
        etCompanyPhone = findViewById(R.id.companyPhoneRegistration);
        etComapnyPassword = findViewById(R.id.companyPasswordRegistration);
        repCompanyPassword = findViewById(R.id.repeatPasswordCompanyRegistration);

        companyRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName= etCompanyName.getText().toString();
                String companyRepresentativeName= etRepresentativeName.getText().toString();
                String companyEmail= etCompanyEmail.getText().toString();
                String companyPassword=etComapnyPassword.getText().toString();
                String companyPhone=etCompanyPhone.getText().toString();
                String companyRepPassword=repCompanyPassword.getText().toString();

                new RegisterCompany().execute(companyName,companyRepresentativeName, companyEmail, companyPhone, companyPassword, companyRepPassword);
            }
        });
    }

    public class RegisterCompany extends AsyncTask<String,Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String companyName= strings[0];
            String companyRepresentativeName= strings[1];
            String companyEmail= strings[2];
            String companyPhone= strings[3];
            String companyPassword=strings[4];
            String companyRepPassword=strings[5];
            String finalurl=url_Register+"?company_name="+companyName+
                    "&company_representative_name="+companyRepresentativeName+
                    "&company_email="+companyEmail+
                    "&company_phone="+companyPhone+
                    "&company_password="+companyPassword+
                    "&user_repPassword="+companyRepPassword;

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
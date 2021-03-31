package com.example.parttimejobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnlogin;
    TextView tvRegister;
    final String login_url = "https://localhost/login/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.vhod);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        //login activity

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//getting text from textbox
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new LoginUser().execute(Email, Password);

            }
        });

    }

    //requesting email and password from server

    public class LoginUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String Email= strings[0];
            String Password= strings[1];

            OkHttpClient okHttpClient= new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(login_url)
                    .post(formBody)
                    .build();

            //checking whether we are getting response from server or not

            Response response=null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result= response.body().string();
                    if(result.equalsIgnoreCase("login")){    //"login" text is fetching from php file is login success
                        Intent i= new Intent(LoginActivity.this, FirstPageActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        showToast("Wrong Username n password");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
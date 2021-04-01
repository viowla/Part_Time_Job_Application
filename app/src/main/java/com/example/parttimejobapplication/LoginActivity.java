package com.example.parttimejobapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parttimejobapplication.login.RetrofitClientInstance;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText emailTxt, passTxt;
    private String email, password;
    private TextView signupPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.loginBtn);
        emailTxt=findViewById(R.id.emailLogin);
        passTxt=findViewById(R.id.passwordLogin);

        signupPage = findViewById(R.id.tvRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailTxt.getText().toString();
                password=passTxt.getText().toString();

                String authToken= createAuthToken(email, password);
                checkLoginDetails(authToken);
            }
        });


    }

    public void onClickMove(View view){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final JsonPlaceHolderApi jsonPlaceHolderApi =retrofit.create(JsonPlaceHolderApi.class);

        Call<String> call = jsonPlaceHolderApi.checkLogin(authToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    if (response.body().matches("success")){
                        Toast.makeText(getApplicationContext(), "Success Logged In", Toast.LENGTH_LONG).show();
                        Intent intent =  new Intent(LoginActivity.this, FirstPageActivity.class);
                        startActivity(intent);

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }

    private String createAuthToken(String email, String password){
        byte[] data = new byte[0];
        try {
            data=(email+":"+password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return "Basic "+ Base64.encodeToString(data, Base64.NO_WRAP);
    }
}
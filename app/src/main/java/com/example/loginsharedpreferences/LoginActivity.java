package com.example.loginsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity{

    private SharedPreferences sharedPreferences;
    public static final String KEY_USER = "keyUser";
    public static final String KEY_PASSWORD = "keyPassword";
    public static final String USERNAME = "username";
    public static final String MY_SHARED_PREFERENCE= "mySharedPreference";
    private EditText user;
    private TextInputEditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.editUserName);
        password = findViewById(R.id.editPassword);

        sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCE ,MODE_PRIVATE);

        getUserInfo();
    }

    public void onLogin(View view){
        loginUser();
    }

    private void getUserInfo() {
        if(checkIfLoggedIn()){
            autoLogin();
        }
    }

    private boolean checkIfLoggedIn(){
        String username = sharedPreferences.getString(KEY_USER, "");
        String password = sharedPreferences.getString(KEY_PASSWORD, "");
        return !username.isEmpty() && !password.isEmpty();
    }

    private void autoLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USERNAME, sharedPreferences.getString(KEY_USER, ""));
        startActivity(intent);
    }

    private void loginUser(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(ifUserIsEmpty() && ifPasswordIsEmpty()){
            String name = user.getText().toString();
            String pass = password.getText().toString();

            editor.putString(KEY_USER, name);
            editor.putString(KEY_PASSWORD, pass);
            editor.apply();

            autoLogin();
        } else if(!ifUserIsEmpty()){
            user.setError("Must fill the field");
        } else if(!ifPasswordIsEmpty()){
            password.setError("Must fill the field");
        }
    }

    private boolean ifUserIsEmpty(){
        return !user.getText().toString().isEmpty();
    }

    private boolean ifPasswordIsEmpty(){
        return !password.getText().toString().isEmpty();
    }
}


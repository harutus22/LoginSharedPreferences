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

    private EditText user;
    private TextInputEditText password;

    public static final String KEY_USER = "keyUser";
    public static final String KEY_PASSWORD = "keyPassword";
    public static final String USERNAME = "username";
    public static final String MY_SHARED_PREFERENCE= "mySharedPreference";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private boolean isLoggedIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCE ,MODE_PRIVATE);

        getUserInfo();
    }

    private void findViews() {
        user = findViewById(R.id.editUserName);
        password = findViewById(R.id.editPassword);
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
        isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        if(username != null && password != null) {
            return isLoggedIn;
        }
        return false;
    }

    private void autoLogin(){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USERNAME, sharedPreferences.getString(KEY_USER, ""));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loginUser(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        isLoggedIn = true;
        if(ifUserIsEmpty() && ifPasswordIsEmpty()){
            String name = user.getText().toString();
            String pass = password.getText().toString();

            editor.putString(KEY_USER, name);
            editor.putString(KEY_PASSWORD, pass);
            editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
            editor.apply();

            autoLogin();
        } else if(!ifUserIsEmpty()){
            user.setError("Must fill the field");
        } else if(!ifPasswordIsEmpty()){
            password.setError("Must fill the field");
        }
    }

    private boolean ifUserIsEmpty(){
        if(user.getText() != null) {
            return !user.getText().toString().isEmpty();
        }
        return false;
    }

    private boolean ifPasswordIsEmpty(){
        if(password.getText() != null) {
            return !password.getText().toString().isEmpty();
        }
        return false;
    }
}


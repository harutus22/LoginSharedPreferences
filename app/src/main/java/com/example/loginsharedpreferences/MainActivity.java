package com.example.loginsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        String welcomeText = "Welcome home " + getIntent().getStringExtra(LoginActivity.USERNAME);

        textView.setText(welcomeText);
    }

    public void onLogOut(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(
                LoginActivity.MY_SHARED_PREFERENCE, MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        startActivity(new Intent(this, LoginActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}

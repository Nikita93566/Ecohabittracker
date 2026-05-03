package com.example.ecohabittracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    Button btnStart;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        btnStart = findViewById(R.id.btnStart);
        etName = findViewById(R.id.etName); // add in XML

        btnStart.setOnClickListener(v -> {

            String name = etName.getText().toString();

            SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
            prefs.edit().putString("name", name).apply();

            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
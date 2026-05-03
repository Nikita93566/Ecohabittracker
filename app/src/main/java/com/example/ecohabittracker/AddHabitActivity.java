package com.example.ecohabittracker;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecohabittracker.data.AppDatabase;
import com.example.ecohabittracker.data.Habit;

public class AddHabitActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnSave;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnSave = findViewById(R.id.btnSave);

        db = AppDatabase.getDB(this);

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter habit name", Toast.LENGTH_SHORT).show();
                return;
            }

            Habit habit = new Habit(name, desc);

            new Thread(() -> {
                db.habitDao().insert(habit);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();
        });
    }
}
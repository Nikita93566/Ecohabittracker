package com.example.ecohabittracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvScore, tvLevel, tvStreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvProfileScore);
        tvLevel = findViewById(R.id.tvProfileLevel);
        tvStreak = findViewById(R.id.tvProfileStreak);

        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);

        String name = prefs.getString("name", "User");

        tvName.setText("👤 Name: " + name);

        // Dummy values (you can pass real later)
        tvScore.setText("🌍 Score: Keep going!");
        tvLevel.setText("🏆 Level: Eco Beginner");
        tvStreak.setText("🔥 Streak: Stay consistent!");
    }
}
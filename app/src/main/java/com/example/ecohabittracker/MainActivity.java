package com.example.ecohabittracker;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecohabittracker.adapter.HabitAdapter;
import com.example.ecohabittracker.data.AppDatabase;
import com.example.ecohabittracker.data.Habit;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnAdd, btnBadge;

    TextView tvScore, tvStreak, tvLevel;

    AppDatabase db;

    int totalScore = 0;
    int totalStreak = 0;
    String level = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnBadge = findViewById(R.id.btnBadge);

        tvScore = findViewById(R.id.tvScore);
        tvStreak = findViewById(R.id.tvStreak);
        tvLevel = findViewById(R.id.tvLevel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }

        db = AppDatabase.getDB(this);

        showTestNotification();
        loadData();

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddHabitActivity.class))
        );

        btnBadge.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, BadgeActivity.class);
            i.putExtra("score", totalScore);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {

        List<Habit> list = db.habitDao().getAll();

        // ✅ ADD HERE (IMPORTANT)
        if (list.isEmpty()) {
            db.habitDao().insert(new Habit("Avoid Plastic", "Use reusable bottle"));
            db.habitDao().insert(new Habit("Save Water", "Turn off tap"));

            // 🔄 reload list after inserting
            list = db.habitDao().getAll();
        }

        long today = System.currentTimeMillis();

        // ✅ DAILY RESET
        for (Habit habit : list) {
            if (today - habit.lastUpdated > 86400000) {
                habit.isDoneToday = false;
                db.habitDao().updateHabit(habit);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HabitAdapter(list, db));

        totalScore = 0;
        totalStreak = 0;

        for (Habit habit : list) {
            totalScore += habit.points;

            if (habit.streak > totalStreak) {
                totalStreak = habit.streak;
            }
        }

        tvScore.setText("🌍 Eco Score: " + totalScore);
        tvStreak.setText("🔥 Total Streak: " + totalStreak + " Days");
        git config --global --add safe.directory C:/Users/ganes/AndroidStudioProjects/Ecohabittracker3
        if (totalScore >= 100) {
            level = "Green Master";
        } else if (totalScore >= 60) {
            level = "Eco Hero";
        } else if (totalScore >= 30) {
            level = "Sustainability Champion";
        } else {
            level = "Beginner";
        }

        tvLevel.setText("🏆 Level: " + level);
    }
    void showTestNotification() {

        String channelId = "eco_channel";

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Eco Habit Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Eco Habit Tracker")
                        .setContentText("🌿 Don’t forget your eco habits today!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

        manager.notify(1, builder.build());
    }
}
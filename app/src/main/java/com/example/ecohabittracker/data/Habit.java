package com.example.ecohabittracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habits")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;

    public int progress = 0;
    public int points = 0;

    public int streak = 0;
    public long lastUpdated = 0;

    public boolean isDoneToday = false;   // ✅ NEW FIELD

    public Habit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
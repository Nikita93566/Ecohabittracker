package com.example.ecohabittracker.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {Habit.class},
        version = 4,
        exportSchema = false   // ✅ ADD THIS LINE
)// 🔥 CHANGE VERSION (VERY IMPORTANT)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HabitDao habitDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDB(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "eco_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration() // 🔥 VERY IMPORTANT
                    .build();
        }
        return INSTANCE;
    }
}
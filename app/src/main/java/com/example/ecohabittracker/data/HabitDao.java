package com.example.ecohabittracker.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HabitDao {

    @Insert
    void insert(Habit habit);

    @Query("SELECT * FROM habits")
    List<Habit> getAll();

    @Update   // ✅ NEW (VERY IMPORTANT)
    void updateHabit(Habit habit);
}
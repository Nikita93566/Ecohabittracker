package com.example.ecohabittracker.adapter;

import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecohabittracker.R;
import com.example.ecohabittracker.data.AppDatabase;
import com.example.ecohabittracker.data.Habit;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {

    List<Habit> list;
    AppDatabase db;

    public HabitAdapter(List<Habit> list, AppDatabase db) {
        this.list = list;
        this.db = db;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc, points, streak;
        ProgressBar progressBar;
        Button btn;

        public ViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.tvName);
            desc = v.findViewById(R.id.tvDesc);
            points = v.findViewById(R.id.tvPoints);
            streak = v.findViewById(R.id.tvStreak);
            progressBar = v.findViewById(R.id.progressBar);
            btn = v.findViewById(R.id.btnDone);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {

        Habit habit = list.get(pos);

        h.name.setText(habit.name);
        h.desc.setText(habit.description);
        h.points.setText("Points: " + habit.points);
        h.progressBar.setProgress(habit.progress);
        h.streak.setText("🔥 Streak: " + habit.streak + " days");

        // ✅ Button state
        if (habit.isDoneToday) {
            h.btn.setText("Done ✅");
            h.btn.setEnabled(false);
        } else {
            h.btn.setText("Mark Done");
            h.btn.setEnabled(true);
        }

        h.btn.setOnClickListener(v -> {

            if (habit.isDoneToday) {
                Toast.makeText(v.getContext(), "Already done today!", Toast.LENGTH_SHORT).show();
                return;
            }

            long today = System.currentTimeMillis();

            if (habit.lastUpdated == 0) {
                habit.streak = 1;
            } else if (today - habit.lastUpdated < 2 * 86400000) {
                habit.streak++;
            } else {
                habit.streak = 1;
            }

            habit.lastUpdated = today;
            habit.isDoneToday = true;

            habit.points += 10;

            if (habit.progress < 10) {
                habit.progress++;
            }

            db.habitDao().updateHabit(habit);

            notifyItemChanged(pos);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
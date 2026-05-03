# 🌱 Eco Habit Tracker

An Android application that helps users build and maintain eco-friendly habits through tracking, rewards, and gamification.


#📱 Features

* ✅ Add and manage daily eco habits
* 🔄 Daily habit tracking system
* 🔥 Streak management (consistency tracking)
* 🎯 Points-based reward system
* 🏆 Level system (Beginner → Green Master)
* 🎖 Badge system for achievements
* 🔔 Notification reminders for habits
* 📊 Progress tracking using visual indicators

---

#🛠 Tech Stack

* Language: Java
* UI: XML
* Database: Room Database (SQLite)
* IDE: Android Studio

---

#🧠 Working Logic

* Each habit stores:

  * Progress
  * Points
  * Streak
  * Last updated timestamp

* Streak Logic:

  * If completed within 24 hrs → streak increases
  * If missed → streak resets

* Daily Reset:

  * `isDoneToday` flag resets every 24 hours

* Score Calculation:

  * Total score = sum of all habit points

* Level System:

  * Beginner (<30)
  * Sustainability Champion (30–60)
  * Eco Hero (60–100)
  * Green Master (100+)

---

#🚀 How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/Nikita93566/Ecohabittracker.git
   ```

2. Open in Android Studio

3. Sync Gradle

4. Run the app on Emulator / Device

---

#📸 Screenshots




#🔮 Future Scope

* ☁ Firebase integration (cloud sync)
* 👤 User login & profile system
* ⏰ Smart reminders using WorkManager
* 🤖 AI-based habit suggestions
* 🌍 Community & leaderboard features

---

#💡 Challenges Faced

* Implementing accurate streak logic
* Managing daily reset using timestamps
* Preventing multiple updates in a single day

---

#👩‍💻 Author

Nikita Phuke
BTech AI & Data Science

---



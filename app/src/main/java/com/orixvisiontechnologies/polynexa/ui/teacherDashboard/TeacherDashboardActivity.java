package com.orixvisiontechnologies.polynexa.ui.teacherDashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.placeholderFragment.PlaceholderFragment;

public class TeacherDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        BottomNavigationView nav = findViewById(R.id.bottom_navteacher);

        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            String title;
            if (id == R.id.nav_classes) title = "My Classes";
            else if (id == R.id.nav_attendance) title = "Take Attendance";
            else if (id == R.id.nav_profile) title = "Teacher Profile";
            else title = "Teacher Home";
            Fragment f = PlaceholderFragment.newInstance(title);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, f).commit();
            return true;
        });

        if (savedInstanceState == null) nav.setSelectedItemId(R.id.nav_home);
    }
}
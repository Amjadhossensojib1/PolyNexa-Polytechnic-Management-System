package com.orixvisiontechnologies.polynexa.ui.studentDashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.studentChatFragment.StudentChatFragment;
import com.orixvisiontechnologies.polynexa.ui.studentNoticeFragment.StudentNoticeFragment;
import com.orixvisiontechnologies.polynexa.ui.studentProfileFragment.StudentProfileFragment;
import com.orixvisiontechnologies.polynexa.ui.studentRoutineFragment.StudentRoutineFragment;
import com.orixvisiontechnologies.polynexa.ui.placeholderFragment.PlaceholderFragment;
import com.orixvisiontechnologies.polynexa.ui.studenthomeFragment.StudentHomeFragment;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);

        nav.setOnItemSelectedListener(item -> {
            Fragment f;
            int id = item.getItemId();
            if (id == R.id.nav_routine) f = new StudentRoutineFragment();
            else if (id == R.id.nav_notice) f = new StudentNoticeFragment();
            else if (id == R.id.nav_profile) f = new StudentProfileFragment();
            else if (id == R.id.nav_chat) f = new StudentChatFragment();
            else f = new StudentHomeFragment();
            load(f);
            return true;
        });

        if (savedInstanceState == null) nav.setSelectedItemId(R.id.nav_home);
    }

    private void load(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f).commit();
    }
}
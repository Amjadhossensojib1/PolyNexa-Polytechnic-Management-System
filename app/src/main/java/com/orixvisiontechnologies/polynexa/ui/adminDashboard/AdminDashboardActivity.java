package com.orixvisiontechnologies.polynexa.ui.adminDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.placeholderFragment.PlaceholderFragment;
import com.orixvisiontechnologies.polynexa.ui.onboardActivity.OnboardActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawer = findViewById(R.id.drawer_layout);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navView = findViewById(R.id.nav_view);

        toolbar.setNavigationOnClickListener(v -> drawer.openDrawer(GravityCompat.START));

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_logout) {
                Intent i = new Intent(this, OnboardActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                String title = "Admin Dashboard";
                if (id == R.id.nav_students) title = "Manage Students";
                else if (id == R.id.nav_teachers) title = "Manage Teachers";
                else if (id == R.id.nav_notice) title = "Notices";
                Fragment f = PlaceholderFragment.newInstance(title);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance("Admin Dashboard"))
                    .commit();
        }
    }
}
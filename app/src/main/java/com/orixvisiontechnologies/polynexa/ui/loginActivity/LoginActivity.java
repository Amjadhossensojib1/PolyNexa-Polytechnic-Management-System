package com.orixvisiontechnologies.polynexa.ui.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.adminDashboard.AdminDashboardActivity;
import com.orixvisiontechnologies.polynexa.ui.signupActivity.SignupActivity;
import com.orixvisiontechnologies.polynexa.ui.studentDashboard.StudentDashboardActivity;
import com.orixvisiontechnologies.polynexa.ui.teacherDashboard.TeacherDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String role = getIntent().getStringExtra("role");
        MaterialButton btnSignIn = findViewById(R.id.btn_sign_in);
        TextView tvGoSignup = findViewById(R.id.tv_go_signup);

        btnSignIn.setOnClickListener(v -> {
            Intent intent;
            if ("teacher".equals(role)) {
                intent = new Intent(this, TeacherDashboardActivity.class);
            } else if ("admin".equals(role)) {
                intent = new Intent(this, AdminDashboardActivity.class);
            } else {
                intent = new Intent(this, StudentDashboardActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        tvGoSignup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            intent.putExtra("role", role);
            startActivity(intent);
        });
    }
}
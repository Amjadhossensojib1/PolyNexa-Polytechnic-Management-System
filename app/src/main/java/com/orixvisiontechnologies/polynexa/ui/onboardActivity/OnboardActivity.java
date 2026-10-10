package com.orixvisiontechnologies.polynexa.ui.onboardActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.loginActivity.LoginActivity;

public class OnboardActivity extends AppCompatActivity {

    private MaterialButton btnStudent,btnTeacher, btnAdminAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        btnStudent = findViewById(R.id.btn_student);
        btnTeacher = findViewById(R.id.btn_teacher);
        btnAdminAccount = findViewById(R.id.btn_admin);

        btnStudent.setOnClickListener(view -> {
            Intent intent = new Intent(OnboardActivity.this, LoginActivity.class);
            intent.putExtra("role", "student");
            startActivity(intent);
        });
        btnTeacher.setOnClickListener(view -> {
            Intent intent = new Intent(OnboardActivity.this, LoginActivity.class);
            intent.putExtra("role", "teacher");
            startActivity(intent);
        });

        btnAdminAccount.setOnClickListener(view -> {
            Intent intent = new Intent(OnboardActivity.this, LoginActivity.class);
            intent.putExtra("role", "admin");
            startActivity(intent);
        });
    }
}
package com.orixvisiontechnologies.polynexa.ui.signupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.orixvisiontechnologies.polynexa.R;
import com.orixvisiontechnologies.polynexa.ui.adminDashboard.AdminDashboardActivity;
import com.orixvisiontechnologies.polynexa.ui.loginActivity.LoginActivity;
import com.orixvisiontechnologies.polynexa.ui.studentDashboard.StudentDashboardActivity;
import com.orixvisiontechnologies.polynexa.ui.teacherDashboard.TeacherDashboardActivity;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText etFullName, etEmail, etPassword;
    private MaterialButton btnSignUp;
    private TextView tvGoSignIn;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        role = getIntent().getStringExtra("role");

        etFullName = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvGoSignIn = findViewById(R.id.tv_go_signin);

        btnSignUp.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etFullName.setError("Full name dao");
                return;
            }
            if (TextUtils.isEmpty(email)
                    || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Thik email dao");
                return;
            }
            if (pass.length() < 6) {
                etPassword.setError("Kom pokkhe 6 character dao");
                return;
            }

            // TODO: pore ekhane Firebase/API diye real account create korben
            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
            openDashboard();
        });

        tvGoSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("role", role);
            startActivity(intent);
            finish();
        });
    }

    private void openDashboard() {
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
    }
}
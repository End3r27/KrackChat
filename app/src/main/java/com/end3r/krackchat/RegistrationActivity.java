package com.end3r.krackchat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, secretCodeEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        secretCodeEditText = findViewById(R.id.secretCodeEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String secretCode = secretCodeEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || secretCode.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Add your registration logic here
        // 1. Validate the secret code
        // 2. Hash the password
        // 3. Store the user data in your database

        // Placeholder for registration success
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        // You might want to start the main activity here after successful registration
        // Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        // startActivity(intent);
        // finish();
    }
}
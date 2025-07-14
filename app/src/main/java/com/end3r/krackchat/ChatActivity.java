package com.end3r.krackchat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChatActivity extends AppCompatActivity {


@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);

    Toolbar chatToolbar = findViewById(R.id.chatToolbar);
    setSupportActionBar(chatToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    chatToolbar.setNavigationOnClickListener(v -> onBackPressed());

    ImageView profileImageView = findViewById(R.id.profileImageView);
    TextView chatTitleTextView = findViewById(R.id.chatTitleTextView);

    // Get the chat ID and username from the intent
    String chatId = getIntent().getStringExtra("chatId");
    String username = getIntent().getStringExtra("username");

    // Set the chat title or other UI elements
    chatTitleTextView.setText("Chat with " + username);

    // Load profile image if available
    // profileImageView.setImageURI(...);
}
}
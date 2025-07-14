package com.end3r.krackchat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment implements ChatsAdapter.OnChatClickListener {

    private TextView usernameTextView;
    private EditText searchEditText;
    private RecyclerView chatsRecyclerView;
    private ChatsAdapter chatsAdapter;
    private List<Chat> chats;
    private List<Chat> filteredChats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();
        setupSearchListener();
        loadChatsData();
    }

    private void initViews(View view) {
        usernameTextView = view.findViewById(R.id.usernameTextView);
        searchEditText = view.findViewById(R.id.searchEditText);
        chatsRecyclerView = view.findViewById(R.id.chatsRecyclerView);
    }

    private void setupRecyclerView() {
        chats = new ArrayList<>();
        filteredChats = new ArrayList<>();
        chatsAdapter = new ChatsAdapter(filteredChats, this);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatsRecyclerView.setAdapter(chatsAdapter);
    }

    private void setupSearchListener() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterChats(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadChatsData() {
        // Set current user's nickname
        usernameTextView.setText("john_doe");

        // Load sample chat data (in a real app, this would come from a database or API)
        chats.add(new Chat("1", "jane_smith", "Hey! How's your project going?", System.currentTimeMillis(), 2));
        chats.add(new Chat("2", "alex_wilson", "Thanks for the code review!", System.currentTimeMillis() - 3600000, 0));
        chats.add(new Chat("3", "sarah_jones", "Let's grab coffee tomorrow?", System.currentTimeMillis() - 7200000, 1));
        chats.add(new Chat("4", "mike_brown", "Great job on the presentation!", System.currentTimeMillis() - 86400000, 0));
        chats.add(new Chat("5", "emma_davis", "Can you help me with the login issue?", System.currentTimeMillis() - 172800000, 3));

        filteredChats.addAll(chats);
        chatsAdapter.notifyDataSetChanged();
    }

    private void filterChats(String query) {
        filteredChats.clear();

        if (query.isEmpty()) {
            filteredChats.addAll(chats);
        } else {
            for (Chat chat : chats) {
                if (chat.getUsername().toLowerCase().contains(query.toLowerCase()) ||
                        chat.getLastMessage().toLowerCase().contains(query.toLowerCase())) {
                    filteredChats.add(chat);
                }
            }
        }

        chatsAdapter.notifyDataSetChanged();
    }


@Override
public void onChatClick(Chat chat) {
    openChat(chat);
}

private void openChat(Chat chat) {
    Intent intent = new Intent(getContext(), ChatActivity.class);
    intent.putExtra("chatId", chat.getId());
    intent.putExtra("username", chat.getUsername());
    startActivity(intent);
}
}
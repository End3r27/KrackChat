package com.end3r.krackchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements PostsAdapter.OnPostInteractionListener {

    private ImageView profileImageView;
    private TextView usernameTextView;
    private TextView bioTextView;
    private TextView postsCountTextView;
    private Button editProfileButton;
    private RecyclerView userPostsRecyclerView;
    private PostsAdapter postsAdapter;
    private List<Post> userPosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();
        loadProfileData();
        setupClickListeners();
    }

    private void initViews(View view) {
        profileImageView = view.findViewById(R.id.profileImageView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        bioTextView = view.findViewById(R.id.bioTextView);
        postsCountTextView = view.findViewById(R.id.postsCountTextView);
        editProfileButton = view.findViewById(R.id.editProfileButton);
        userPostsRecyclerView = view.findViewById(R.id.userPostsRecyclerView);
    }

    private void setupRecyclerView() {
        userPosts = new ArrayList<>();
        postsAdapter = new PostsAdapter(userPosts, this);
        userPostsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userPostsRecyclerView.setAdapter(postsAdapter);
    }

    private void loadProfileData() {
        // Load user profile data (in a real app, this would come from a database or API)
        usernameTextView.setText("john_doe");
        bioTextView.setText("Software developer | Coffee enthusiast | Mountain lover");

        // Load user's posts
        userPosts.add(new Post("1", "john_doe", "Just finished an amazing hike in the mountains! The view was absolutely breathtaking 🏔️", 15, 3, System.currentTimeMillis()));
        userPosts.add(new Post("6", "john_doe", "Working on a new Android app. The coding journey never ends! 💻", 23, 8, System.currentTimeMillis() - 86400000));
        userPosts.add(new Post("7", "john_doe", "Beautiful sunset from my office window today 🌅", 18, 4, System.currentTimeMillis() - 172800000));
        userPosts.add(new Post("8", "john_doe", "Coffee and code - my favorite combination for a productive day ☕", 31, 12, System.currentTimeMillis() - 259200000));

        postsCountTextView.setText(userPosts.size() + " posts");
        postsAdapter.notifyDataSetChanged();
    }

    private void setupClickListeners() {
        editProfileButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            // Here you would typically open an edit profile activity or dialog
        });
    }

    @Override
    public void onUpvoteClick(Post post) {
        if (post.isUpvoted()) {
            post.setUpvoteCount(post.getUpvoteCount() - 1);
            post.setUpvoted(false);
        } else {
            post.setUpvoteCount(post.getUpvoteCount() + 1);
            post.setUpvoted(true);
        }
        postsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCommentClick(Post post) {
        Toast.makeText(getContext(), "Opening comments for post", Toast.LENGTH_SHORT).show();
    }
}
package com.end3r.krackchat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements
        StoriesAdapter.OnStoryClickListener,
        PostsAdapter.OnPostInteractionListener {

    private RecyclerView storiesRecyclerView;
    private RecyclerView postsRecyclerView;
    private StoriesAdapter storiesAdapter;
    private PostsAdapter postsAdapter;
    private List<Story> stories;
    private List<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerViews();
        loadSampleData();
    }

    private void initViews(View view) {
        storiesRecyclerView = view.findViewById(R.id.storiesRecyclerView);
        postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
    }

    private void setupRecyclerViews() {
        // Stories RecyclerView
        stories = new ArrayList<>();
        storiesAdapter = new StoriesAdapter(stories, this);
        storiesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        storiesRecyclerView.setAdapter(storiesAdapter);

        // Posts RecyclerView
        posts = new ArrayList<>();
        postsAdapter = new PostsAdapter(posts, this);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postsRecyclerView.setAdapter(postsAdapter);
    }

    private void loadSampleData() {
        // Sample stories data
        stories.add(new Story("1", "john_doe", "https://example.com/story1.jpg", System.currentTimeMillis()));
        stories.add(new Story("2", "jane_smith", "https://example.com/story2.jpg", System.currentTimeMillis()));
        stories.add(new Story("3", "alex_wilson", "https://example.com/story3.jpg", System.currentTimeMillis()));
        stories.add(new Story("4", "sarah_jones", "https://example.com/story4.jpg", System.currentTimeMillis()));
        stories.add(new Story("5", "mike_brown", "https://example.com/story5.jpg", System.currentTimeMillis()));

        // Sample posts data
        posts.add(new Post("1", "john_doe", "Just finished an amazing hike in the mountains! The view was absolutely breathtaking 🏔️", 15, 3, System.currentTimeMillis()));
        posts.add(new Post("2", "jane_smith", "Working on a new project tonight. Sometimes the best ideas come when you least expect them ✨", 8, 2, System.currentTimeMillis()));
        posts.add(new Post("3", "alex_wilson", "Coffee and coding - the perfect combination for a productive morning ☕", 22, 7, System.currentTimeMillis()));
        posts.add(new Post("4", "sarah_jones", "Sunset from my balcony tonight. Nature never fails to amaze me 🌅", 31, 5, System.currentTimeMillis()));
        posts.add(new Post("5", "mike_brown", "Finally finished reading that book everyone's been talking about. Highly recommend it!", 12, 4, System.currentTimeMillis()));

        // Notify adapters
        storiesAdapter.notifyDataSetChanged();
        postsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStoryClick(Story story) {
        Toast.makeText(getContext(), "Clicked on " + story.getUsername() + "'s story", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), "Opening comments for " + post.getUsername() + "'s post", Toast.LENGTH_SHORT).show();
    }
}
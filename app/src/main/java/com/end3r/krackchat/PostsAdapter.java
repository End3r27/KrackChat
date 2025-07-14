package com.end3r.krackchat;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private List<Post> posts;
    private OnPostInteractionListener listener;

    public interface OnPostInteractionListener {
        void onUpvoteClick(Post post);
        void onCommentClick(Post post);
    }

    public PostsAdapter(List<Post> posts, OnPostInteractionListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post, listener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView postUsername;
        TextView postContent;
        TextView upvoteCount;
        TextView commentCount;
        View upvoteButton;
        View commentButton;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postUsername = itemView.findViewById(R.id.postUsername);
            postContent = itemView.findViewById(R.id.postContent);
            upvoteCount = itemView.findViewById(R.id.upvoteCount);
            commentCount = itemView.findViewById(R.id.commentCount);
            upvoteButton = itemView.findViewById(R.id.upvoteButton);
            commentButton = itemView.findViewById(R.id.commentButton);
        }

        void bind(Post post, OnPostInteractionListener listener) {
            postUsername.setText(post.getUsername());
            postContent.setText(post.getContent());
            upvoteCount.setText(String.valueOf(post.getUpvoteCount()));
            commentCount.setText(String.valueOf(post.getCommentCount()));

            upvoteButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUpvoteClick(post);
                }
            });

            commentButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCommentClick(post);
                }
            });
        }
    }
}
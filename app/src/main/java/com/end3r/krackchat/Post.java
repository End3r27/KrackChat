package com.end3r.krackchat;


public class Post {
    private String id;
    private String username;
    private String content;
    private int upvoteCount;
    private int commentCount;
    private long timestamp;
    private boolean isUpvoted;

    public Post(String id, String username, String content, int upvoteCount, int commentCount, long timestamp) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.upvoteCount = upvoteCount;
        this.commentCount = commentCount;
        this.timestamp = timestamp;
        this.isUpvoted = false;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getContent() { return content; }
    public int getUpvoteCount() { return upvoteCount; }
    public int getCommentCount() { return commentCount; }
    public long getTimestamp() { return timestamp; }
    public boolean isUpvoted() { return isUpvoted; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setContent(String content) { this.content = content; }
    public void setUpvoteCount(int upvoteCount) { this.upvoteCount = upvoteCount; }
    public void setCommentCount(int commentCount) { this.commentCount = commentCount; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public void setUpvoted(boolean upvoted) { this.isUpvoted = upvoted; }
}
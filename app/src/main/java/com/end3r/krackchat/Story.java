package com.end3r.krackchat;

public class Story {
    private String id;
    private String username;
    private String imageUrl;
    private long timestamp;

    public Story(String id, String username, String imageUrl, long timestamp) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getImageUrl() { return imageUrl; }
    public long getTimestamp() { return timestamp; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

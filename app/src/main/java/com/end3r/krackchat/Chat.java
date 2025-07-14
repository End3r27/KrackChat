package com.end3r.krackchat;

public class Chat {
    private String id;
    private String username;
    private String lastMessage;
    private long timestamp;
    private int unreadCount;

    public Chat(String id, String username, String lastMessage, long timestamp, int unreadCount) {
        this.id = id;
        this.username = username;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.unreadCount = unreadCount;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getLastMessage() { return lastMessage; }
    public long getTimestamp() { return timestamp; }
    public int getUnreadCount() { return unreadCount; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public void setUnreadCount(int unreadCount) { this.unreadCount = unreadCount; }
}
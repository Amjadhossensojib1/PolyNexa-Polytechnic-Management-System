package com.orixvisiontechnologies.polynexa.model;

public class ChatMessage {

    private final String id;
    private final String name;
    private final String message;
    private final String time;
    private final int avatarRes;

    public ChatMessage(String id, String name, String message, String time) {
        this(id, name, message, time, 0);
    }

    public ChatMessage(String id, String name, String message, String time, int avatarRes) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.time = time;
        this.avatarRes = avatarRes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public int getAvatarRes() {
        return avatarRes;
    }
}
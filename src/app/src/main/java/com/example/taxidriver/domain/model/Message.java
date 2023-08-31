package com.example.taxidriver.domain.model;
import java.time.LocalDateTime;

public class Message {
    String id;
    String body;
    LocalDateTime timeSent;
    MessageType messageType;
    Drive drive;

    public Message(String id, String body, LocalDateTime timeSent, MessageType messageType, Drive drive) {
        this.id = id;
        this.body = body;
        this.timeSent = timeSent;
        this.messageType = messageType;
        this.drive = drive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}

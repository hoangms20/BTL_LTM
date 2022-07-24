package com.example.eventmanager.model;

public class RequestedEventDTO extends EventDTO{
    private String sender;

    public RequestedEventDTO() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

package com.example.eventmanager.model;

/**
 * This class is to Event Request info: Join or invite
 *
 * @author hoangnguyenthe20183925
 */
public class EventRequestDTO {
    private String sender;

    private String receiver;

    private String eventId;

    public EventRequestDTO() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}

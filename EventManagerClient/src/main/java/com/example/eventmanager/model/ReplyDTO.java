package com.example.eventmanager.model;

/**
 * This class is to Reply info
 *
 * @author hoangnguyenthe20183925
 */
public class ReplyDTO {
    private String sender;
    private String receiver;
    private String eventId;
    private String reply;

    public ReplyDTO() {
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}

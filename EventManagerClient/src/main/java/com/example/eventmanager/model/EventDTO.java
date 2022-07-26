package com.example.eventmanager.model;

/**
 * This class is to Event info
 *
 * @author hoangnguyenthe20183925
 */
public class EventDTO {
    private String id;
    private String name;
    private String time;
    private String place;
    private String description;
    private String createdBy;

    public EventDTO() {
    }

    public EventDTO(String id, String name, String time, String place, String description, String createdBy) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.place = place;
        this.description = description;
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

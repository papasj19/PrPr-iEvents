package edu.url.salle.spencerjames.johnson.proj;

import java.util.Date;

public class Event {

    private String title;
    private String description;
    private Date dateOfEvent;
    private String creator;

    public Event(String title, String description, Date dateOfEvent, String creator) {
        this.title = title;
        this.description = description;
        this.dateOfEvent = dateOfEvent;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public String getCreator() {
        return creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

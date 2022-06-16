package edu.url.salle.spencerjames.johnson.proj.models;

public class Event {
    public String name, image, location, description, eventStart_date, eventEnd_date, type;
    public int n_participators;
    public int id, ownerId;
    public Event(String name, String image, String location, String description, String eventStart_date, String eventEnd_date, String type, int n_participators) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.type = type;
        this.n_participators = n_participators;
    }

    public Event(int id, int ownerId, String name, String image, String location, String description, String eventStart_date, String eventEnd_date, String type, int n_participators) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.type = type;
        this.n_participators = n_participators;
    }
}

package edu.url.salle.spencerjames.johnson.proj.EntitiesForWritingToAPI;

public class EventPUTAPI {

    private String name;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private int participators;
    private String type;

    public EventPUTAPI() {
    }

    public EventPUTAPI(String name, String image, String location, String description, String startDate, String endDate, int participators, String type) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participators = participators;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setParticipators(int participators) {
        this.participators = participators;
    }

    public void setType(String type) {
        this.type = type;
    }
}

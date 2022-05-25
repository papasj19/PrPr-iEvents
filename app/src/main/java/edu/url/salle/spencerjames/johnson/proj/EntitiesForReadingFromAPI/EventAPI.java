package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class EventAPI {

    private int id;
    private String name;
    private int owner_id;
    private String date;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String EndDate;
    private int participants;
    //slug???????????
    private String typeEvent;



    public EventAPI(int id, String name, int owner_id, String date, String image, String location, String description, String startDate, String endDate, int participants, String typeEvent) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
        this.date = date;
        this.image = image;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        EndDate = endDate;
        this.participants = participants;
        this.typeEvent = typeEvent;
    }

    public EventAPI(){

    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setDate(String date) {
        this.date = date;
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
        EndDate = endDate;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }
}

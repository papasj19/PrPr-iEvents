package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class AssistancesEventUserAPI {

    private int user;
    private int event;
    private int puntuation;
    private String comentary;

    public AssistancesEventUserAPI() {
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }
}

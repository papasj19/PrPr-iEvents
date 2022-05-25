package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class AssistancesAPI {

    private EventAPI theEvent;
    private int puntuation;
    private String commentary;

    public AssistancesAPI() {
    }

    public void setTheEvent(EventAPI theEvent) {
        this.theEvent = theEvent;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}

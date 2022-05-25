package edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI;

public class EventScoreAPI {

    private EventAPI theEvent;
    private Float score;

    public EventScoreAPI(EventAPI theEvent, Float score) {
        this.theEvent = theEvent;
        this.score = score;
    }
}

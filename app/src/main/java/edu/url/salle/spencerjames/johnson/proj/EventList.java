package edu.url.salle.spencerjames.johnson.proj;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventList {

    private static EventList sEventList;
    private List<Event> mEvents;

    public static EventList get(Context context){
        if(sEventList == null){
            sEventList = new EventList(context);
        }
        return sEventList;
    }

    public EventList(Context context) {
        mEvents = new ArrayList<>();
    }

    public List<Event> getEvents(){return mEvents;}

    //im not sure if i recieve an event or the info about the event hmmmm
    public void addEvent(Event newEvent){
        mEvents.add(newEvent);
    }

    public void removeEvent(int position){mEvents.remove(position);}

    //im not sure if did this one correct
    public Event getEvent(UUID id){
        for(Event event : mEvents){
            if(event.getTitle().equals(id)){
                return event;
            }
        }
        return null;
    }
}

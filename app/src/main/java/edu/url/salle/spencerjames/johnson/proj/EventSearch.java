package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class EventSearch extends AppCompatActivity {

    private RecyclerView recView;


    private EventList listEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_search);

        //need to load persistence into list
        listEvents = EventList.get(this);


        recView = (RecyclerView) findViewById(R.id.main_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

    }
}
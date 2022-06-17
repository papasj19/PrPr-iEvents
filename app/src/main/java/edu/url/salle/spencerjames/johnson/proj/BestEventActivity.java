package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import edu.url.salle.spencerjames.johnson.proj.adapters.EventsListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BestEventActivity extends AppCompatActivity {

    private ListView eventsLv;
    private final ArrayList<Event> eventsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_event);

        init();
        Util.showProgressDialog(BestEventActivity.this, BestEventActivity.this.getString(R.string.getbestevent));
        API.getAllEvents(BestEventActivity.this, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(BestEventActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject eventJson = response.getJSONObject(i);
                        eventsList.add(new Event(eventJson.getInt("id"), eventJson.getInt("owner_id"),
                                eventJson.getString("name"), eventJson.getString("image"), eventJson.getString("location"),
                                eventJson.getString("description"), eventJson.getString("eventStart_date"), eventJson.getString("eventEnd_date"),
                                eventJson.getString("type"), eventJson.getInt("n_participators")));

                    }
                    Util.dismissProgressDialog();
                    setListview();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(BestEventActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });

    }

    private void init(){
        eventsLv = findViewById(R.id.best_lv_events);
    }

    private void setListview(){
        EventsListviewAdapter eventsListviewAdapter = new EventsListviewAdapter(BestEventActivity.this, eventsList, 0);
        eventsLv.setAdapter(eventsListviewAdapter);
    }
}
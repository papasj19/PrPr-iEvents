package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import edu.url.salle.spencerjames.johnson.proj.adapters.EventsListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpecificEventActivity extends AppCompatActivity {

    private EditText idEt, searchEtKeyword, searchEtLocation, searchEtDate;
    //private TextView eventInfoTv;
    private ListView eventsLv;
    private final ArrayList<Event> eventsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_event);

        eventsLv = findViewById(R.id.search_lv_events);

        idEt = findViewById(R.id.specific_et_id);
        searchEtKeyword = findViewById(R.id.specific_et_search_keyword);
        searchEtLocation = findViewById(R.id.specific_et_search_location);
        searchEtDate = findViewById(R.id.specific_et_search_date);

        findViewById(R.id.specific_btn_id).setOnClickListener(view -> {
            if(Util.isEditTextEmpty(idEt)){
                Util.showToast(SpecificEventActivity.this, SpecificEventActivity.this.getString(R.string.ID_first));
            }else{
                Util.showProgressDialog(SpecificEventActivity.this, SpecificEventActivity.this.getString(R.string.gettingeventinfo));
                API.getEventById(SpecificEventActivity.this, Integer.parseInt(idEt.getText().toString()), new VolleyInterfaceArray() {
                    @Override
                    public void onError(String message) {
                        Util.showToast(SpecificEventActivity.this, message);
                        Util.dismissProgressDialog();
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        showResults(response);
                    }
                });
            }
        });

        findViewById(R.id.specific_btn_search).setOnClickListener(view -> {
            if(Util.isEditTextEmpty(searchEtKeyword) && Util.isEditTextEmpty(searchEtLocation) && Util.isEditTextEmpty(searchEtDate)){
                Util.showToast(SpecificEventActivity.this, SpecificEventActivity.this.getString(R.string.Plzentkeyword));
            }else{
                Util.showProgressDialog(SpecificEventActivity.this, SpecificEventActivity.this.getString(R.string.gettingeventinfo));
                API.getEventBySearch(SpecificEventActivity.this,
                        "keyword="+searchEtKeyword.getText().toString()+
                        "&location="+searchEtLocation.getText().toString()
                        + (Util.isEditTextEmpty(searchEtDate) ? "" : searchEtDate.getText().toString())
                        , new VolleyInterfaceArray() {
                    @Override
                    public void onError(String message) {
                        Util.showToast(SpecificEventActivity.this, message);
                        Util.dismissProgressDialog();
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        showResults(response);
                    }
                });
            }
        });
    }

    private void showResults(JSONArray results) {
        try {
            if (results.length() > 0) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject eventJson = results.getJSONObject(i);
                    eventsList.add(new Event(eventJson.getInt("id"), eventJson.getInt("owner_id"),
                            eventJson.getString("name"), eventJson.getString("image"), eventJson.getString("location"),
                            eventJson.getString("description"), eventJson.getString("eventStart_date"), eventJson.getString("eventEnd_date"),
                            eventJson.getString("type"), eventJson.getInt("n_participators")));
                }
                setListview();
            } else {
                Util.showToast(SpecificEventActivity.this, "No events found.");
            }
        } catch (JSONException e) {
            Util.showToast(SpecificEventActivity.this, e.getLocalizedMessage());
            e.printStackTrace();
        }
        Util.dismissProgressDialog();
    }

    private void setListview(){
        EventsListviewAdapter eventsListviewAdapter = new EventsListviewAdapter(SpecificEventActivity.this, eventsList);
        eventsLv.setAdapter(eventsListviewAdapter);
    }
}
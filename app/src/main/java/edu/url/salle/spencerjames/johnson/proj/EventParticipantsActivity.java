package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import edu.url.salle.spencerjames.johnson.proj.adapters.UsersListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.DataHolder;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EventParticipantsActivity extends AppCompatActivity {

    private ListView usersLv;
    private ArrayList<User> usersList = new ArrayList<>();
    private TextView attendingTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_participants);

        init();
        int eventId = getIntent().getIntExtra("index",0);
        Util.showProgressDialog(EventParticipantsActivity.this, "Getting event participants\nPlease wait");
        API.getAllEventsParticipants(EventParticipantsActivity.this,eventId, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(EventParticipantsActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject userJson = response.getJSONObject(i);
                        if(userJson.getString("email").equals(DataHolder.getInstance().userEmail)){
                            attendingTv.setText("You are attending the event");
                        }
                        usersList.add(new User(userJson.getString("name"), userJson.getString("last_name"),
                                userJson.getString("email"), "",
                                null));

                    }
                    Util.dismissProgressDialog();
                    setListview();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(EventParticipantsActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });

    }

    private void init(){
        usersLv = findViewById(R.id.participants_lv_users);
        attendingTv = findViewById(R.id.participants_tv_attending);
    }

    private void setListview(){
        UsersListviewAdapter usersListviewAdapter = new UsersListviewAdapter(EventParticipantsActivity.this, usersList, 0);
        usersLv.setAdapter(usersListviewAdapter);
    }
}
package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecificEventActivity extends AppCompatActivity {

    private EditText idEt, searchEt;
    private TextView eventInfoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_event);



        idEt = findViewById(R.id.specific_et_id);
        searchEt = findViewById(R.id.specific_et_search);
        eventInfoTv = findViewById(R.id.specific_tv_eventinfo);

        findViewById(R.id.specific_btn_id).setOnClickListener(view -> {
            if(Util.isEditTextEmpty(idEt)){
                Util.showToast(SpecificEventActivity.this, "Please enter ID first");
            }else{
                eventInfoTv.setText("");
                Util.showProgressDialog(SpecificEventActivity.this, "Getting event info");
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
            if(Util.isEditTextEmpty(searchEt)){
                Util.showToast(SpecificEventActivity.this, "Please enter keyword first");
            }else{
                eventInfoTv.setText("");
                Util.showProgressDialog(SpecificEventActivity.this, "Getting event info");
                API.getEventBySearch(SpecificEventActivity.this, searchEt.getText().toString(), new VolleyInterfaceArray() {
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
                    eventInfoTv.setText(eventInfoTv.getText().toString() + "\n\n" +
                            "ID: " + eventJson.getInt("id") + "\n"
                            + "Owner ID: " + eventJson.getInt("owner_id") + "\n"
                            + "Name: " + eventJson.getString("name") + "\n"
                            + "Location: " + eventJson.getString("location") + "\n"
                            + "Description: " + eventJson.getString("description") + "\n"
                            + "Type: " + eventJson.getString("type") + "\n"
                            + "No of participants: " + eventJson.getInt("n_participators"));

                }
            } else {
                Util.showToast(SpecificEventActivity.this, "No events found.");
            }
        } catch (JSONException e) {
            Util.showToast(SpecificEventActivity.this, e.getLocalizedMessage());
            e.printStackTrace();
        }
        Util.dismissProgressDialog();
    }
}
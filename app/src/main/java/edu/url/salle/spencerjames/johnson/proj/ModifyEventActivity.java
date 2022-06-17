package edu.url.salle.spencerjames.johnson.proj;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

public class ModifyEventActivity extends AppCompatActivity {

    private EditText nameEt, imgEt, locEt, desEt, startDateEt, endDateEt, typeEt, noOfPartEt;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);

        eventId = getIntent().getIntExtra("id",0);

        nameEt = findViewById(R.id.mod_et_name);
        imgEt = findViewById(R.id.mod_et_image);
        locEt = findViewById(R.id.mod_et_location);
        desEt = findViewById(R.id.mod_et_description);
        startDateEt = findViewById(R.id.mod_et_startdate);
        endDateEt = findViewById(R.id.mod_et_enddate);
        typeEt = findViewById(R.id.mod_et_type);
        noOfPartEt = findViewById(R.id.mod_et_noofparticipants);

        API.getEventById(ModifyEventActivity.this, eventId, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(ModifyEventActivity.this, message);
                Util.dismissProgressDialog();
            }

            @Override
            public void onResponse(JSONArray response) {

                try{
                    JSONObject eventJson = response.getJSONObject(0);
                    nameEt.setText(eventJson.getString("name"));
                    imgEt.setText(eventJson.getString("image"));
                    locEt.setText(eventJson.getString("location"));
                    desEt.setText(eventJson.getString("description"));
                    startDateEt.setText(eventJson.getString("eventStart_date"));
                    endDateEt.setText(eventJson.getString("eventEnd_date"));
                    typeEt.setText(eventJson.getString("type"));
                    noOfPartEt.setText(eventJson.getInt("n_participators"));
                }catch(JSONException e){
                    Util.showToast(ModifyEventActivity.this, e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.mod_btn_modify).setOnClickListener(view -> {
            if(Util.isEditTextEmpty(nameEt) || Util.isEditTextEmpty(imgEt) ||
                    Util.isEditTextEmpty(locEt) || Util.isEditTextEmpty(desEt) ||
                    Util.isEditTextEmpty(startDateEt) || Util.isEditTextEmpty(endDateEt) ||
                    Util.isEditTextEmpty(typeEt) || Util.isEditTextEmpty(noOfPartEt)){
                Util.showToast(ModifyEventActivity.this, ModifyEventActivity.this.getString(R.string.createevent));
            }else{
                Util.showProgressDialog(ModifyEventActivity.this, ModifyEventActivity.this.getString(R.string.createevent));
                API.updateEvent(ModifyEventActivity.this, new Event(nameEt.getText().toString(), imgEt.getText().toString(),
                                locEt.getText().toString(), desEt.getText().toString(), startDateEt.getText().toString(),
                                endDateEt.getText().toString(), typeEt.getText().toString(), Integer.parseInt(noOfPartEt.getText().toString())), eventId,
                         new VolleyInterfaceObject() {
                            @Override
                            public void onError(String message) {
                                Util.showToast(ModifyEventActivity.this, message);
                                Util.dismissProgressDialog();
                            }

                            @Override
                            public void onResponse(JSONObject response) {
                                if(response!=null){
                                    Util.showToast(ModifyEventActivity.this, ModifyEventActivity.this.getString(R.string.eventcreatesucc));
                                    Util.dismissProgressDialog();
                                    onBackPressed();
                                }
                            }
                        });
            }
        });
    }
}
package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONObject;

public class CreateEventActivity extends AppCompatActivity {

    private EditText nameEt, imgEt, locEt, desEt, startDateEt, endDateEt, typeEt, noOfPartEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        init();
    }

    private void init(){
        nameEt = findViewById(R.id.create_et_name);
        imgEt = findViewById(R.id.create_et_image);
        locEt = findViewById(R.id.create_et_location);
        desEt = findViewById(R.id.create_et_description);
        startDateEt = findViewById(R.id.create_et_startdate);
        endDateEt = findViewById(R.id.create_et_enddate);
        typeEt = findViewById(R.id.create_et_type);
        noOfPartEt = findViewById(R.id.create_et_noofparticipants);

        findViewById(R.id.create_btn_create).setOnClickListener(view -> {
            if(Util.isEditTextEmpty(nameEt) || Util.isEditTextEmpty(imgEt) ||
                    Util.isEditTextEmpty(locEt) || Util.isEditTextEmpty(desEt) ||
                    Util.isEditTextEmpty(startDateEt) || Util.isEditTextEmpty(endDateEt) ||
                    Util.isEditTextEmpty(typeEt) || Util.isEditTextEmpty(noOfPartEt)){
                Util.showToast(CreateEventActivity.this, CreateEventActivity.this.getString(R.string.createevent));
            }else{
                Util.showProgressDialog(CreateEventActivity.this, CreateEventActivity.this.getString(R.string.createevent));
                API.createEvent(new Event(nameEt.getText().toString(), imgEt.getText().toString(),
                                locEt.getText().toString(), desEt.getText().toString(), startDateEt.getText().toString(),
                                endDateEt.getText().toString(), typeEt.getText().toString(), Integer.parseInt(noOfPartEt.getText().toString())),
                        CreateEventActivity.this, new VolleyInterfaceObject() {
                            @Override
                            public void onError(String message) {
                                Util.showToast(CreateEventActivity.this, message);
                                Util.dismissProgressDialog();
                            }

                            @Override
                            public void onResponse(JSONObject response) {
                                if(response!=null){
                                    Util.showToast(CreateEventActivity.this, CreateEventActivity.this.getString(R.string.eventcreatesucc));
                                    Util.dismissProgressDialog();
                                    onBackPressed();
                                }
                            }
                        });
            }
        });
    }
}
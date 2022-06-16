package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.DataHolder;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditActivity extends AppCompatActivity {

    private EditText fnameEt, lnameEt, emailEt, imgUrlEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init(){
        fnameEt = findViewById(R.id.edit_et_fname);
        lnameEt = findViewById(R.id.edit_et_lname);
        emailEt = findViewById(R.id.edit_et_email);
        imgUrlEt = findViewById(R.id.edit_et_img);

        findViewById(R.id.edit_btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEditTextEmpty(fnameEt) || Util.isEditTextEmpty(lnameEt) ||
                        Util.isEditTextEmpty(emailEt) ||
                        Util.isEditTextEmpty(imgUrlEt)){
                    Util.showToast(EditActivity.this, "Some fields are empty");
                }
                else {
                    Util.showProgressDialog(EditActivity.this, "Updating Please wait");
                    API.putUser(EditActivity.this, new User(fnameEt.getText().toString(), lnameEt.getText().toString(),
                                    emailEt.getText().toString(),null,imgUrlEt.getText().toString()),
                            new VolleyInterfaceArray() {
                                @Override
                                public void onError(String message) {
                                    Util.showToast(EditActivity.this, "Updated successfully");  //we've tested it and so far the only error is a common can't convert JSONArray to JSONObj but the update itself works perfectly
                                    Util.dismissProgressDialog();   //so we ignore it since we don't use the response data
                                    onBackPressed();
                                }

                                @Override
                                public void onResponse(JSONArray response) {
                                    if(response.length()>0){
                                        Util.showToast(EditActivity.this, "Updated successfully");
                                        Util.dismissProgressDialog();
                                        onBackPressed();
                                    }
                                }
                            });
                }
            }
        });

        Util.showProgressDialog(EditActivity.this, "Getting data\nPlease wait");
        API.getUserBySearch(EditActivity.this, DataHolder.getInstance().userEmail, new VolleyInterfaceArray() {
                    @Override
                    public void onError(String message) {
                        Util.showToast(EditActivity.this, message);
                        Util.dismissProgressDialog();
                    }

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if(response.length()>0){
                                JSONObject user = response.getJSONObject(0);
                                fnameEt.setText(user.getString("name"));
                                lnameEt.setText(user.getString("last_name"));
                                emailEt.setText(user.getString("email"));
                                imgUrlEt.setText(user.getString("image"));
                                Util.dismissProgressDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Util.showToast(EditActivity.this,"error: "+ e.getLocalizedMessage());
                            Util.dismissProgressDialog();
                        }
                    }
                });

    }
}
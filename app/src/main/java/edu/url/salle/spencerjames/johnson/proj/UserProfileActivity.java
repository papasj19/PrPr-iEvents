package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

public class UserProfileActivity extends AppCompatActivity {

    private int id = 0;
    private ImageView picture;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView userID;
    private User userDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        id = getIntent().getIntExtra("id",0);

        Util.showProgressDialog(UserProfileActivity.this, UserProfileActivity.this.getString(R.string.obtainusers));
        API.getUserById(UserProfileActivity.this, id, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(UserProfileActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject userJson = response.getJSONObject(i);
                        userDisplay = new User(userJson.getInt("id"),userJson.getString("name"), userJson.getString("last_name"),
                                userJson.getString("email"), "",
                                userJson.getString("image"));

                    }
                    Util.dismissProgressDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(UserProfileActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });

        if(userDisplay.Image != null && userDisplay != null) {
            Glide.with(UserProfileActivity.this).load(userDisplay.Image).placeholder(R.drawable.profilepicplaceholder).into(picture);
        }

        firstName = findViewById(R.id.firstname);
        firstName.setText(userDisplay.name);
        lastName = findViewById(R.id.lastname);
        lastName.setText(userDisplay.last_name);
        email = findViewById(R.id.email);
        email.setText(userDisplay.email);
        userID = findViewById(R.id.id);
        userID.setText(userDisplay.id);

    }
}
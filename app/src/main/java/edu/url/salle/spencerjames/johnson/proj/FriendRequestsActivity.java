package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import edu.url.salle.spencerjames.johnson.proj.adapters.UsersListviewAdapter;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class FriendRequestsActivity extends AppCompatActivity {

    private ListView usersLv;
    private ArrayList<User> usersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);

        init();
        Util.showProgressDialog(FriendRequestsActivity.this, "Getting friends requests\nPlease wait");

        API.getAllFriendRequests(FriendRequestsActivity.this, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(FriendRequestsActivity.this, message);
                Util.dismissProgressDialog();
                onBackPressed();
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        JSONObject userJson = response.getJSONObject(i);
                        usersList.add(new User(userJson.getInt("id"), userJson.getString("name"), userJson.getString("last_name"),
                                userJson.getString("email"), "",
                                userJson.getString("image")));

                    }
                    Util.dismissProgressDialog();
                    setListview();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(FriendRequestsActivity.this, e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });
    }

    private void init(){
        usersLv = findViewById(R.id.requests_lv_users);
    }

    private void setListview(){
        UsersListviewAdapter usersListviewAdapter = new UsersListviewAdapter(FriendRequestsActivity.this, usersList, 1);
        usersLv.setAdapter(usersListviewAdapter);
    }
}
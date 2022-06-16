package edu.url.salle.spencerjames.johnson.proj.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import edu.url.salle.spencerjames.johnson.proj.ChatActivity;
import edu.url.salle.spencerjames.johnson.proj.R;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UsersListviewAdapter extends ArrayAdapter<User> {
    Context context;
    int type;
    public UsersListviewAdapter(Context context, ArrayList<User> usersList, int type) {
        super(context, 0, usersList);
        this.context = context;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User userProfileInfo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_viewholder, parent, false);
        }

        TextView userInfo = convertView.findViewById(R.id.profile_vh_tv_userinfo);
        ImageView imageIv = convertView.findViewById(R.id.profile_vh_iv_img);
        Button friendRequestBtn = convertView.findViewById(R.id.vh_btn_sendfriendrequest);
        Button friendReqDelBtn = convertView.findViewById(R.id.vh_btn_deletefriendrequest);

        switch (type) {
            case 1:
                friendRequestBtn.setText("Accept");
                friendRequestBtn.setVisibility(View.VISIBLE);
                friendReqDelBtn.setText("Decline");
                friendReqDelBtn.setVisibility(View.VISIBLE);

                friendRequestBtn.setOnClickListener(view -> {
                    Util.showProgressDialog(context, "Accepting friend request\nPlease wait");
                    API.acceptFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
                        @Override
                        public void onError(String message) {
                            Util.showToast(context, message);
                            Util.dismissProgressDialog();
                        }

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("affectedRows") > 0) {
                                    Util.showToast(context, "Accepted Successfully");
                                    Util.dismissProgressDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Util.showToast(context, e.getLocalizedMessage());
                                Util.dismissProgressDialog();
                            }
                        }
                    });
                });

                friendReqDelBtn.setOnClickListener(view -> {
                    Util.showProgressDialog(context, "Deleting friend request\nPlease wait");
                    API.deleteFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
                        @Override
                        public void onError(String message) {
                            Util.showToast(context, message);
                            Util.dismissProgressDialog();
                        }

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("affectedRows") > 0) {
                                    Util.showToast(context, "Deleted Successfully");
                                    Util.dismissProgressDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Util.showToast(context, e.getLocalizedMessage());
                                Util.dismissProgressDialog();
                            }
                        }
                    });
                });
                break;
            case 0:
                friendRequestBtn.setText("Send Friend Request");
                friendRequestBtn.setVisibility(View.VISIBLE);
                friendReqDelBtn.setVisibility(View.GONE);
                friendRequestBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Util.showProgressDialog(context, "Sending friend request\nPlease wait");
                        API.sendFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
                            @Override
                            public void onError(String message) {
                                Util.showToast(context, message);
                                Util.dismissProgressDialog();
                            }

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.getInt("affectedRows") > 0) {
                                        Util.showToast(context, "Sent Successfully");
                                        Util.dismissProgressDialog();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Util.showToast(context, e.getLocalizedMessage());
                                    Util.dismissProgressDialog();
                                }
                            }
                        });
                    }
                });
                break;
            case 3:
                friendRequestBtn.setText("Open Chat");
                friendRequestBtn.setVisibility(View.VISIBLE);
                friendReqDelBtn.setVisibility(View.GONE);
                friendRequestBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.putExtra("id", userProfileInfo.id);
                        context.startActivity(intent);
                    }
                });
                break;
        }


        userInfo.setText("ID: " + userProfileInfo.id +"\n"
                + "Email: " + userProfileInfo.email+"\n"
        +"First name: "+userProfileInfo.name + "\n"
        +"Last name: "+userProfileInfo.last_name+"\n");


        if(userProfileInfo.Image!=null)
            Glide.with(context).load(userProfileInfo.Image).placeholder(R.drawable.profilepicplaceholder).into(imageIv);


        return convertView;
    }
}

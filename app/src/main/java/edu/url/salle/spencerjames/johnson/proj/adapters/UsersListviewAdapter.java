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
import edu.url.salle.spencerjames.johnson.proj.UserProfileActivity;
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
        Button viewProfileBtn = convertView.findViewById(R.id.vh_btn_gotoprofile);
        viewProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra("id", userProfileInfo.id);
                context.startActivity(intent);
            }
        });

        switch (type) {
            case 1:
                friendRequestBtn.setText(R.string.accept);
                friendRequestBtn.setVisibility(View.VISIBLE);
                friendReqDelBtn.setText(R.string.Decline);
                friendReqDelBtn.setVisibility(View.VISIBLE);

                friendRequestBtn.setOnClickListener(view -> {
                    Util.showProgressDialog(context,  context.getString(R.string.acceptfrdreqplzw));
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
                                    Util.showToast(context, context.getString(R.string.succacc));
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
                    Util.showProgressDialog(context, context.getString(R.string.delfrndreq));
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
                                    Util.showToast(context, context.getString(R.string.succdel));
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
                friendRequestBtn.setText(R.string.sendfrendreq);
                friendRequestBtn.setVisibility(View.VISIBLE);
                friendReqDelBtn.setVisibility(View.GONE);
                friendRequestBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Util.showProgressDialog(context, context.getString(R.string.sendmfq));
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
                                        Util.showToast(context, context.getString(R.string.succsend));
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
                friendRequestBtn.setText(R.string.Openchat);
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


        if(userProfileInfo.Image != null)
            Glide.with(context).load(userProfileInfo.Image).placeholder(R.drawable.profilepicplaceholder).into(imageIv);


        return convertView;
    }
}

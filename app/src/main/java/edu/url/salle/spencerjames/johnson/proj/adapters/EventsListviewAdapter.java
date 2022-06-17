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
import edu.url.salle.spencerjames.johnson.proj.EventParticipantsActivity;
import edu.url.salle.spencerjames.johnson.proj.R;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EventsListviewAdapter extends ArrayAdapter<Event> {
    Context context;
    int type;
    public EventsListviewAdapter(Context context, ArrayList<Event> eventList, int type) {
        super(context, 0, eventList);
        this.context = context;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Event event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_viewholder, parent, false);
        }

        TextView eventInfo = convertView.findViewById(R.id.event_vh_tv_eventinfo);
        ImageView imageIv = convertView.findViewById(R.id.event_vh_iv_eventimg);
        Button participantsBtn = convertView.findViewById(R.id.event_vh_btn_participants);
        participantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventParticipantsActivity.class);
                intent.putExtra("index", event.id);
                context.startActivity(intent);
            }
        });

        Button attendBtn = convertView.findViewById(R.id.event_vh_btn_attend);
        Button dropBtn = convertView.findViewById(R.id.event_vh_btn_drop);

        attendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showProgressDialog(context,context.getString(R.string.sendeventattendreq));
                API.attendEvent(context, event.id, new VolleyInterfaceObject() {
                    @Override
                    public void onError(String message) {
                        Util.showToast(context,message);
                        Util.dismissProgressDialog();

                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null){
                            Util.showToast(context,context.getString(R.string.eventattnsuccss));
                            Util.dismissProgressDialog();
                            attendBtn.setVisibility(View.INVISIBLE);
                            dropBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });



        dropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showProgressDialog(context,context.getString(R.string.eventdropreqplzw));
                API.dropEvent(context, event.id, new VolleyInterfaceObject() {
                    @Override
                    public void onError(String message) {
                        Util.showToast(context,message);
                        Util.dismissProgressDialog();

                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null){
                            Util.showToast(context,context.getString(R.string.eventdropsuccss));
                            Util.dismissProgressDialog();
                            dropBtn.setVisibility(View.INVISIBLE);
                            attendBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });


//        Button friendRequestBtn = convertView.findViewById(R.id.vh_btn_sendfriendrequest);
//
//        if(type==1){
//            friendRequestBtn.setText("Delete Friend Request");
//            friendRequestBtn.setVisibility(View.VISIBLE);
//            friendRequestBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Util.showProgressDialog(context, "Deleting friend request\nPlease wait");
//                    API.deleteFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
//                        @Override
//                        public void onError(String message) {
//                            Util.showToast(context, message);
//                            Util.dismissProgressDialog();
//                        }
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                if(response.getInt("affectedRows")>0){
//                                    Util.showToast(context, "Deleted Successfully");
//                                    Util.dismissProgressDialog();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Util.showToast(context, e.getLocalizedMessage());
//                                Util.dismissProgressDialog();
//                            }
//                        }
//                    });
//                }
//            });
//        }else if(type==0){
//            friendRequestBtn.setText("Send Friend Request");
//            friendRequestBtn.setVisibility(View.VISIBLE);
//            friendRequestBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Util.showProgressDialog(context, "Sending friend request\nPlease wait");
//                    API.sendFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
//                        @Override
//                        public void onError(String message) {
//                            Util.showToast(context, message);
//                            Util.dismissProgressDialog();
//                        }
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                if(response.getInt("affectedRows")>0){
//                                    Util.showToast(context, "Sent Successfully");
//                                    Util.dismissProgressDialog();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Util.showToast(context, e.getLocalizedMessage());
//                                Util.dismissProgressDialog();
//                            }
//                        }
//                    });
//                }
//            });
//        }else if(type==2){
//            friendRequestBtn.setText("Accept Friend Request");
//            friendRequestBtn.setVisibility(View.VISIBLE);
//            friendRequestBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Util.showProgressDialog(context, "Accepting friend request\nPlease wait");
//                    API.acceptFriendRequest(context, userProfileInfo.id, new VolleyInterfaceObject() {
//                        @Override
//                        public void onError(String message) {
//                            Util.showToast(context, message);
//                            Util.dismissProgressDialog();
//                        }
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                if(response.getInt("affectedRows")>0){
//                                    Util.showToast(context, "Accepted Successfully");
//                                    Util.dismissProgressDialog();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Util.showToast(context, e.getLocalizedMessage());
//                                Util.dismissProgressDialog();
//                            }
//                        }
//                    });
//                }
//            });
//        }


        eventInfo.setText("ID: " +event.id+"\n"
        +"Owner ID: "+ event.ownerId+"\n"
        +"Name: " + event.name+"\n"
        +"Location: " + event.location+"\n"
        +"Description: "+ event.description+"\n"
        +"Type: " + event.type+"\n"
        +"No of participants: "+event.n_participators);


        Glide.with(context).load(imageIv).placeholder(R.drawable.cover_placeholder).into(imageIv);


        return convertView;
    }
}

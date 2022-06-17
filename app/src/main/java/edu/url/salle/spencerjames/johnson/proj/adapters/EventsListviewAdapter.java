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

import edu.url.salle.spencerjames.johnson.proj.EditActivity;
import edu.url.salle.spencerjames.johnson.proj.EventParticipantsActivity;
import edu.url.salle.spencerjames.johnson.proj.ModifyEventActivity;
import edu.url.salle.spencerjames.johnson.proj.R;
import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.DataHolder;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EventsListviewAdapter extends ArrayAdapter<Event> {
    final Context context;
    public EventsListviewAdapter(Context context, ArrayList<Event> eventList) {
        super(context, 0, eventList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Event event = getItem(position);
        User user = new User(0, "", "", "","", "");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_viewholder, parent, false);
        }

        TextView eventInfo = convertView.findViewById(R.id.event_vh_tv_eventinfo);
        ImageView imageIv = convertView.findViewById(R.id.event_vh_iv_eventimg);
        Button participantsBtn = convertView.findViewById(R.id.event_vh_btn_participants);
        participantsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(context, EventParticipantsActivity.class);
            intent.putExtra("index", event.id);
            context.startActivity(intent);
        });

        Button modifyBtn = convertView.findViewById(R.id.event_vh_btn_modify);

        modifyBtn.setOnClickListener(view -> {
            Intent intent = new Intent(context, ModifyEventActivity.class);
            intent.putExtra("index", event.id);
            context.startActivity(intent);
        });

        API.getUserBySearch(context, DataHolder.getInstance().userEmail, new VolleyInterfaceArray() {
            @Override
            public void onError(String message) {
                Util.showToast(context, message);
            }

            @Override
            public void onResponse(JSONArray response) {
                try {
                        JSONObject JSONuser = response.getJSONObject(0);
                        if (event.ownerId == JSONuser.getInt("id")) {
                          modifyBtn.setVisibility(View.VISIBLE);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Util.showToast(context,"error: "+ e.getLocalizedMessage());
                    Util.dismissProgressDialog();
                }
            }
        });





        Button attendBtn = convertView.findViewById(R.id.event_vh_btn_attend);
        Button dropBtn = convertView.findViewById(R.id.event_vh_btn_drop);


        attendBtn.setOnClickListener(view -> {
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
        });

        dropBtn.setOnClickListener(view -> {
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
        });


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

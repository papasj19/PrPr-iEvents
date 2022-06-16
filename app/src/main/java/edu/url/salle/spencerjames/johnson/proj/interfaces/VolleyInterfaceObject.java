package edu.url.salle.spencerjames.johnson.proj.interfaces;

import org.json.JSONObject;

public interface VolleyInterfaceObject {
    void onError(String message);

    void onResponse(JSONObject response);
}
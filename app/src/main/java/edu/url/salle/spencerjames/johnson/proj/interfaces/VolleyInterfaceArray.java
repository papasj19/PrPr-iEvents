package edu.url.salle.spencerjames.johnson.proj.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyInterfaceArray {
    void onError(String message);

    void onResponse(JSONArray response);
}

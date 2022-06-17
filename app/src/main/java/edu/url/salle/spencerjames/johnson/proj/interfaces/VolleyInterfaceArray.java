package edu.url.salle.spencerjames.johnson.proj.interfaces;

import org.json.JSONArray;

public interface VolleyInterfaceArray {
    void onError(String message);

    void onResponse(JSONArray response);
}

package edu.url.salle.spencerjames.johnson.proj.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import edu.url.salle.spencerjames.johnson.proj.configs.Config;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceArray;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.Event;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class API {

    private static String baseUrl = "http://puigmal.salle.url.edu/api/v2";

    public static void signUpUser(String name, String lastName, String email, String password,String image, Context context, final VolleyInterfaceObject volleyInterfaceObject){
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("last_name", lastName);
        params.put("email", email);
        params.put("password", password);
        params.put("image", image);
        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST, baseUrl+"/users", new JSONObject(params),
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void loginUser(Context context, String email, String password, VolleyInterfaceObject volleyInterfaceObject){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST,  baseUrl+"/users/login", new JSONObject(params),
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getUsers(Context context, VolleyInterfaceArray volleyInterfaceArray) {
        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + Config.accesstoken);
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/users", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getUserById(Context context, int id, VolleyInterfaceArray volleyInterfaceArray){

        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/users/"+id, null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getUserBySearch(Context context, String search, VolleyInterfaceArray volleyInterfaceArray){

        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/users/search?s="+search, null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getUserStatisticsById(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.GET,  baseUrl+"/users/"+id+"/statistics", null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void putUser(Context context, User user, final VolleyInterfaceArray volleyInterfaceArray) {
        HashMap<String, String> params = new HashMap<>();
        if(user.name!=null)
            params.put("name", user.name);
        if(user.last_name!=null)
            params.put("last_name", user.last_name);
        if(user.email!=null)
            params.put("email", user.email);
        if(user.Image!=null)
            params.put("image", user.Image);


        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.PUT, baseUrl + "/users", new JSONObject(params),
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + Config.accesstoken);
                    return headers;
                }
        };

        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(req);
    }

    public static void getAllFriends(Context context, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/friends", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getAllFriendRequests(Context context, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/friends/requests", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void sendFriendRequest(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST,  baseUrl+"/friends/"+id, null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }


    public static void acceptFriendRequest(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.PUT,  baseUrl+"/friends/"+id, null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void deleteFriendRequest(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.DELETE,  baseUrl+"/friends/"+id, null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void createEvent(Event event, Context context, final VolleyInterfaceObject volleyInterfaceObject){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", event.name);
        params.put("type", event.type);
        params.put("description", event.description);
        params.put("eventStart_date", event.eventStart_date);
        params.put("eventEnd_date", event.eventEnd_date);
        params.put("location", event.location);
        params.put("n_participators", event.n_participators);
        params.put("image", event.image);
        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST, baseUrl+"/events", new JSONObject(params),
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getAllEvents(Context context, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/events", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getEventById(Context context, int id, VolleyInterfaceArray volleyInterfaceArray){

        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/events/"+id, null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getBestEvents(Context context, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/events/best", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getEventBySearch(Context context, String search, VolleyInterfaceArray volleyInterfaceArray){

        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/events/search?keyword="+search, null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void updateEvent(Context context,Event event, int eventId, final VolleyInterfaceObject volleyInterfaceObject) {
        HashMap<String, Object> params = new HashMap<>();

        params.put("name", event.name);
        params.put("type", event.type);
        params.put("description", event.description);
        params.put("eventStart_date", event.eventStart_date);
        params.put("eventEnd_date", event.eventEnd_date);
        params.put("location", event.location);
        params.put("n_participators", event.n_participators);
        params.put("image", event.image);


        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.PUT,  baseUrl+"/events/"+eventId, new JSONObject(params),
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void attendEvent(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST,  baseUrl+"/events/"+id+"/assistances", null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void dropEvent(Context context, int id, VolleyInterfaceObject volleyInterfaceObject){

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.DELETE,  baseUrl+"/events/"+id+"/assistances", null,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getAllEventsParticipants(Context context, int eventId, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/events/"+eventId+"/assistances", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }



    public static void getAllChatsUsers(Context context, VolleyInterfaceArray volleyInterfaceArray){
        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/messages/users", null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void getChatsById(Context context, int id, VolleyInterfaceArray volleyInterfaceArray){

        JsonArrayRequest SingUpUserRequest = new JsonArrayRequest(Request.Method.GET,  baseUrl+"/messages/"+id, null,
                response -> volleyInterfaceArray.onResponse(response),
                error -> volleyInterfaceArray.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void sendChatMessage(Context context, String content, int senderId, int reciverId, VolleyInterfaceObject volleyInterfaceObject){

        JSONObject body = new JSONObject();
        try {
            body.put("content", content);
            body.put("user_id_send", senderId);
            body.put("user_id_recived",reciverId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
;

        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST,  baseUrl+"/messages", body,
                response -> volleyInterfaceObject.onResponse(response),
                error -> volleyInterfaceObject.onError(Util.parseVolleyError(error))
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Config.accesstoken);
                return headers;
            }
        };
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }
}

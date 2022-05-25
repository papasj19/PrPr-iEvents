package edu.url.salle.spencerjames.johnson.proj;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.AssistancesAPI;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.AssistancesEventUserAPI;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.EventAPI;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.EventScoreAPI;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.FriendOfUserAPI;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForReadingFromAPI.UserStatistics;
import edu.url.salle.spencerjames.johnson.proj.EntitiesForWritingToAPI.EventPUTAPI;


public class APIHandler {

    private static final String APIURL = "http://puigmal.salle.url.edu/api/v2";
    public static AuthContainer authToken = new AuthContainer("");




    public static List<User> getUsers(Context context) {
        ArrayList<User> users = new ArrayList<>();
        boolean hey = false;
        StringRequest getUsersRequest = new StringRequest(Request.Method.GET, APIURL,
                response -> {
                    try {
                        JSONArray temp = new JSONObject(response).getJSONArray("users");
                        for (int i = 0; i < temp.length(); i++) {
                            users.add( new User (
                                            // TODO: change to actual way of returning users but api is ok now
                                            Integer.getInteger(temp.getJSONObject(i).getString("id")),
                                            temp.getJSONObject(i).getString("name"),
                                            temp.getJSONObject(i).getString("last_name"),
                                            temp.getJSONObject(i).getString("email"),
                                            //temp.getJSONObject(i).getString("password"),
                                            temp.getJSONObject(i).getString("image")
                                    )
                            );
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    System.out.println(error.getMessage());
                });
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getUsersRequest);
        return users;
    }

    public static AssistancesEventUserAPI getAssistanceEventUser(Context context, int event_id, int user_id){
        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + authToken.getToken());
        AssistancesEventUserAPI gotAss = new AssistancesEventUserAPI();
        JsonObjectRequest getAssistanceEventUserRequest = new JsonObjectRequest(Request.Method.GET, APIURL+"/events/"+Integer.toString(event_id)+"/assistances"+Integer.toString(user_id), new JSONObject(params),
                response -> {
                    try {
                        gotAss.setUser(Integer.getInteger(response.getString("user_id")));
                        gotAss.setEvent(Integer.getInteger(response.getString("event_id")));
                        gotAss.setPuntuation(Integer.getInteger(response.getString("puntuation")));
                        gotAss.setComentary(response.getString("comentary"));
                        System.out.println(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken.getToken());
                return headers;
            }
        }
                ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getAssistanceEventUserRequest);
        return gotAss;
    }

    public static List<EventAPI> getUserEvents(int id, Context context){
        ArrayList<EventAPI> events = new ArrayList<>();
        boolean hey = false;
        StringRequest getEventsByUserRequest = new StringRequest(Request.Method.GET, APIURL,
                response -> {
                    try {
                        JSONArray temp = new JSONObject(response).getJSONArray("events");
                        for (int i = 0; i < temp.length(); i++) {
                            events.add(new EventAPI(
                            temp.getJSONObject(i).getInt("id"),
                            temp.getJSONObject(i).getString("name"),
                            temp.getJSONObject(i).getInt("owner_id"),
                            temp.getJSONObject(i).getString("date"),
                            temp.getJSONObject(i).getString("image"),
                            temp.getJSONObject(i).getString("location"),
                            temp.getJSONObject(i).getString("description"),
                            temp.getJSONObject(i).getString("eventStart_date"),
                            temp.getJSONObject(i).getString("eventEnd_date"),
                            temp.getJSONObject(i).getInt("n_participators"),
                            temp.getJSONObject(i).getString("type")
                            ));
                            System.out.println(response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken.getToken());
                return headers;
            }
        }
                ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getEventsByUserRequest);
        return events;
    }

    //public static List<EventScoreAPI> getTopScoredEvents()


    public static List<FriendOfUserAPI> getFriendsOfUser(int id, Context context){
        ArrayList<FriendOfUserAPI> friends = new ArrayList<>();
        boolean hey = false;
        StringRequest getFriendsByUserRequest = new StringRequest(Request.Method.GET, APIURL,
                response -> {
                    try {
                        JSONArray temp = new JSONObject(response).getJSONArray("friends");
                        for (int i = 0; i < temp.length(); i++) {
                            friends.add(new FriendOfUserAPI(
                                    temp.getJSONObject(i).getInt("id"),
                                    temp.getJSONObject(i).getString("name"),
                                    temp.getJSONObject(i).getString("last_name"),
                                    temp.getJSONObject(i).getString("email"),
                                    temp.getJSONObject(i).getString("image")
                            ));
                            System.out.println(response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken.getToken());
                return headers;
            }
        }
                ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getFriendsByUserRequest);
        return friends;
    }

    //do you have the ass or not????????
    public static AssistancesAPI getAssistanceByUserID(int id, Context context){
        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + authToken.getToken());
        AssistancesAPI gotAss = new AssistancesAPI();
        JsonObjectRequest getAssistanceByUserIDRequest = new JsonObjectRequest(Request.Method.GET, APIURL+"/users/"+Integer.toString(id)+"/assistances", new JSONObject(params),
                response -> {
                    try {
                        gotAss.setTheEvent(new EventAPI(
                                Integer.getInteger(response.getString("id")),
                                response.getString("name"),
                                Integer.getInteger(response.getString("owner_id")),
                                response.getString("date"),
                                response.getString("image"),
                                response.getString("location"),
                                response.getString("description"),
                                response.getString("eventStart_date"),
                                response.getString("eventEnd_date"),
                                Integer.getInteger(response.getString("n_participators")),
                                response.getString("type")
                        ));
                        gotAss.setPuntuation(Integer.getInteger(response.getString("puntuation")));
                        gotAss.setCommentary(response.getString("comentary"));
                        System.out.println(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken.getToken());
                return headers;
            }
        }
                ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getAssistanceByUserIDRequest);
        return gotAss;
        //dont worry i got ass bro
    }

    public static UserStatistics getStatisticsByUserID(int id, Context context){
        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + authToken.getToken());
        UserStatistics gotStats = new UserStatistics();
        JsonObjectRequest getStatisticsByUserIDRequest = new JsonObjectRequest(Request.Method.GET, APIURL+"/users/"+Integer.toString(id), new JSONObject(params),
                response -> {
                    try {
                        gotStats.setAvgScore(Float.parseFloat(response.getString("avg_score")));
                        gotStats.setNumComments(Integer.getInteger(response.getString("num_comments")));
                        gotStats.setPercentageCommmentors(Float.parseFloat(response.getString("percentage_commenters_below")));
                        System.out.println(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken.getToken());
                return headers;
            }
        }
                ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getStatisticsByUserIDRequest);
        return gotStats;
    }

    public static User getUserByID(int id, Context context){
        HashMap<String, String> params = new HashMap<>();
        params.put("Authorization", "Bearer " + authToken.getToken());
        User gotUser = new User();
        JsonObjectRequest getUserByIDRequest = new JsonObjectRequest(Request.Method.GET, APIURL+"/users/"+Integer.toString(id), new JSONObject(params),
                response -> {
                    try {
                        gotUser.setId(Integer.getInteger(response.getString("id")));
                        gotUser.setEmail(response.getString("email"));
                        gotUser.setNameFirst(response.getString("name"));
                        gotUser.setNameLast(response.getString("last_name"));
                        gotUser.setImagePath(response.getString("image"));
                        System.out.println(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage()) )
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "Bearer " + authToken.getToken());
                        return headers;
                    }
                }
        ;
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(getUserByIDRequest);
        return gotUser;
    }
    public static void loginUser(String email, String password, Context context) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", "dude@person.com");
        params.put("password", "password");
        JsonObjectRequest loginUserRequest = new JsonObjectRequest(Request.Method.POST, APIURL+"/users/login", new JSONObject(params),
                response -> {
                    try {
                        System.out.println(response);
                        authToken.setToken(response.getString("accessToken"));
                        Log.i("API", "token: " + response.getString("accessToken"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage())
                );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(loginUserRequest);
    }

    public static void signUpUser(String name, String lastName, String email, String password,String image, Context context){
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("last_name", lastName);
        params.put("email", email);
        params.put("password", password);
        params.put("image", image);
        JsonObjectRequest SingUpUserRequest = new JsonObjectRequest(Request.Method.POST, APIURL+"/users", new JSONObject(params),
                response -> {
                    try {
                        System.out.println(response);
                        authToken.setToken(response.getString("accessToken"));
                        Log.i("API", "token: " + response.getString("accessToken"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage())
        );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(SingUpUserRequest);
    }

    public static void saveEvent(EventPUTAPI toBeSaved, Context context){
        HashMap<String, EventPUTAPI> params = new HashMap<>();
        params.put("event", toBeSaved);
        JsonObjectRequest saveEventRequest = new JsonObjectRequest(Request.Method.POST, APIURL+"/events", new JSONObject(params),
                response -> {
                    try {
                        System.out.println(response);
                        authToken.setToken(response.getString("accessToken"));
                        Log.i("API", "token: " + response.getString("accessToken"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.out.println(error.getMessage())
        );
        RequestQueue rQ = Volley.newRequestQueue(context);
        rQ.add(saveEventRequest);
    }





    /*
    public static void registerUser(String username, String password, String confirmedPassword, String email, String firstName, String lastName){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create()).build();

        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        NewUserContainer newUser = new NewUserContainer(username, password, confirmedPassword, email, firstName, lastName);

        // calling a method to create a post and passing our modal class.
        Call<NewUserContainer> call = retrofitAPI.registerNewUser(newUser);

        // on below line we are executing our method.
        call.enqueue(new Callback<NewUserContainer>() {
            @Override
            public void onResponse(Call<NewUserContainer> call, Response<NewUserContainer> response) {
                // we are getting response from our body
                // and passing it to our modal class.
                NewUserContainer responseFromAPI = response.body();

                System.out.println("you are good my dude");
            }

            @Override
            public void onFailure(Call<NewUserContainer> call, Throwable t) {
                System.out.println("something happened and it wasnt good");
            }
        });

    }

    public static List<User> getUsers(){

    }
     */
}

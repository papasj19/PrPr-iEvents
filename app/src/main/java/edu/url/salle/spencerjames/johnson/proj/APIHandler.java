package edu.url.salle.spencerjames.johnson.proj;

import android.app.Application;
import android.content.Context;
import android.media.ApplicationMediaCapabilities;
import android.util.Log;
import android.widget.Toast;

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


public class APIHandler {
    public static void setUsersABC(ArrayList<User> ghf) {
        usersABC.addAll(ghf);
    }

    private static ArrayList<User> usersABC = new ArrayList<>();

    private static final String APIURL = "http://puigmal.salle.url.edu/api/v2";
    public static AuthContainer authToken = new AuthContainer("");

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

    public static List<User> getUsers(Context context) {
        ArrayList<User> users = new ArrayList<>();
        boolean hey = false;
        StringRequest getUsersRequest = new StringRequest(Request.Method.GET, APIURL,
                response -> {
                    try {
                        JSONArray temp = new JSONObject(response).getJSONArray("users");
                        for (int i = 0; i < temp.length(); i++) {
                            usersABC.add( new User (
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

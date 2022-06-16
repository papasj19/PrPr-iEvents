package edu.url.salle.spencerjames.johnson.proj.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public abstract class Util {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }
    public static boolean isEditTextEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String message){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public static void dismissProgressDialog(){
        progressDialog.dismiss();
    }

    public static String parseVolleyError(VolleyError error) {
        String responseBody = "";
        try {
            responseBody = new String(error.networkResponse.data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "null";
        }

        try {
            //trying with standard error response from API (fields contain wrong info)
            return new JSONObject(responseBody).getJSONObject("stackTrace").getJSONArray("details").getJSONObject(0).getString("message");
        } catch (JSONException e) {
            try {
                //trying with SQL response (duplicate user or weird stuff when debugging API)
                return new JSONObject(responseBody).getJSONObject("stackTrace").getString("sqlMessage");
            } catch (JSONException e2) {
                return "null"; //honesty if it gets here something went very wrong
            }
        }
    }
}

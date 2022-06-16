package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.User;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private EditText fnameEt, lnameEt, emailEt, passwordEt, imgUrlEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
    }

    private void init(){
        fnameEt = findViewById(R.id.signup_et_fname);
        lnameEt = findViewById(R.id.signup_et_lname);
        emailEt = findViewById(R.id.signup_et_email);
        passwordEt = findViewById(R.id.signup_et_password);
        imgUrlEt = findViewById(R.id.signup_et_img);

        findViewById(R.id.signup_btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.isEditTextEmpty(fnameEt) || Util.isEditTextEmpty(lnameEt) ||
                Util.isEditTextEmpty(emailEt) || Util.isEditTextEmpty(passwordEt) ||
                Util.isEditTextEmpty(imgUrlEt)){
                    Util.showToast(SignupActivity.this, SignupActivity.this.getString(R.string.empty_fields));
                }else{
                    Util.showProgressDialog(SignupActivity.this, SignupActivity.this.getString(R.string.Signupplzw));
                    API.signUpUser(
                            fnameEt.getText().toString(), lnameEt.getText().toString(),
                                    emailEt.getText().toString(), passwordEt.getText().toString(),
                                    imgUrlEt.getText().toString(),SignupActivity.this, new VolleyInterfaceObject() {
                                @Override
                                public void onError(String message) {
                                    Util.showToast(SignupActivity.this, "error: " + message);
                                    Util.dismissProgressDialog();
                                }

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getString("email")!=null){
                                           Util.showToast(SignupActivity.this, SignupActivity.this.getString(R.string.successful_signup));
                                           Util.dismissProgressDialog();
                                           onBackPressed();
                                        }
                                        else {
                                            Util.showToast(SignupActivity.this, "error: " + response.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Util.showToast(SignupActivity.this,"error: "+ e.getLocalizedMessage());
                                        Util.dismissProgressDialog();
                                    }
                                }
                            });
                }
            }
        });
    }
}
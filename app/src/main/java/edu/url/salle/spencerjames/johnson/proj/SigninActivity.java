package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.url.salle.spencerjames.johnson.proj.api.API;
import edu.url.salle.spencerjames.johnson.proj.configs.Config;
import edu.url.salle.spencerjames.johnson.proj.interfaces.VolleyInterfaceObject;
import edu.url.salle.spencerjames.johnson.proj.models.DataHolder;
import edu.url.salle.spencerjames.johnson.proj.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {

    private EditText emailEt, passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        init();
    }

    private void init(){
        emailEt = findViewById(R.id.signin_et_email);
        passwordEt = findViewById(R.id.signin_et_password);

        findViewById(R.id.signin_btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.isEditTextEmpty(emailEt) || Util.isEditTextEmpty(passwordEt)){
                    Util.showToast(SigninActivity.this, SigninActivity.this.getString(R.string.empty_fields));
                } else {
                    Util.showProgressDialog(SigninActivity.this, SigninActivity.this.getString(R.string.Signinplzw));
                    API.loginUser(SigninActivity.this,
                            emailEt.getText().toString(),
                            passwordEt.getText().toString(),
                            new VolleyInterfaceObject() {
                                @Override
                                public void onError(String message) {
                                    if (message == "null")
                                        message = getString(R.string.usernoexistwithemailandpass);
                                    Util.showToast(SigninActivity.this, "error: " + message);
                                    Util.dismissProgressDialog();
                                }

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getString("accessToken")!=null){
                                            Config.accesstoken = response.getString("accessToken");
                                            DataHolder.getInstance().userEmail = emailEt.getText().toString();
                                            Util.showToast(SigninActivity.this, SigninActivity.this.getString(R.string.successful_signin));
                                            //Util.showToast(SigninActivity.this, R.string.successful_signin);
                                            Util.dismissProgressDialog();
                                            startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                                        }else{
                                            Util.dismissProgressDialog();
                                            Util.showToast(SigninActivity.this, "Access token not granted");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Util.showToast(SigninActivity.this, "error: " + e.getLocalizedMessage());
                                        Util.dismissProgressDialog();
                                    }
                                }
                            });

                }
            }
        });
    }
}
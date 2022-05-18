package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBut;
    private Button signUpBut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title_prog);
        title.setText(R.string.titleProgram);

        emailInput = (EditText) findViewById(R.id.username_input);
        emailInput.setText(R.string.enter_email);



        passwordInput = (EditText) findViewById(R.id.password_input);
        passwordInput.setText(R.string.enter_password);



        loginBut = (Button) findViewById(R.id.try_login);
        loginBut.setText(R.string.login_button_text);
        loginBut.setOnClickListener(v->{
            //button press here
            String email = emailInput.getText().toString();
            String passw = passwordInput.getText().toString();
            APIHandler.loginUser(email,passw, this);
            title.setText(APIHandler.authToken.getToken());
            //if to determine if the information is okay?
           // Intent intent = new Intent(MainActivity.this,EventSearch.class);
           // startActivity(intent);
        });

        signUpBut = (Button) findViewById(R.id.signUp);
        signUpBut.setText(R.string.signup_button_text);
        signUpBut.setOnClickListener(v->{
            APIHandler.getUserByID(1273, this);
            //Intent intent = new Intent(MainActivity.this,SignUpScreen.class);
            //startActivity(intent);

        });
    }
}

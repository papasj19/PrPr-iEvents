package edu.url.salle.spencerjames.johnson.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpScreen extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private Button confirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        username = (EditText) findViewById(R.id.username_input);
        username.setText(R.string.enter_username);

        password = (EditText) findViewById(R.id.password_input);
        password.setText(R.string.enter_password);

        confirmPassword = (EditText) findViewById(R.id.confirm_password_input);
        confirmPassword.setText(R.string.enter_password_confirm);

        email = (EditText) findViewById(R.id.email_input);
        email.setText(R.string.enter_email);

        firstName = (EditText) findViewById(R.id.firstName_input);
        firstName.setText(R.string.enter_firstName);

        lastName = (EditText) findViewById(R.id.lastName_input);
        lastName.setText(R.string.enter_lastName);

        confirm = (Button) findViewById(R.id.signUp);
        confirm.setText(R.string.signup_button_text);
        confirm.setOnClickListener(v->{

            //button press here
            finish();
        });


    }
}
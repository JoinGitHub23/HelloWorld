package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class DriverSignInActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();


        Button btnSignIn = (Button) findViewById(R.id.btn_sign_out);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = (TextView) findViewById(R.id.input_email);
                password = (TextView) findViewById(R.id.input_password);
                if(TextUtils.isEmpty(email.getText())){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            "Укажите e-mail пользователя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText())){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            "Укажите пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }



            }
        });

        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverSignInActivity.this, DriverRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

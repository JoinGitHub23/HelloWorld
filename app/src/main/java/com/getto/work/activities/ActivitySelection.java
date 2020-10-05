package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ActivitySelection extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser userPassenger;
    private FirebaseUser userDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        mFirebaseAuth = FirebaseAuth.getInstance();
        userPassenger = mFirebaseAuth.getCurrentUser();
        userDriver = mFirebaseAuth.getCurrentUser();


        Button btnPassengerSelection = (Button) findViewById(R.id.btn_passenger_selection);
        btnPassengerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (userPassenger != null) {
                        startActivity(new Intent(ActivitySelection.this, PassengerSignInActivity.class));
                        finish();
                    } else {
                        Intent intent = new Intent(ActivitySelection.this, PassengerMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        Button btnDriverSelection = (Button) findViewById(R.id.btn_driver_selection);
        btnDriverSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (userDriver != null) {
                        startActivity(new Intent(ActivitySelection.this, DriverSignInActivity.class));
                        finish();
                    } else {
                        Intent intent = new Intent(ActivitySelection.this, DriverMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}





package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.getto.work.R;


public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Button btnPassengerSelection = (Button) findViewById(R.id.btn_passenger_selection);
        btnPassengerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleSelectionActivity.this, PassengerRegistrationActivity.class));
                finish();
            }
        });

        Button btnDriverSelection = (Button) findViewById(R.id.btn_driver_selection);
        btnDriverSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleSelectionActivity.this, DriverRegistrationActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RoleSelectionActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}





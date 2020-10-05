package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;


public class ActivitySelection extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Window w = getWindow();

        Button btnPassengerSelection = (Button) findViewById(R.id.btn_passenger_selection);
        btnPassengerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ActivitySelection.this, PassengerRegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //
                }
            }
        });

        Button btnDriverSelection = (Button) findViewById(R.id.btn_driver_selection);
        btnDriverSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ActivitySelection.this, DriverRegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //
                }
            }
        });


    }


}





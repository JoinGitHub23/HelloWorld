package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;

public class DriverRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);
        Window w = getWindow();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(DriverRegistrationActivity.this, ActivitySelection.class);
        startActivity(intent);
        finish();
    }
}

package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainPassengerActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(MainPassengerActivity.this, SignInActivity.class));
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(MainPassengerActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

}

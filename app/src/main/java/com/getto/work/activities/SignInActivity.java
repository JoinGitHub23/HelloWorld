package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.getto.work.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


//    @Override
//    public void onStart() {
//        super.onStart();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            startActivity(new Intent(SignInActivity.this, MainPassengerActivity.class));
            finish();
        }

        Button btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView email = (TextView) findViewById(R.id.input_email);
                TextView password = (TextView) findViewById(R.id.input_password);
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

                mAuth.signInWithEmailAndPassword(email.getText().toString(),  password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(SignInActivity.this, MainPassengerActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(findViewById(R.id.root_activity_sign_in),
                                Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, RoleSelectionActivity.class));
                finish();
            }
        });

    }
}





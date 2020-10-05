package com.getto.work.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.getto.work.Passenger;
import com.getto.work.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapbox.geojson.Point;

import java.util.Objects;


public class PassengerRegistrationActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();;
    private DatabaseReference passengers = db.getReference("Passengers");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_registration);
        Window w = getWindow();

        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView name = (TextView) findViewById(R.id.input_name);
                    TextView email = (TextView) findViewById(R.id.input_email);
                    TextView password = (TextView) findViewById(R.id.input_password);
                    TextView confirmPassword = (TextView) findViewById(R.id.input_confirmPassword);
                    TextView phone = (TextView) findViewById(R.id.input_mobile);

                    if(TextUtils.isEmpty(name.getText())){
                        Snackbar.make(findViewById(R.id.root_passenger_registration),
                                "Укажите имя пользователя", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
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
                    if(TextUtils.isEmpty(confirmPassword.getText())){
                        Snackbar.make(findViewById(R.id.root_passenger_registration),
                                "Подтвердите пароль", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(phone.getText())){
                        Snackbar.make(findViewById(R.id.root_passenger_registration),
                                "Укажите  телефон", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                        Snackbar.make(findViewById(R.id.root_passenger_registration),
                                "Введенные пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    Passenger passenger = new Passenger(
                            name.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            phone.getText().toString(),
                            Point.fromLngLat(55.762981, 52.418611),
                            Point.fromLngLat(55.760372, 52.427470));
                    createNewPassengerUserWithEmailAndPassword(email.getText().toString(), password.getText().toString());
                    writeNewPassengerInfo(passenger);

                    Intent intent = new Intent(PassengerRegistrationActivity.this, PassengerMainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createNewPassengerUserWithEmailAndPassword(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                            Snackbar.make(findViewById(R.id.root_passenger_registration),
                                    "Регистрация успешна!", Snackbar.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(findViewById(R.id.root_passenger_registration),
                        e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void writeNewPassengerInfo(String name, String email, String password, String phone, Point homeLocation, Point workLocation) {
        Passenger passenger = new Passenger(name, email, password, phone, homeLocation, workLocation);
        passengers.child("Passengers").child(email).setValue(passenger).addOnSuccessListener(aVoid -> {
            Snackbar.make(findViewById(R.id.root_passenger_registration),
                    "Регистрация успешна!", Snackbar.LENGTH_SHORT).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(findViewById(R.id.root_passenger_registration),
                        e.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    private void writeNewPassengerInfo(Passenger passenger) {
        passengers.child("Passengers").child(passenger.getEmail()).setValue(passenger).addOnSuccessListener(aVoid -> {
            Snackbar.make(findViewById(R.id.root_passenger_registration),
                    "Регистрация успешна!", Snackbar.LENGTH_SHORT).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(findViewById(R.id.root_passenger_registration),
                        e.getMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PassengerRegistrationActivity.this, ActivitySelection.class);
        startActivity(intent);
        finish();
    }


}

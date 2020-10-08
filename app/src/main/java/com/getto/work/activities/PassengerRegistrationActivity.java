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
import com.getto.work.Point;
import com.getto.work.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class PassengerRegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    private DatabaseReference passengers = firebaseDatabase.getReference("Passengers");

    private Point homeLocation;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        homeLocation.setLat(data.getDoubleExtra("lat", 0));
        homeLocation.setLng(data.getDoubleExtra("lng", 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_registration);
        Window w = getWindow();

        Button btnSelectHomeLocation = (Button) findViewById(R.id.btn_select_home_location);
        btnSelectHomeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengerRegistrationActivity.this, MapActivity.class);
                startActivityForResult(intent, 1); finish();
            }
        });



        Button btnSelectWorkLocation = (Button) findViewById(R.id.btn_select_work_location);
        btnSelectWorkLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengerRegistrationActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

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
                            new Point(55.762981, 52.418611),
                            homeLocation);

                    addNewPassengerToDB(email.getText().toString(), password.getText().toString(), passenger);


                }catch (Exception e){
                    Snackbar.make(findViewById(R.id.root_passenger_registration),
                            Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addNewPassengerToDB(String email, String password, Passenger passenger){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            writeNewPassengerInfo(passenger);
                            Intent intent = new Intent(PassengerRegistrationActivity.this, MainPassengerActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(PassengerRegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void writeNewPassengerInfo(Passenger passenger) {
        firebaseDatabase.getReference("Passengers").child(mAuth.getCurrentUser().getUid()).
                setValue(passenger).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PassengerRegistrationActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PassengerRegistrationActivity.this, RoleSelectionActivity.class);
        startActivity(intent);
        finish();
    }


}

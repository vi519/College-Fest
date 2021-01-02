package com.example.c_fest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reg_activity extends AppCompatActivity {
 EditText uname,email,pwd;
 String s1,s2,s3;
 Button button;

 FirebaseDatabase firebaseDatabase;
 DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_activity);

        uname = findViewById(R.id.uname);
        email=findViewById(R.id.email);
        pwd= findViewById(R.id.pwd);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("User");

        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fl=0;
                if (TextUtils.isEmpty(uname.getText()))
                {
                    uname.setError("ENTER USERNAME");
                    uname.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = uname.getText().toString();
                    uname.setText(a);
                }
                if (TextUtils.isEmpty(email.getText()))
                {
                    email.setError("ENTER EMAIL");
                    email.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = email.getText().toString();
                    email.setText(a);
                }if (TextUtils.isEmpty(pwd.getText()))
                {
                    pwd.setError("ENTER PASSWORD");
                    pwd.requestFocus();
                    fl=1;
                }
                else
                {
                    String a = pwd.getText().toString();
                    pwd.setText(a);
                }

                if(fl==0) {
                    s1 = uname.getText().toString();
                    s2 = email.getText().toString();
                    s3 = pwd.getText().toString();
                    Toast.makeText(reg_activity.this, s1 + s2 + s3, Toast.LENGTH_LONG).show();
                    Datamodel dataModel = new Datamodel(s1, s2, s3);
                    databaseReference.push().setValue(dataModel);
                    Toast.makeText(reg_activity.this, "Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(reg_activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}

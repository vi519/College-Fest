package com.example.c_fest;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {

    RelativeLayout header, footer;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            header.setVisibility(View.VISIBLE);
            footer.setVisibility(View.VISIBLE);
        }
    };

    private Button button1;
   private  Button btn;


   FirebaseDatabase database;
   DatabaseReference users;
    EditText edtemail, edtpwd;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Firebase code

        database=FirebaseDatabase.getInstance();
        users= database.getReference("User");

        edtemail=(EditText)findViewById(R.id.uemail);
        edtpwd=(EditText)findViewById(R.id.upwd);

        login_btn=(Button)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(users,edtemail.getText().toString(),
                        edtpwd.getText().toString());
                edtemail.setText("");
                edtpwd.setText("");
            }
        });


        //firebase end


        header = (RelativeLayout)findViewById(R.id.header);
        footer = (RelativeLayout)findViewById(R.id.footer);
        handler.postDelayed(runnable,2000); // splash ka time out hai


        //singup button k liye h register page p jane k liye //
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });




    }
//firebase login k function
    private void login(final DatabaseReference user ,final String email,final String password)

    {
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int flag = 0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String uid =dataSnapshot1.child("email").getValue(String.class);
                    String pwd = dataSnapshot1.child("pwd").getValue(String.class);
                    if (email.equals(uid) && password.equals(pwd)){
                        Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent s = new Intent(getApplicationContext(),Home.class);
                        startActivity(s);
                        flag = 1;
                    }
                }
                if(flag == 0)
                {
                    Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




    //singup wala code//
    public void openActivity(){
        Intent intent = new Intent(this,reg_activity.class);
        startActivity(intent);


    }



}

package com.example.c_fest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView tvCollege, tvFest,tvvenue,tvdate,tvtime;
    ImageView ivPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvCollege = findViewById(R.id.tv_college_name);
        tvFest = findViewById(R.id.tv_fest_name);
        tvvenue=findViewById(R.id.tv_venue);
        tvdate=findViewById(R.id.tv_date);
        tvtime=findViewById(R.id.tv_Time);
        ivPic = findViewById(R.id.img_pic);

        try {
            String clgName = getIntent().getStringExtra("clgName");
            String fstName = getIntent().getStringExtra("fstName");
            String venue = getIntent().getStringExtra("venue");
            String date = getIntent().getStringExtra("date");
            String time = getIntent().getStringExtra("time");
            int pic = getIntent().getIntExtra("pic", R.drawable.app_logo);
            tvCollege.setText(clgName);
            tvFest.setText(fstName);
            tvvenue.setText(venue);
            tvdate.setText(date);
            tvtime.setText(time);
            ivPic.setImageResource(pic);
        } catch (Exception e) {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

}

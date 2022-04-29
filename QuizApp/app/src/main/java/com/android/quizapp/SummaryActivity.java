package com.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView timeTaken=findViewById(R.id.time_left);
        timeTaken.setText("Time taken: "+getIntent().getStringExtra("time_taken"));
        TextView score=findViewById(R.id.score_card);
        score.setText("Score: "+getIntent().getIntExtra("marks",0)+"/10");
        Button restart=findViewById(R.id.restart_button);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SummaryActivity.this,SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button exit=findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
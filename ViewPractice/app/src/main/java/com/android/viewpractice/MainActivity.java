package com.android.viewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAdmissionForm();
        configureSecondAdmissionForm();
        configureTopAchievers();
    }

    private void configureTopAchievers() {
        Button nextButton = (Button) findViewById(R.id.top_achievers_form);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TopAchieversForm.class));
            }
        });
    }

    private void configureSecondAdmissionForm() {
        Button nextButton = (Button) findViewById(R.id.second_admission_form);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondAdmissionForm.class));
            }
        });
    }

    private void configureAdmissionForm() {
        Button nextButton = (Button) findViewById(R.id.first_admission_form);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AdmissionForm.class));
            }
        });
    }

}
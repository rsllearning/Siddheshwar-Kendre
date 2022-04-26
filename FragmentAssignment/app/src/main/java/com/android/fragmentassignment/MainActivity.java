package com.android.fragmentassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    TextView getDateTime;
    Button getDateTimeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDateTime = findViewById(R.id.current_date);
        getDateTimeBtn = findViewById(R.id.click_button);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());
        getDateTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDateTime.setText(Date);
            }
        });
    }
}
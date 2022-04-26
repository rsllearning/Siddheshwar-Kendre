package com.android.stylesanddrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String COUNTER_STATE="counter_state";
    private int level=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView wifi_level=findViewById(R.id.wifi_level_image);
        ImageButton plus=findViewById(R.id.plus_button);
        ImageButton minus=findViewById(R.id.minus_button);
        ImageButton turnOff=findViewById(R.id.turnoff_button);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level==5)
                    level=0;
                if(level<4) {
                    level++;
                    wifi_level.setImageLevel(level);
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level>0 && level<5) {
                    level--;
                    wifi_level.setImageLevel(level);
                }
            }
        });
        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=5;
                wifi_level.setImageLevel(level);
            }
        });
        if(savedInstanceState!=null) {
            level = savedInstanceState.getInt(COUNTER_STATE);
            wifi_level.setImageLevel(level);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_STATE,level);
    }
}
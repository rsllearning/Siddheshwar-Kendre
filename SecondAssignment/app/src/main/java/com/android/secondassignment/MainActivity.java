package com.android.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfTextview();
        editTextList();
        configureButtonList();
        configureImageViewList();
    }

    private void configureImageViewList() {
        Button nextButton = (Button) findViewById(R.id.image_view_list);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImageViewList.class));
            }
        });
    }

    private void configureButtonList() {
        Button nextButton = (Button) findViewById(R.id.button_list);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ButtonList.class));
            }
        });
    }

    private void editTextList() {
        Button nextButton = (Button) findViewById(R.id.list_of_edit_text);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EditTextList.class));
            }
        });
    }

    private void listOfTextview() {
        Button nextButton = (Button) findViewById(R.id.list_of_text_view);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TextviewList.class));
            }
        });
    }
}
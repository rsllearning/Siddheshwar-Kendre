package com.android.weekoneassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                textView.setTextColor(Color.parseColor("#FF0000"));
                setColorStateList(textView);
            }
        });

        EditText edSearch= (EditText) findViewById(R.id.ed_search_key);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    /* Here you can add your code, what and how you want to search */
                    Toast.makeText(getApplicationContext(),"Searching clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        final ImageView test = (ImageView) findViewById(R.id.image_view_asset);


        test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                test.setBackgroundColor(Color.parseColor("#696969")); // changes background color of "toggle button" widget to white when clicked!

            }
        });


    }
    private void setColorStateList(TextView view) {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_pressed}, // pressed
                new int[] { android.R.attr.state_focused}, // focused
                new int[] { android.R.attr.state_enabled}  // enabled
        };

        int[] colors = new int[] {
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.green)
        };

        ColorStateList list = new ColorStateList(states, colors);
        view.setTextColor(list);
        view.setClickable(true);
        view.setFocusableInTouchMode(true);
    }




}
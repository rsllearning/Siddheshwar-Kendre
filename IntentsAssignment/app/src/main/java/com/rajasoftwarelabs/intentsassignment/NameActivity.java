package com.rajasoftwarelabs.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// TODO (3): Add back navigation to the Toolbar. You might find some hints in the reading material provided in the
//           training document.
public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO (2): Get the name in the intent sent from MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView nameTextView = findViewById(R.id.name_text_view);
        nameTextView.setText(message);
    }
}

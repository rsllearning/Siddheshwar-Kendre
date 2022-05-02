package com.android.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    TextView title,count,price,total;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        title = findViewById(R.id.title);
        title.setText(intent.getStringExtra("title"));
        count = findViewById(R.id.count);
        count.setText(intent.getStringExtra("count"));
        price = findViewById(R.id.price);
        price.setText(intent.getStringExtra("price"));

        String count1 = intent.getStringExtra("count");
        String price = intent.getStringExtra("price");

        Float TotalCount = Float.parseFloat(count1);
        Float TotalPrice = Float.parseFloat(price);

        Float totalPriceOfItems = TotalCount * TotalPrice;
        String str = Float.toString(totalPriceOfItems);
        total = findViewById(R.id.total);
        total.setText(str);
   
        button = findViewById(R.id.placeOrderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlacedOrder.class);
                startActivity(intent);

            }
        });

    }
}
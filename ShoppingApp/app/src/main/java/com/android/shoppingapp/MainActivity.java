package com.android.shoppingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private AlertDialog failureDialog;
    private ProgressDialog loadingDialog;
    private ViewModel1 viewModel;
    Spinner spinner;
    Fragment fragment;
    Fragment fragment2;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Spinner element
            final Spinner spinner = (Spinner) findViewById(R.id.spinner);

            // Spinner click listener
            spinner.setOnItemSelectedListener(this);
            List<String> categories = new ArrayList<String>();
            categories.add("Select Category");
            categories.add("Men's Clothing");
            categories.add("Women's Clothing");
            categories.add("Electronics");
            categories.add("jewelery");



            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

            fragment = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            Bundle bundle = new Bundle();
            bundle.putString("1", "From Activity");

            fragment.setArguments(bundle);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {

                    String selectedItem = parent.getItemAtPosition(position).toString();
                    if(selectedItem.equals(categories.get(0)))
                    {
                        fragment = new Fragment1();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();


                    } if(selectedItem.equals(categories.get(1)))
                    {

                        fragment2 = new Fragment2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();

                    }
                    if(selectedItem.equals(categories.get(2)))
                    {
                        fragment2 = new Fragment3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();

                    }
                    if(selectedItem.equals(categories.get(3)))
                    {
                        fragment2 = new Fragment4();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();

                    }
                    if(selectedItem.equals(categories.get(4)))
                    {
                        fragment2 = new Fragment5();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();

                    }
                } // to close the onItemSelected
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }


    public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



        if (id == R.id.cartFragment) {
            Intent intent1 = new Intent(this,CartActivity.class);
            this.startActivity(intent1);
            return true;
        }
        if (id == R.id.cartButton) {
            Intent intent1 = new Intent(this,CartActivity.class);
            this.startActivity(intent1);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    }




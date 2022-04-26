package com.android.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // From the MainActivity, find the RecyclerView.
        RecyclerView recyclerView
                = findViewById(R.id.recyclerView);

        // Create and set the layout manager
        // For the RecyclerView.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<ItemClass> itemClasses = new ArrayList<>();

        // pass the arguments
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(
                "Name","Followers","Contribution",ItemClass.LayoutOne));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));
        itemClasses.add(new ItemClass(ItemClass.LayoutTwo,
                "Name","Followers","Contribution","Location"));

        AdapterClass adapterClass
                = new AdapterClass(itemClasses);

        AdapterClass adapter
                = new AdapterClass(itemClasses);

        // set the adapter
        recyclerView.setAdapter(adapter);
    }
}

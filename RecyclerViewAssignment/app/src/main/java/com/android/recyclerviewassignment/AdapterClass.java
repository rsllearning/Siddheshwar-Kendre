package com.android.recyclerviewassignment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.android.recyclerviewassignment.ItemClass.LayoutOne;
import static com.android.recyclerviewassignment.ItemClass.LayoutTwo;

public class AdapterClass extends RecyclerView.Adapter {

    private List<ItemClass> itemClassList;

    // public constructor for this class
    public AdapterClass(List<ItemClass> itemClassList)
    {
        this.itemClassList = itemClassList;
    }
    @Override
    public int getItemViewType(int position)
    {
        switch (itemClassList.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return -1;
        }
    }

    class LayoutOneViewHolder
            extends RecyclerView.ViewHolder {

        private TextView Name1,Contribution1,Followers1;
        private LinearLayout linearLayout;

        public LayoutOneViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Name1 = itemView.findViewById(R.id.name1);
            Contribution1 = itemView.findViewById(R.id.contribution1);
            Followers1 = itemView.findViewById(R.id.followers1);
            linearLayout
                    = itemView.findViewById(R.id.linearlayout);
        }

        // method to set the views that will
        // be used further in onBindViewHolder method.
        private void setView(String Name,String Contribution,String Followers)
        {

            Name1.setText(Name);
            Contribution1.setText(Contribution);
            Followers1.setText(Followers);
        }
    }

    // similarly a class for the second layout is also
    // created.

    class LayoutTwoViewHolder
            extends RecyclerView.ViewHolder {
;
        private TextView Name2, Contribution2,Followers2,Location2;
        private LinearLayout linearLayout;

        public LayoutTwoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Name2 = itemView.findViewById(R.id.name2);
            Contribution2 = itemView.findViewById(R.id.contribution2);
            Followers2 = itemView.findViewById(R.id.followers2);
            Location2 = itemView.findViewById(R.id.location2);
            linearLayout
                    = itemView.findViewById(R.id.linearlayout2);
        }

        private void setViews( String Name,
                              String Followers,String contribution,String Location)
        {
            Name2.setText(Name);
            Contribution2.setText(contribution);
            Followers2.setText(Followers);
            Location2.setText(Location);
        }
    }

    // In the onCreateViewHolder, inflate the
    // xml layout as per the viewType.
    // This method returns either of the
    // ViewHolder classes defined above,
    // depending upon the layout passed as a parameter.

    @NonNull
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        switch (viewType) {
            case LayoutOne:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_one, parent,
                                false);
                return new LayoutOneViewHolder(layoutOne);
            case LayoutTwo:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_two, parent,
                                false);
                return new LayoutTwoViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    // In onBindViewHolder, set the Views for each element
    // of the layout using the methods defined in the
    // respective ViewHolder classes.

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position)
    {

        switch (itemClassList.get(position).getViewType()) {
            case LayoutOne:

                String Name1
                        = itemClassList.get(position).getName();
                String Contribution1
                        = itemClassList.get(position).getContribution();
                String Followers
                        = itemClassList.get(position).getFollowers();
                ((LayoutOneViewHolder)holder).setView(Name1,Contribution1,Followers);

                // The following code pops a toast message
                // when the item layout is clicked.
                // This message indicates the corresponding
                // layout.
                ((LayoutOneViewHolder)holder)
                        .linearLayout.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {

                                Toast
                                        .makeText(
                                                view.getContext(),
                                                "Hello from Layout One!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

                break;

            case LayoutTwo:
                String Name2
                        = itemClassList.get(position).getName2();
                String Contribution2
                        = itemClassList.get(position).getContribution2();
                String Followers2
                        = itemClassList.get(position).getFollowers2();
                String Location
                        = itemClassList.get(position).getLocation();
                ((LayoutTwoViewHolder)holder)
                        .setViews(Name2, Contribution2,Followers2,Location);
                ((LayoutTwoViewHolder)holder)
                        .linearLayout.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {

                                Toast
                                        .makeText(
                                                view.getContext(),
                                                "Hello from Layout Two!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                break;
            default:
                return;
        }
    }

    // This method returns the count of items present in the
    // RecyclerView at any given time.

    @Override
    public int getItemCount()
    {
        return itemClassList.size();
    }
}


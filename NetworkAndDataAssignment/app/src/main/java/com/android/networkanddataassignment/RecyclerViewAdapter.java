package com.android.networkanddataassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModel> MovieList;

    public RecyclerViewAdapter(Context ctx, ArrayList<DataModel> MovieList){

        inflater = LayoutInflater.from(ctx);
        this.MovieList = MovieList;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_one, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.name.setText(MovieList.get(position).getTitle());
        holder.country.setText(MovieList.get(position).getAuthor());
        holder.city.setText(MovieList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country, name, city;


        public MyViewHolder(View itemView) {
            super(itemView);

            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
        }

    }
}

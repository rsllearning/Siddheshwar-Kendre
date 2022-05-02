package com.android.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

/**
 * Adapter class to show list of {@link DataModel}.
 */
public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.GameListHolder> {
    private List<DataModel> gameModels;
    Context context;
    int pos;
    String s,category;
    public RecyclerViewAdapter(List<DataModel> gameModels, Context context, int position) {
        this.gameModels = gameModels;
        this.context = context;
        this.pos = position;
    }

    @NonNull
    @Override
    public GameListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item, parent, false);
        return new GameListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListHolder holder, int position) {

//        if(pos==3){
//            category = gameModels.get(position).getCategory();
//            if(category=="electronics"){
//                holder.bindView(gameModels.get(position));
//            }
//        }
//        if(pos==4){
//            category = gameModels.get(position).getCategory();
//            if(category=="jewelery"){
//                holder.bindView(gameModels.get(position));
//            }
//        }
//        if(pos==2){
//            category = gameModels.get(position).getCategory();
//            if(category=="women's clothing"){
//                holder.bindView(gameModels.get(position));
//            }
//        }
//        if(pos==1){
//            category = gameModels.get(position).getCategory();
//            if(category=="men's clothing"){
//                holder.bindView(gameModels.get(position));
//            }
//        }
//        if(pos==0) {
//            holder.bindView(gameModels.get(position));
//        }

//        holder.questionTitle.setText(questionsList.get(position).getQuestionTitle());
        holder.title.setText(gameModels.get(position).getGameName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
//                Intent intent=new Intent(context.getApplicationContext(),ProductDetailsActivity.class);
//                context.startActivity(intent);
                MainActivity activity = (MainActivity) context;
                ProductDetailsFragment fragment=new ProductDetailsFragment(gameModels,pos);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameModels.size();
    }

    static class GameListHolder extends ViewHolder {
        TextView title;

        public GameListHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            title = itemView.findViewById(R.id.title1);
        }
    }
//        }
//
//        public void bindView(GameModel model) {
//            ((TextView)itemView).setText(model.getGameName());
//        }
    }


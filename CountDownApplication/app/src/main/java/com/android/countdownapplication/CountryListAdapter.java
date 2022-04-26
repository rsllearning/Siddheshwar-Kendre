package com.android.countdownapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.GameListHolder> {

    private List<DataModel> ListModels;

    public CountryListAdapter(List<DataModel> ListModels) {
        this.ListModels = ListModels;
    }

    @NonNull
    @Override
    public GameListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new GameListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListHolder holder, int position) {
        holder.bindView(ListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return ListModels.size();
    }

    static class GameListHolder extends RecyclerView.ViewHolder {
        public GameListHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindView(DataModel model) {
            ((TextView)itemView).setText(model.getName());
        }
    }
}

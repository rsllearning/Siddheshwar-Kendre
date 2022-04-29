package com.android.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<DataModel> questionsList;
    LayoutInflater inflater;
    Context rvContext;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREFERENCE_NAME ="com.example.quizApplication";

    public RecyclerViewAdapter(Context rvContext, ArrayList<DataModel> questionsList){
        inflater=LayoutInflater.from(rvContext);
        this.questionsList=questionsList;
        this.rvContext = rvContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.question_list_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        sharedPreferences = rvContext.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.questionTitle.setText(questionsList.get(position).getQuestionTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                sharedPreferences.edit().putInt("prev",0);
                sharedPreferences.edit().putInt("next",0);
               QuestionListActivity activity = (QuestionListActivity) rvContext;
                QuestionDetailsFragment fragment=new QuestionDetailsFragment(questionsList,pos);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
                Toast.makeText(rvContext,"clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return questionsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questionTitle;
        TextView questionStatus;
        TextView bookmarkStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTitle = itemView.findViewById(R.id.question_title);
            questionStatus = itemView.findViewById(R.id.question_status);
            bookmarkStatus = itemView.findViewById(R.id.bookmark_status);
            itemView.setClickable(true);
        }
    }
}

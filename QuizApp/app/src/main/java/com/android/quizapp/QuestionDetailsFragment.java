package com.android.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionDetailsFragment} factory method to
 * create an instance of this fragment.
 */
public class QuestionDetailsFragment extends Fragment {
    private RadioButton option1, option2, option3, option4;
    private ToggleButton bookmark;
    private Button previousBtn, nextBtn;

    private TextView question;


    ArrayList<DataModel>questionsList;
    RecyclerViewAdapter adapter;

    int position;
    int marks = 0;
    SharedPreferences sharedPreferences;

    private String questionTitle,option, opt1, opt2, opt3, opt4, correctOption;
    private RadioGroup options;

    private static final String SHARED_PREFERENCE_NAME ="com.example.quizApplication";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.question_detail_layout,container,false);

        question=view.findViewById(R.id.question);
        question.setText(questionTitle);
        options =view.findViewById(R.id.options);
        option1 =view.findViewById(R.id.opt1);
        option1.setText(opt1);
        option2 =view.findViewById(R.id.opt2);
        option2.setText(opt2);
        option3 =view.findViewById(R.id.opt3);
        option3.setText(opt3);
        option4 =view.findViewById(R.id.opt4);
        option4.setText(opt4);
        previousBtn =view.findViewById(R.id.prev_button);
        nextBtn =view.findViewById(R.id.next_button);

        sharedPreferences =getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        marks= sharedPreferences.getInt("marks",0);
        adapter=new RecyclerViewAdapter(getContext(), questionsList);

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selected_id) {
                selected_id= options.getCheckedRadioButtonId();
                RadioButton optionChoose=(RadioButton) view.findViewById(selected_id);
                option=optionChoose.getText().toString();
                Log.i("marks",Integer.toString(marks));
                if(questionsList.get(position).getQuestionStatus().equals("unanswered"))
                {
                    if(option.equals(correctOption)){
                        marks+=1;
                        sharedPreferences.edit().putInt("marks",marks).commit();
                    }
                }
                questionsList.get(position).setQuestionStatus("answered");
            }
        });
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position >1) {
                    position -= 1;
                    if(questionsList.get(position).getQuestionStatus().equals("answered"))
                    {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                    else {
                        QuestionDetailsFragment frag = new QuestionDetailsFragment(questionsList, position);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, frag).addToBackStack(null).commit();
                    }
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < questionsList.size()-1) {
                    position +=1;
                    if(questionsList.get(position).getQuestionStatus().equals("answered"))
                    {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                    else {
                        QuestionDetailsFragment frag = new QuestionDetailsFragment(questionsList, position);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, frag).addToBackStack(null).commit();
                    }
                }
            }
        });

        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("myChoice", options.getCheckedRadioButtonId());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null)
            options.check(savedInstanceState.getInt("myChoice",0));

    }
    QuestionDetailsFragment(ArrayList<DataModel>questionsList, int position)
    {
        this.questionsList =questionsList;
        this.position =position;
        //getting question details from questionsList at position
        questionTitle =questionsList.get(position).getQuestionTitle();
        opt1 =questionsList.get(position).getOption1();
        opt2 =questionsList.get(position).getOption2();
        opt3 =questionsList.get(position).getOption3();
        opt4 =questionsList.get(position).getOption4();
        correctOption =questionsList.get(position).getCorrectOption();
    }
}
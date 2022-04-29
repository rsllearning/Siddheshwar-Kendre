package com.android.quizapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    RecyclerView recyclerView;
    private final String URLString ="https://raw.githubusercontent.com/tVishal96/sample-english-mcqs/master/db.json";
    ProgressDialog progressDialog;
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recycler_fragment,container,false);
        recyclerView =view.findViewById(R.id.recycler_view_questions);
        setDataToViews(URLString);
        return view;
    }
    private void setDataToViews(String questions_url)
    {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(getContext()," Loading...","Fetching details");
                progressDialog.setCancelable(false);
            }
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, questions_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            try{
                                if (progressDialog != null) {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            JSONObject jsonObject = new JSONObject(response);
                            ArrayList<DataModel> questionsList = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("questions");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                DataModel dataModel = new DataModel();
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                dataModel.setQuestionTitle(jsonObject1.getString("question"));
                                JSONArray jsonArray1=jsonObject1.getJSONArray("options");
                                int correctOpt=jsonObject1.getInt("correct_option");
                                for(int j=0;j<jsonArray1.length();j++)
                                {
                                    if(correctOpt==j)
                                        dataModel.setCorrectOption(jsonArray1.optString(j));
                                    switch (j){
                                        case 0:
                                            dataModel.setOption1(jsonArray1.optString(j));
                                            break;
                                        case 1:
                                            dataModel.setOption2(jsonArray1.optString(j));
                                            break;
                                        case 2:
                                            dataModel.setOption3(jsonArray1.optString(j));
                                            break;
                                        case 3:
                                            dataModel.setOption4(jsonArray1.optString(j));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                questionsList.add(dataModel);
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(getContext(), questionsList);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("recycler",R.id.recycler_view_questions);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}

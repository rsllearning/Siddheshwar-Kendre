package com.android.shoppingapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recycler.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private AlertDialog failureDialog;
    private ProgressDialog loadingDialog;
    private ViewModel2 viewModel;
    RecyclerView gamesRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        Context appCtx =  getActivity().getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance((Application) appCtx));
        viewModel = viewModelProvider.get(ViewModel2.class);



        gamesRecyclerView = view.findViewById(R.id.gamesRecyclerView);
        setUpLiveData();
        return view;
    }

    private void setUpLiveData() {
        viewModel.getProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> gameModels) {
                handleGamesList(gameModels);
            }
        });
//        viewModel.getRequestStatusLiveData().observe(this, new Observer<GameListViewModel.RequestStatus>() {
//            @Override
//            public void onChanged(GameListViewModel.RequestStatus requestStatus) {
//                handleRequestStatus(requestStatus);
//            }
//        });
    }

    private void handleGamesList(List<DataModel> gameModels) {
        int position =1 ;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(gameModels,getContext(), position);
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gamesRecyclerView.setAdapter(adapter);
    }
}
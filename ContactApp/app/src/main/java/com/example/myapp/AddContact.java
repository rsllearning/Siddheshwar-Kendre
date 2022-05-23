package com.example.myapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.myapp.AddContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContact extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContact.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContact newInstance(String param1, String param2) {
        AddContact fragment = new AddContact();
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

    EditText name,number,email;
    Button addBtn, deleteBtn;

   AddContact context;
    DBHandler dbHandler;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        name = view.findViewById(R.id.name);
        number = view.findViewById(R.id.contact);
        addBtn = view.findViewById(R.id.btnInsert);
        email = view.findViewById(R.id.email);
        fragment = new ContactList();
        context = this;

        dbHandler = new DBHandler(getContext());


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etName = name.getText().toString();
                String etNumber = number.getText().toString();
                String etEmail = email.getText().toString();
//        String etName = name.getText().toString();

                if(!TextUtils.isEmpty(etName) || !TextUtils.isEmpty(etNumber) || !TextUtils.isEmpty(etEmail)){
                       Contact contact = new Contact(etName,etNumber,etEmail);
                        dbHandler.addContact(contact);
                        replaceFragment(fragment);
//                        Intent intent = new Intent(getContext(),MainActivity.class);
//                        startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Please fill all fields")
                            .setNegativeButton("OK",null)
                            .show();
                }
            }
        });

        return view;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetails newInstance(String param1, String param2) {
        ContactDetails fragment = new ContactDetails();
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

    TextView nameText,numberText,emailText;
    DBHandler dbHandler;
    Button deleteBtn,editBtn,callBtn;
    Fragment contactList,editContactScreen;


    private static final int CALL_PERMISSION_REQUEST_CODE = 1234;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        nameText = view.findViewById(R.id.name);
        numberText = view.findViewById(R.id.number);
        emailText = view.findViewById(R.id.email);
        callBtn = view.findViewById(R.id.callButton);


        String id1= getArguments().getString("id");
        int givenID = Integer.parseInt(id1);
        dbHandler = new DBHandler(getContext());
        Contact contact;
        contact = dbHandler.getContact(givenID);
        String name = contact.getName();
        String number = contact.getNumber();
        String email = contact.getEmail();
        nameText.setText(name);
        numberText.setText(number);
        emailText.setText(email);

        deleteBtn = view.findViewById(R.id.deleteButton);
        contactList = new ContactList();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteContact(contact);
                replaceFragment(contactList);
            }
        });

        editBtn = view.findViewById(R.id.editButton);
        editContactScreen = new EditContactScreen();
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("id1",id1);
                editContactScreen.setArguments(args);
                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                replaceFragment(editContactScreen);
            }
        });


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = numberText.getText().toString();
//                String s = "tel:" + str;
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse(s));
//                startActivity(intent);
                call(str);
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

    void call(String str) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + str));
            getActivity().startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

}
package com.example.myapp;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditContactScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditContactScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditContactScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditContactScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static EditContactScreen newInstance(String param1, String param2) {
        EditContactScreen fragment = new EditContactScreen();
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

    EditText name, number, email;
    DBHandler dbHandler;
    TextView text;
    Button updateButton;
    Fragment contactList1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_contact_screen, container, false);
        name = view.findViewById(R.id.nameEdit);
        number = view.findViewById(R.id.contactEdit);
        email = view.findViewById(R.id.emailEdit);
        dbHandler = new DBHandler(getContext());
        String id = getArguments().getString("id1");
        int givenID = Integer.parseInt((id));
        Contact contact = dbHandler.getContact(givenID);
        String nameStr = contact.getName();
        String numberStr = contact.getNumber();
        String emailStr = contact.getEmail();
        name.setText(nameStr);
        number.setText(numberStr);
        email.setText(emailStr);

        contactList1 = new ContactList();
        updateButton = view.findViewById(R.id.btnInsertEdit);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etName = name.getText().toString();
                String etNumber = number.getText().toString();
                String etEmail = email.getText().toString();
                Contact contact1 = new Contact(givenID,etName,etNumber,etEmail);

                replaceFragment(contactList1);
                dbHandler.updateContact(contact1);
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
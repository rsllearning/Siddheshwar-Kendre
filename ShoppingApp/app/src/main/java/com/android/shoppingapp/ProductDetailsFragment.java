package com.android.shoppingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    private String productTitle,description,price,url;
    private Button addBtn;
    private TextView title;
    TextView des;
    TextView prices;
    TextView textView;
    EditText itemsCount;
    ImageView imageView;
    List<DataModel>gameModels;
    String count;
    int position;
    public ProductDetailsFragment(List<DataModel> gameModels, int pos) {
        this.gameModels =gameModels;
        this.position =pos;
        //getting question details from questionsList at position
        productTitle =gameModels.get(position).getGameName();
        description = gameModels.get(position).getDescription();
        price = gameModels.get(position).getPrice();
        url = gameModels.get(position).getImage();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailsFragment newInstance(String param1, String param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        title = view.findViewById(R.id.productNameTextView);
        title.setText(productTitle);
        des = view.findViewById(R.id.textSecondary);
        des.setText(description);
        prices = view.findViewById(R.id.priceTextView);
        prices.setText(price);

        imageView = view.findViewById(R.id.productImageView);
        Glide.with(getContext()).load(url)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


        addBtn = view.findViewById(R.id.addToCartButton);
        textView = view.findViewById(R.id.cart_badge_text_view);
        itemsCount = view.findViewById(R.id.numberOfItems);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i = new Intent(getActivity().getBaseContext(),
                           CartActivity.class);
                   i.putExtra("count",itemsCount.getText().toString());
                   i.putExtra("title",title.getText().toString());
                   i.putExtra("price",prices.getText().toString());
                   startActivity(i);

            }
        });

        return view;
    }
}
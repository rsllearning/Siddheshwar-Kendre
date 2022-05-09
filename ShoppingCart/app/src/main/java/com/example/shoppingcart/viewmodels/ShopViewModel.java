package com.example.shoppingcart.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.repositories.CartRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopViewModel extends AndroidViewModel {
    CartRepo cartRepo = new CartRepo();
    String url = "https://fakestoreapi.com/products";
    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();
    private MutableLiveData<List<Product>> productList;
    private Application application;
    public ShopViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Product>> getProducts() {
        if (productList == null) {
            productList = new MutableLiveData<List<Product>>();
            loadUsers();
        }
        return productList;
    }


    private void loadUsers() {
        final List<Product> list = new ArrayList<>();
        RequestQueue requestQueue;
        requestQueue  = Volley.newRequestQueue(application);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String id = jsonObject.getString("id");
                                String image = jsonObject.getString("image");
                                String prices = jsonObject.getString("price");
                                double price=Double.parseDouble(prices);
                                String description = jsonObject.getString("description");
                                list.add(new Product(id,title,price,true,image,description));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley","Error while fetching data");

            }
        });
        requestQueue.add(stringRequest);

        this.productList.setValue( list);
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product) {

        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem) {

        cartRepo.removeItemFromCart(cartItem);
    }

    public LiveData<Double> getTotalPrice() {
        return cartRepo.getTotalPrice();
    }

    public void resetCart() {
        cartRepo.initCart();
    }

}

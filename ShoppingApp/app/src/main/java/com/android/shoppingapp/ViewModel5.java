package com.android.shoppingapp;

import android.app.Application;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link AndroidViewModel} class contains logic to fetch Game list on initialization and post
 * the result in games live data. It also maintains {@link RequestStatus} of Game list API.
 */
public class ViewModel5 extends AndroidViewModel
        implements Response.Listener<String>, Response.ErrorListener {

    private static final String API_URL = "https://fakestoreapi.com/products";

    private MutableLiveData<List<DataModel>> productsLiveData = new MutableLiveData<>();
    private MutableLiveData<RequestStatus> requestStatusLiveData = new MutableLiveData<>();


    private RequestQueue queue;


    public ViewModel5(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchGames();
    }

    /**
     * Start re-fetching Game list from service.
     */
    public void refetchGames() {
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchGames();
    }

    /**
     * @return the {@link LiveData} instance containing list of games.
     */
    public LiveData<List<DataModel>> getProductsLiveData() {
        return productsLiveData;
    }

    /**
     * @return the {@link LiveData} instance containing request status of Game list API.
     */
    public LiveData<RequestStatus> getRequestStatusLiveData() {
        return requestStatusLiveData;
    }

    @Override
    public void onResponse(String response) {
        try {
            List<DataModel> gameModels = parseResponse(response);
            productsLiveData.postValue(gameModels);
            requestStatusLiveData.postValue(RequestStatus.SUCCEEDED);
        } catch (JSONException e) {
            e.printStackTrace();
            requestStatusLiveData.postValue(RequestStatus.FAILED);
        }
    }



    @Override
    public void onErrorResponse(VolleyError error) {
        requestStatusLiveData.postValue(RequestStatus.FAILED);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private void fetchGames() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, this, this);
        queue.add(stringRequest);
    }

    private List<DataModel> parseResponse(String response) throws JSONException {

        List<DataModel> models = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(response);

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String category = jsonObj.getString("category");
            if(category.equals("jewelery")) {
                String title = jsonObj.getString("title");
                String description = jsonObj.getString("description");
                String prices = jsonObj.getString("price");
                String url = jsonObj.getString("image");
                models.add(new DataModel(title,description,prices,category,url));
            }
//            String title = jsonObj.getString("title");
//            models.add(new GameModel(title));
        }
        return models;
    }



    /**
     * Enum class to define status of Game list API request.
     */
    public enum RequestStatus {
        /* Show API is in progress. */
        IN_PROGRESS,

        /* Show API request is failed. */
        FAILED,

        /* Show API request is successfully completed. */
        SUCCEEDED
    }
}

package com.android.countdownapplication;

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

public class CountryListViewModel extends AndroidViewModel
        implements Response.Listener<String>, Response.ErrorListener {

    private static final String API_URL = "https://api.printful.com/countries";
    private static final String RESPONSE_ENTRY_KEY = "result";
    private static final String RESPONSE_NAME_KEY = "name";

    private MutableLiveData<List<DataModel>> countriesLiveData = new MutableLiveData<>();
    private MutableLiveData<RequestStatus> requestStatusLiveData = new MutableLiveData<>();

    private RequestQueue queue;

    public CountryListViewModel(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchGames();
    }

    /**
     * Start re-fetching Game list from service.
     */
    public void refetchCountries() {
        requestStatusLiveData.postValue(RequestStatus.IN_PROGRESS);
        fetchGames();
    }

    /**
     * @return the {@link LiveData} instance containing list of games.
     */
    public LiveData<List<DataModel>> getCountriesLiveData() {
        return countriesLiveData;
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
            countriesLiveData.postValue(gameModels);
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
        JSONObject res = new JSONObject(response);
        JSONArray entries = res.optJSONArray(RESPONSE_ENTRY_KEY);

        if (entries == null) {
            return models;
        }

        for (int i = 0; i < entries.length(); i++) {
            JSONObject obj = (JSONObject) entries.get(i);
            String name = obj.optString(RESPONSE_NAME_KEY);
            DataModel model = new DataModel(name);
            models.add(model);
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

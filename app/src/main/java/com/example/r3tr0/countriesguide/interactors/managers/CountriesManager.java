package com.example.r3tr0.countriesguide.interactors.managers;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import com.example.r3tr0.countriesguide.core.interfaces.ICountriesRetrofit;
import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.interactors.retrofit_clients.CountriesClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The main class of the application for API calls.
 * This class fetches the data from an API using {@link CountriesClient} and
 * parse it in a list of {@link Country}.
 * <p>
 * Then using a {@link android.arch.lifecycle.LiveData} object to track the List changes.
 * <p>
 * The manager uses singleton methodology to create one instance of itself so that
 * the data can be be shared across the application.
 */
public class CountriesManager {

    Context context;
    private MutableLiveData<List<Country>> countries;
    private int selectedCountry;

    private String url;

    public static CountriesManager instance;

    private CountriesManager() {
        selectedCountry = -1;
        countries = new MutableLiveData<>();
    }

    /**
     * A method to get the instance of the class.
     * if it is the first time the object is instantiated, it passes the API url
     * to the getInstance method.
     *
     * @return An instance of the class.
     */
    public static CountriesManager getInstance(Context context) {
        if (instance == null)
            return getInstance(context, "https://restcountries.eu/rest/v2/all");

        return instance;
    }

    /**
     * The private method for instantiating the instance object using the API url.
     *
     * @param url The API url from which the data is fetched.
     * @return The manager's instance.
     */
    private static CountriesManager getInstance(Context context, String url) {
        instance = new CountriesManager();
        instance.context = context;
        instance.setUrl(url);
        return instance;
    }

    /**
     * The method for fetching the data from the API.
     * <p>
     * How it works ?
     * <p>
     * 1)It initializes the {@link ICountriesRetrofit} object using the {@link CountriesClient}
     * to create a new Retrofit client.
     * <p>
     * 2) When enqueue method is used, The client initializes async task to download and parse data.
     * <p>
     * 3) when the data is downloaded, it is set in the {@link MutableLiveData<List<Country>>} object.
     */
    private void getCountriesFromJson() {
        ICountriesRetrofit apiService =
                CountriesClient.getClient().create(ICountriesRetrofit.class);

        apiService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if (response.isSuccessful()) {
                    {
                        countries.setValue(response.body());
                    }
                } else {
                    int statusCode = response.code();// handle request errors depending on status code
                    Toast.makeText(context, "Server returned with code " + statusCode, Toast.LENGTH_SHORT).show();
                    countries.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(context, "Failed to get data " + t.getMessage(), Toast.LENGTH_SHORT).show();
                countries.setValue(null);
            }
        });


    }

    /**
     * The getter method of the countries object.
     * If the value of the object is null, then it creates the fetching task and run it.
     *
     * @return The {@link MutableLiveData<List<Country>>} object.
     */
    public MutableLiveData<List<Country>> getCountries() {
        if (countries.getValue() == null || countries.getValue().size() == 0)
            getCountriesFromJson();
        return countries;
    }

    public int getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(int selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        countries.setValue(null);
    }
}

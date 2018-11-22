package com.example.r3tr0.countriesguide.interactors.retrofit_clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A client class for retrofit api library.
 */
public class CountriesClient {
    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static Retrofit retrofit = null;

    /**
     * The initializing method for the retrofit client that will be used to fetch the data
     * from the API
     *
     * @return An object instance of the {@link Retrofit} class.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

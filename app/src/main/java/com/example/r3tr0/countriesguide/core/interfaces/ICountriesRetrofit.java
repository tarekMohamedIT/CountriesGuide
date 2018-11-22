package com.example.r3tr0.countriesguide.core.interfaces;

import com.example.r3tr0.countriesguide.core.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * THe Retrofit interface used to get countries from the API.
 */
public interface ICountriesRetrofit {

    @GET("all")
    Call<List<Country>> getCountries();

}

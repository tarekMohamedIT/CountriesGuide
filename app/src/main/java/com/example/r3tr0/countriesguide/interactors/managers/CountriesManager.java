package com.example.r3tr0.countriesguide.interactors.managers;

import android.arch.lifecycle.MutableLiveData;

import com.example.r3tr0.countriesguide.core.events.OnJsonArrayDownloaded;
import com.example.r3tr0.countriesguide.core.models.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class of the application for API calls.
 * This class fetches the data from an API using {@link JsonManager} and
 * parse it in a list of {@link Country}.
 *
 * Then using a {@link android.arch.lifecycle.LiveData} object to track the List changes.
 *
 * The manager uses singleton methodology to create one instance of itself so that
 * the data can be be shared across the application.
 */
public class CountriesManager {

    private MutableLiveData<List<Country>> countries;
    private int selectedCountry;
    private JsonManager manager;

    private String url;

    public static CountriesManager instance;

    private CountriesManager(){
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
    public static CountriesManager getInstance() {
        if (instance == null)
            return getInstance("https://restcountries.eu/rest/v2/all");

        return instance;
    }

    /**
     * The private method for instantiating the instance object using the API url.
     *
     * @param url The API url from which the data is fetched.
     * @return The manager's instance.
     */
    private static CountriesManager getInstance(String url) {
        instance = new CountriesManager();
        instance.setUrl(url);
        return instance;
    }

    /**
     * The method for fetching the data from the API.
     *
     * How it works ?
     *
     * 1)It initializes the {@link JsonManager} object using the {@link OnJsonArrayDownloaded}
     *  parameter.
     *
     * 2) Inside the {@link OnJsonArrayDownloaded#onArrayDownloaded(JSONArray)}, it makes
     * a for loop, iterating through the Json array and populating a {@link List<Country>} object.
     *
     * 3) On finishing the loop the data is set in the {@link MutableLiveData<List<Country>>} object.
     *
     * 4) after initializing the object the method executes it as a background task.
     *
     * @param url The url of the API.
     */
    private void getCountriesFromJson(String url) {
        manager = new JsonManager(new OnJsonArrayDownloaded() {
            @Override
            public void onArrayDownloaded(JSONArray jsonArray) {
                ArrayList<Country> countriesList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject countryObject = jsonArray.getJSONObject(i);
                        countriesList.add(new Country(
                                countryObject.getString("name"),
                                countryObject.getString("alpha3Code"),
                                countryObject.getString("capital"),
                                countryObject.getString("region"),
                                countryObject.getString("subregion"),
                                countryObject.getString("nativeName"),
                                countryObject.getString("flag")


                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                countries.setValue(countriesList);
            }
        });
        manager.execute(url);
    }

    /**
     * The getter method of the countries object.
     * If the value of the object is null, then it creates the fetching task and run it.
     *
     * @return The {@link MutableLiveData<List<Country>>} object.
     */
    public MutableLiveData<List<Country>> getCountries() {
        if (countries.getValue() == null || countries.getValue().size() == 0)
            getCountriesFromJson(url);
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

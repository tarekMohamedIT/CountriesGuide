package com.example.r3tr0.countriesguide.interactors.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.interactors.managers.CountriesManager;

import java.util.List;

/**
 * The ViewModel class is responsible for delivering the data from the managers to the UI.
 *
 * I used the {@link AndroidViewModel} class because it is Life cycle-aware, meaning no leaks
 * will happen.
 */
public class CountriesViewModel extends AndroidViewModel {
    CountriesManager manager;
    LiveData<List<Country>> countries;


    public CountriesViewModel(@NonNull Application application) {
        super(application);
        manager = CountriesManager.getInstance();
        countries = manager.getCountries();
    }

    public LiveData<List<Country>> getCountries() {
        return countries;
    }

    public void setSelectedCountry(int position){
        manager.setSelectedCountry(position);
    }

    public int getSelectedCountry(){
        return manager.getSelectedCountry();
    }
}

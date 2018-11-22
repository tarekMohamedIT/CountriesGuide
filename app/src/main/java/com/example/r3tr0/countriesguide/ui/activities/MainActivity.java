package com.example.r3tr0.countriesguide.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.events.OnItemClickListener;
import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.interactors.managers.SignInManager;
import com.example.r3tr0.countriesguide.interactors.view_models.CountriesViewModel;
import com.example.r3tr0.countriesguide.ui.adapters.CountriesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CountriesViewModel viewModel;
    RecyclerView recyclerView;

    List<Country> countriesList;
    CountriesRecyclerAdapter adapter;
    boolean dataUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        char select = getIntent().getCharExtra("char", 'A');

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        initRecyclerView(select);

        if (!dataUpdated) {
            setSelectedData(select);
        }
    }

    void initRecyclerView(char c) {
        final char countryCh = c;
        recyclerView = findViewById(R.id.countriesRecyclerView);
        adapter = new CountriesRecyclerAdapter(this, viewModel.getCountries().getValue());

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewModel.setSelectedCountry(position);
                startActivity(new Intent(MainActivity.this, InformationActivity.class));
            }
        });

        viewModel.getCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable List<Country> countries) {
                countriesList = countries;
                if (!dataUpdated) {
                    setSelectedData(countryCh);
                }
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    void setSelectedData(char c) {
        if (countriesList != null) {
            dataUpdated = true;
            List<Country> selected = new ArrayList<>();

            for (Country country : countriesList) {
                if (country.getName().startsWith("" + c))
                    selected.add(country);
            }

            adapter.setCountries(selected);
        } else {
            dataUpdated = false;
            adapter.setCountries(new ArrayList<Country>());
        }
    }

    public void logout(View view) {
        new SignInManager(this).signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

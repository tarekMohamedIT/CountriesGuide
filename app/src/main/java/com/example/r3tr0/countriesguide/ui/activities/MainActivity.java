package com.example.r3tr0.countriesguide.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.events.OnItemClickListener;
import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.interactors.managers.SignInManager;
import com.example.r3tr0.countriesguide.interactors.view_models.CountriesViewModel;
import com.example.r3tr0.countriesguide.ui.adapters.CountriesRecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CountriesViewModel viewModel;
    RecyclerView recyclerView;
    CountriesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        initRecyclerView();

    }

    void initRecyclerView(){
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
                adapter.setCountries(countries);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void logout(View view) {
        new SignInManager(this).signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

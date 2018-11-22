package com.example.r3tr0.countriesguide.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.events.OnItemClickListener;
import com.example.r3tr0.countriesguide.interactors.view_models.CountriesViewModel;
import com.example.r3tr0.countriesguide.ui.adapters.CharsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CharsActivity extends AppCompatActivity {
    CountriesViewModel viewModel;
    RecyclerView recyclerView;
    CharsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chars);

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        initRecyclerView();

    }

    void initRecyclerView() {
        recyclerView = findViewById(R.id.charsRecyclerView);
        final List<Character> characters = new ArrayList<>();

        for (char i = 'A'; i <= 'Z'; i++) {
            characters.add(i);
        }

        adapter = new CharsAdapter(this, characters);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CharsActivity.this, MainActivity.class);
                intent.putExtra("char", characters.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}

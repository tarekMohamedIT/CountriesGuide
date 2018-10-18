package com.example.r3tr0.countriesguide.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.interactors.view_models.CountriesViewModel;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.GlideApp;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.GlideRequest;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.SvgSoftwareLayerSetter;

public class InformationActivity extends AppCompatActivity {
    CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView alpha3TextView = findViewById(R.id.alpha3NameTextView);
        TextView infoTextView = findViewById(R.id.informationTextView);

        ImageView imageView = findViewById(R.id.app_bar_image);

        Country country = viewModel.getCountries().getValue().get(viewModel.getSelectedCountry());

        if (country != null){
            nameTextView.setText(String.format("%s(%s)", country.getName(), country.getNativeName()));
            alpha3TextView.setText(country.getAlpha3Code());

            infoTextView.setText(String.format("Capital : %s\nRegion : %s\nSubregion : %s"
                    , country.getCapital()
                    , country.getRegion()
                    , country.getSubRegion()));

            buildSvgRequest()
                    .load(country.getFlagUrl())
                    .fitCenter()
                    .into(imageView);

        }
    }

    private GlideRequest<PictureDrawable> buildSvgRequest(){
        return GlideApp.with(this)
                .as(PictureDrawable.class)
                .placeholder(R.drawable.test_image)
                .listener(new SvgSoftwareLayerSetter());
    }
}

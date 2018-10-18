package com.example.r3tr0.countriesguide.ui.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.events.OnItemClickListener;
import com.example.r3tr0.countriesguide.core.models.Country;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.GlideApp;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.GlideOptions;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.GlideRequest;
import com.example.r3tr0.countriesguide.ui.adapters.helpers.SvgSoftwareLayerSetter;

import java.util.List;

/**
 * A simple recycler view adapter implementation with methods for glide svg decoding.
 */
public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder> {
    private Context context;
    private List<Country> countries;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public CountriesRecyclerAdapter(Context context) {
        this.context = context;
    }

    public CountriesRecyclerAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Context getContext() {
        return context;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new CountriesViewHolder(inflater.inflate(R.layout.view_country_flag, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder countriesViewHolder, int position) {
        final int pos = position;
        countriesViewHolder.countryTextView.setText(countries.get(position).getName());

        try {
            buildSvgRequest()
                    .load(countries.get(position).getFlagUrl())
                    .fitCenter()
                    .into(countriesViewHolder.flagImageView);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(onItemClickListener != null)
            countriesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, pos);
                }
            });
    }

    /**
     * A method for building a Glide request for svg decoding.
     * @return The glide request.
     */
    private GlideRequest<PictureDrawable> buildSvgRequest(){
        return GlideApp.with(context)
                .as(PictureDrawable.class)
                .placeholder(R.drawable.test_image)
                .listener(new SvgSoftwareLayerSetter());
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    class CountriesViewHolder extends RecyclerView.ViewHolder{
        ImageView flagImageView;
        TextView countryTextView;

        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);

            flagImageView = itemView.findViewById(R.id.flagImageView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
        }
    }

}
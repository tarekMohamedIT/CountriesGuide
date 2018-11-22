package com.example.r3tr0.countriesguide.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.events.OnItemClickListener;

import java.util.List;

/**
 * A simple recycler view adapter implementation for characters display.
 */
public class CharsAdapter extends RecyclerView.Adapter<CharsAdapter.CharsViewHolder> {
    private Context context;
    private List<Character> characters;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public CharsAdapter(Context context, List<Character> characters) {
        this.context = context;
        this.characters = characters;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CharsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CharsViewHolder(inflater.inflate(R.layout.view_char_select, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharsViewHolder charsViewHolder, int i) {
        final int position = i;
        charsViewHolder.charTextView.setText(String.format("%s", characters.get(i)));

        if (onItemClickListener != null)
            charsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class CharsViewHolder extends RecyclerView.ViewHolder {
        TextView charTextView;

        public CharsViewHolder(@NonNull View itemView) {
            super(itemView);
            charTextView = itemView.findViewById(R.id.charsTextView);
        }
    }
}

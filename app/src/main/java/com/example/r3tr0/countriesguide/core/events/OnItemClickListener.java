package com.example.r3tr0.countriesguide.core.events;

import android.view.View;

/**
 * This event is used in the {@link com.example.r3tr0.countriesguide.ui.adapters.CountriesRecyclerAdapter}
 * To be triggered when a list item is clicked.
 */
public interface OnItemClickListener {
    /**
     * The item click event handler's method.
     * @param view The view which was clicked.
     * @param position The position of this view's item in the list.
     */
    void onItemClick(View view, int position);
}

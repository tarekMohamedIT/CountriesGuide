package com.example.r3tr0.countriesguide.core.events;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The event of Json array downloaded from the API.
 * Used in : {@link com.example.r3tr0.countriesguide.interactors.managers.JsonManager}
 */
public interface OnJsonArrayDownloaded {

    /**
     * The Json array downloaded event handler.
     * @param jsonArray The array which was downloaded from the API
     */
    void onArrayDownloaded(JSONArray jsonArray);
}

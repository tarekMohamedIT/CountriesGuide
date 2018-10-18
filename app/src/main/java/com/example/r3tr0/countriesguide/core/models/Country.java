package com.example.r3tr0.countriesguide.core.models;

/**
 * The country model class.
 * Using the API, The application stores the countries in this class's instances.
 */
public class Country {
    private String name; //The name of the country.
    private String alpha3Code; //The alpha 3 code ie: Egypt : EGY.
    private String capital; //The capital of the country.
    private String region; // The continent of the country.
    private String subRegion; //The sub-region of the continent.
    private String nativeName; //The native name of the country.
    private String flagUrl; //The url of the flag (The API stores the flags using SVG format).

    public Country(String name
            , String alpha3Code
            , String capital
            , String region
            , String subRegion
            , String nativeName
            , String flagUrl) {

        this.name = name;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.region = region;
        this.subRegion = subRegion;
        this.nativeName = nativeName;
        this.flagUrl = flagUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}

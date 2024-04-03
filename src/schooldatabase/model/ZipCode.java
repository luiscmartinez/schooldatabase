package schooldatabase.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ZipCode implements Serializable {
    @SerializedName("post code")
    private String postCode;
    private String country;

    @SerializedName("country abbreviation")
    private String countryAbbreviation;
    private Place[] places;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }
}
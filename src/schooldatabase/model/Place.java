package schooldatabase.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Place implements Serializable {

    @SerializedName("place name")
    private String placename;
    private String longitude;
    private String state;

    @SerializedName("state abbreviation")
    private String stateAbbreviation;
    private String latitude;

    public String getPlaceName() {
        return placename;
    }

    public void setPlaceName(String placeName) {
        this.placename = placeName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

package schooldatabase.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import schooldatabase.model.ZipCode;

public class ZipCodeClient {
    private static final String API_URL = "https://api.zippopotam.us/us/";
    private String zipCode;

    public ZipCodeClient(String zipCode) {
        this.zipCode = zipCode;
    }

    public ZipCode fetchZipCodeInfo() throws IOException {
        String apiUrl = API_URL + zipCode;

        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        ZipCode zipCode = gson.fromJson(response.toString(), ZipCode.class);

        System.out.println(zipCode.getPostCode());
        System.out.println(zipCode.getCountry());
        System.out.println(zipCode.getCountryAbbreviation());
        System.out.println(zipCode.getPlaces()[0].getPlaceName());
        System.out.println(zipCode.getPlaces()[0].getLongitude());
        System.out.println(zipCode.getPlaces()[0].getState());
        System.out.println(zipCode.getPlaces()[0].getStateAbbreviation());
        System.out.println(zipCode.getPlaces()[0].getLatitude());
        return zipCode;
    }
}

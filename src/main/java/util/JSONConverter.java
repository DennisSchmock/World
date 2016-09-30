/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Country;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dennisschmock
 */
public class JSONConverter {
    Gson gson = new Gson();

    public String createCountryJson(List<Country> countries) {
        List<JsonObject> countryArray = new ArrayList();
        int i = 0;
        for (Country country : countries) {
            JsonObject countryJ = new JsonObject();
             countryJ.addProperty("code", country.getCode());
             countryJ.addProperty("name", country.getName());
             countryJ.addProperty("continent", country.getContinent());
             if(country.getCapital()!=null){
             countryJ.addProperty("capital", country.getCapital().getName());
             } else{
                              countryJ.addProperty("capital", "");

             }
             countryArray.add(countryJ);
        }

        return gson.toJson(countryArray);
    }
}

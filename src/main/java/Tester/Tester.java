/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import entity.City;
import entity.Country;
import facade.CountryFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.JSONConverter;

/**
 *
 * @author dennisschmock
 */
public class Tester {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CountryFacade cf = new CountryFacade(emf);
    private static JSONConverter jsc = new JSONConverter();

    public static void main(String[] args) {
        Country c1 = cf.getCountry("AGO");
        List<City> cities = cf.getCities(c1);
        List<Country> countries = cf.getCountries();
        List<Country> countriesMinPop = cf.getCountriesMinPop(10000000);

        City city = new City("","daf", 124345);
        city.setCountryCode(c1);
        cf.createCity(c1, city);

        String json = jsc.createCountryJson(countries);
        System.out.println(json);

//        System.out.println("Total number of countries:" + countries.size());
//
//        for (Country country : countries) {
//            System.out.println(country.getName());
//
//        }
    }
}

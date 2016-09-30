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

/**
 *
 * @author dennisschmock
 */
public class Tester {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CountryFacade cf = new CountryFacade(emf);

    public static void main(String[] args) {
        Country c1 = cf.getCountry("AGO");
        List<City> cities = cf.getCities(c1);
        List<Country> countries = cf.getCountries();
        List<Country> countriesMinPop = cf.getCountriesMinPop(10000000);

        for (City city : cities) {
            System.out.println(city.getName());

        }

//        System.out.println("Total number of countries:" + countries.size());
//
//        for (Country country : countries) {
//            System.out.println(country.getName());
//
//        }
        System.out.println("Total number of countries:" + countriesMinPop.size());

        for (Country country : countriesMinPop) {
            System.out.println(country.getName());

        }
    }
}

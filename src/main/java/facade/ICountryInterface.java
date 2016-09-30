/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.City;
import entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dennis
 */
public interface ICountryInterface {
    
    public Country getCountry(String code);
    public List<Country> getCountries();
    public List<Country> getCountriesMinPop(int population);
    public List<City> getCities(String country);
    public City createCity(Country country, City city);
    public EntityManager getEntityManager();
    
}

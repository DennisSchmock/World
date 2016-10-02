/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.City;
import entity.Country;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Dennis
 */
public class CountryFacade implements ICountryInterface {

    private static EntityManagerFactory emf;

    public CountryFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Country getCountry(String code) {
        EntityManager em = getEntityManager();
        Country country = null;
        try {
            country = em.find(Country.class, code);

        } finally {
            em.close();
        }

        return country;

    }

    @Override
    public List<Country> getCountries() {
        EntityManager em = getEntityManager();
        List<Country> countries = new ArrayList();
        try {
            Query q = em.createQuery("Select c From Country c");
            countries = q.getResultList();

        } finally {
            em.close();
        }
        return countries;

    }

    @Override
    public List<Country> getCountriesMinPop(int population) {
        EntityManager em = getEntityManager();
        List<Country> countries = new ArrayList();
        try {
            Query q = em.createQuery("Select c From Country c where c.population > ?1");
            q.setParameter(1, population);
            countries = q.getResultList();

        } finally {
            em.close();
        }
        return countries;
    }

    @Override
    public List<City> getCities(Country country) {
        EntityManager em = getEntityManager();
        List<City> cities = new ArrayList();
        try {
            Query q = em.createQuery("Select ci From City ci where ci.countryCode = ?1");
            q.setParameter("1", country);
            cities = q.getResultList();

        } finally {
            em.close();
        }
        return cities;
    }

    @Override
    public City createCity(Country country, City city) {
        EntityManager em = getEntityManager();
        try {
            country.getCityCollection().add(city);
            city.setCountryCode(country);
            em.getTransaction().begin();
            em.persist(city);
            em.merge(country);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return city;

    }

    public List<Country> getCountriesSearch(String search) {
      
        search = "%"+search+"%";
        EntityManager em = getEntityManager();
        List<Country> countries = new ArrayList();
        try {
            Query q = em.createQuery("Select c From Country c where c.name like ?1");
            
            q.setParameter(1, search);
            countries = q.getResultList();

        } finally {
            em.close();
        }
        return countries;
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}

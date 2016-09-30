/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.City;
import entity.Country;
import facade.CountryFacade;
import facade.ICountryInterface;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import util.JSONConverter;

/**
 * REST Web Service
 *
 * @author dennisschmock
 */
@Path("country")
public class CountryService {

    @Context
    private UriInfo context;
    private static JSONConverter jscon = new JSONConverter();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static ICountryInterface cf = new CountryFacade(emf);
    private static Gson gson = new Gson();

    /**
     * Creates a new instance of CountryService
     */
    public CountryService() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountries() {
        return jscon.createCountryJson(cf.getCountries());
    }

    @Path("{min}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountries(@PathParam("min") int min) {
        return jscon.createCountryJson(cf.getCountriesMinPop(min));
    }

    @Path("cities/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCities(@PathParam("code") String code) {
        return jscon.createCityJson(cf.getCities(cf.getCountry(code)));
    }

    @Path("cities/{code}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createCity(@PathParam("code") String code, String json) {
        Country country = cf.getCountry(code);
        System.out.println(country.getName());
        City city = jscon.createCityFromJson(json,country);
        System.out.println("From createCity, after Jsoncon" + city.getName());
        cf.createCity(country, city);
        return jscon.createCityJson(city);

    }

}

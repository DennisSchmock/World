/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facade.CountryFacade;
import facade.ICountryInterface;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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

    /**
     * Creates a new instance of CountryService
     */
    public CountryService() {
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountries(){
        return jscon.createCountryJson(cf.getCountries());
    }
    
}

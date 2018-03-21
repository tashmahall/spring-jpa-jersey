package br.com.cinq.spring.data.sample.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.context.annotation.ApplicationScope;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@ApplicationScope
@Path("rest")
public class CityResource {
	@Inject
	private CityRepository cityRep;
	@Inject
	private CountryRepository countryRep;
	
	@GET
	@Path("/cities")
	@Produces({MediaType.APPLICATION_JSON})
	public List<City> getAllCitiesByCountryName(@QueryParam("country") String countryName) {
		List<Country> lCTemp = countryRep.findLikeName(countryName);
		List<City> lCities = new ArrayList<>();
		for(Country cTemp : lCTemp) {
			if(cTemp!=null) {
				lCities.addAll(cityRep.findByCountry(cTemp));
			}
		}
		return lCities;
	}
}

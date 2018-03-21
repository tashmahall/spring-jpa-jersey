package br.com.cinq.spring.data.sample.resource;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
@Component
public class CityFileHandler extends FileHandler<City>{

	@Inject
	private CityRepository cityRep;
	@Inject
	private CountryRepository countryRep;
	@Override
	public void save(City t) {
		cityRep.save(t);	
	}
	@Override
	protected City createEntity(String[] string) {
		Country countryTemp = countryRep.findByName(string[1]);
		City cTemp = new City();
		cTemp.setCountry(countryTemp);
		cTemp.setName(string[0]);
		return cTemp;
	}
	//Create a ValidationChain
	@Override
	protected void valid(String[] stringCity) throws InvalidLineException {
		if(stringCity.length<2||stringCity.length>2) {
			throw new InvalidLineException("The number of parameters to create a city is wrong");
		}
		Country cTemp = countryRep.findByName(stringCity[1]);
		if(cTemp==null) {
			throw new InvalidLineException("There is no country with the name "+stringCity[1]);
		}
		if (stringCity[0] == null || stringCity[0].isEmpty()){
			throw new InvalidLineException("The name of the city can not be null");
		}
	}
}

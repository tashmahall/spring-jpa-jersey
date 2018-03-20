package br.com.cinq.spring.data.sample.util;

import java.util.Scanner;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
@Component
public class FileHandler {
	@Inject
	private CityRepository cityRep;
	@Inject
	private CountryRepository countryRep;
	public Boolean loadToDataTCityTables(Scanner sc) {
		while (sc.hasNext()) {
			String[] array = sc.next().split(";");
		}
		return null;
		
	}
	private void saveCity(String[] stringCity) throws InvalidLineException {
		validCityLine(stringCity);
		Country countryTemp = countryRep.findByName(stringCity[2]);
		City cTemp = new City();
		cTemp.setCountry(countryTemp);
		cTemp.setName(stringCity[1]);
		cityRep.save(cTemp);
	}
	//Create a ValidationChain
	private void validCityLine(String[] stringCity) throws InvalidLineException {
		Country cTemp = countryRep.findByName(stringCity[2]);
		boolean error= false;
		String errorMessage=null;
		if(cTemp==null) {
			errorMessage =  "There is no country with the name "+stringCity[2];
			error=true;
		}
		if (stringCity[1] == null || stringCity[1].isEmpty()){
			errorMessage = "The name of the city can not be null";
			error=true;
		}
		if(error) {
			throw new InvalidLineException(errorMessage);
		}
		
	}

}

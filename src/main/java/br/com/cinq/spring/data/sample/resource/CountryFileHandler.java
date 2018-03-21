package br.com.cinq.spring.data.sample.resource;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
@Component
public class CountryFileHandler extends FileHandler<Country>{

	@Inject
	private CountryRepository countryRep;
	@Override
	public void save(Country t) throws InvalidLineException {
		countryRep.save(t);	
	}
	@Override
	protected Country createEntity(String[] string) {
		Country cTemp = new Country();
		cTemp.setName(string[0]);
		return cTemp;
	}
	//Create a ValidationChain
	@Override
	protected void valid(String[] stringCity) throws InvalidLineException {
		if(stringCity.length<1||stringCity.length>1) {
			throw new InvalidLineException("The number of parameters to create a Country is wrong");
		}
		if (stringCity[0] == null || stringCity[0].isEmpty()){
			throw new InvalidLineException("The name of the Country can not be null");
		}
	}
}

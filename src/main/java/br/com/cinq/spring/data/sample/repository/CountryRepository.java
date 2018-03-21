package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import br.com.cinq.spring.data.sample.entity.Country;

public interface CountryRepository  {
	public Country findByName( String countryName);
		
	public List<Country> findLikeName( String countryName);
	
	public Integer count();
	
	public List<Country> findAll();
	
	public void save(Country country);

}

package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public interface CityRepository extends CrudRepository<City, Integer> {
	@Query("select u from City u where u.country.id = (select c.id from Country c where c = :country)")
	public List<City> findByCountry(@Param("country") Country country) throws NoResultException;

}

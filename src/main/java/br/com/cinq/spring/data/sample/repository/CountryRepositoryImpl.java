package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.Country;

@Repository
@Transactional
public class CountryRepositoryImpl implements CountryRepository {
    @PersistenceContext
    private EntityManager em;
	@Override
	public Country findByName(String countryName) {
		TypedQuery<Country> typedQuery=em.createNamedQuery(Country.FIND_BY_NAME, Country.class);
		typedQuery.setParameter("countryName", countryName);
		Country c = typedQuery.getSingleResult();
		return c;
	}

	@Override
	public List<Country> findLikeName(String countryName) {
		TypedQuery<Country> typedQuery=em.createNamedQuery(Country.FIND_LIKE_NAME, Country.class);
		typedQuery.setParameter("countryName", countryName);
		return typedQuery.getResultList();
	}

	@Override
	public Integer count() {
		TypedQuery<Country> typedQuery=em.createNamedQuery(Country.FIND_ALL,Country.class);
		if (typedQuery==null)
			return 0;
		return typedQuery.getResultList().size();
	}

	@Override
	public List<Country> findAll() {
		TypedQuery<Country> typedQuery=em.createNamedQuery(Country.FIND_ALL,Country.class);
		if(typedQuery==null)
			return null;
		return typedQuery.getResultList();
	}

	@Override
	public void save(Country country) {
		em.persist(country);
	}

}

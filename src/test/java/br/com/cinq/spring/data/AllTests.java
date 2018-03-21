package br.com.cinq.spring.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.cinq.spring.data.repository.test.CityRepositoryTest;
import br.com.cinq.spring.data.repository.test.CountryRepositoryTest;
import br.com.cinq.spring.data.sample.test.CityFileHandlerTest;
import br.com.cinq.spring.data.sample.test.CountryFileHandlerTest;
import br.com.cinq.spring.data.sample.test.EndpointTest;

@RunWith(Suite.class)
@SuiteClasses({
	CountryRepositoryTest.class,
	CityRepositoryTest.class,
	EndpointTest.class,
	CityFileHandlerTest.class,
	CountryFileHandlerTest.class,
	})
public class AllTests {

}

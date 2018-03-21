package br.com.cinq.spring.data.sample.test;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.resource.CityFileHandler;
import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
public class CityFileHandlerTest {
	@Autowired
	private CityFileHandler cfh;
	
    @Autowired
    private CityRepository dao;
    @Autowired
    private CountryRepository dao2;

	@Test
	public void testSaveCity() {
		
        Assert.assertNotNull(dao);
        
        Assert.assertNotNull(cfh);
        
        Country country = dao2.findByName("Brazil");
        
        City c = new City();
        c.setName("Niteroi");
        c.setCountry(country);
		cfh.save(c);
        List<City> lTemp = dao.findByCountry(country);
        for(City city : lTemp) {
        	if(city.getName().equals("Niteroi")) {
        		 Assert.assertTrue(city.getName().equals("Niteroi"));
        		 return;
        	}
        }
        fail();
	}


	@Test
	public void testLoadToDataTableCityTables() {
		Assert.assertNotNull(cfh);
	    Assert.assertNotNull(dao);
		String csv = "Itaborai;Brazil\n" + 
				"Petropolis;Brazil\n" + 
				"Teresopolis;Brazil\n" + 
				"Friburgo;Brazil";
		try {
			cfh.loadToDataTableCityTables(new Scanner(csv), ";");
	        Country country = dao2.findByName("Brazil");
	        List<City> lTemp = dao.findByCountry(country);
	        for(City city : lTemp) {
	        	if(city.getName().equals("Petropolis")) {
	        		 Assert.assertTrue(city.getName().equals("Petropolis"));
	        		 return;
	        	}
	        }
	        fail();
			
		} catch (InvalidLineException e) {
		}
	}
	@Test(expected = InvalidLineException.class) 
	public void testFailLoadToDataTableCityTables() throws InvalidLineException {
		Assert.assertNotNull(cfh);
	    Assert.assertNotNull(dao);
		String wrongcsv = "Angola"; 
		cfh.loadToDataTableCityTables(new Scanner(wrongcsv), ";");
	}
}

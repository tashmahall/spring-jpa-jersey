package br.com.cinq.spring.data.sample.test;

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
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.resource.CountryFileHandler;
import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
public class CountryFileHandlerTest {
	@Autowired
	private CountryFileHandler cfh;
	
    @Autowired
    private CountryRepository dao;
	

	@Test
	public void testSaveCountry() {
		
        Assert.assertNotNull(dao);
        
        Assert.assertNotNull(cfh);
        
        Country countries = new Country();
        countries.setName("Gearmany");
		cfh.save(countries);
        Country cTemp = dao.findByName("Gearmany");
        Assert.assertEquals("Gearmany", cTemp.getName());
	}


	@Test
	public void testLoadToDataTableCountryTables() {
		Assert.assertNotNull(cfh);
	    Assert.assertNotNull(dao);
		String csv = "Afghanistan\n" + 
				"Albania\n" + 
				"Algeria\n" + 
				"Andorra";
		try {
			cfh.loadToDataTableCityTables(new Scanner(csv), ";");
			Country c = dao.findByName("Algeria");
			Assert.assertEquals("Algeria", c.getName());
			
		} catch (InvalidLineException e) {
		}
	}
	@Test(expected = InvalidLineException.class) 
	public void testFailLoadToDataTableCountryTables() throws InvalidLineException {
		Assert.assertNotNull(cfh);
	    Assert.assertNotNull(dao);
		String wrongcsv = "Angola;Teste"; 
		cfh.loadToDataTableCityTables(new Scanner(wrongcsv), ";");
	}
}

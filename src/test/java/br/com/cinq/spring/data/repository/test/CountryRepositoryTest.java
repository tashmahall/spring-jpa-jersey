package br.com.cinq.spring.data.repository.test;

import java.util.List;

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
//import br.com.cinq.spring.data.sample.entity.Country;
//import br.com.cinq.spring.data.sample.repository.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository dao;

    @Test
    public void testGelAllCountries() {

        Assert.assertNotNull(dao);

        long count = dao.count();

        Assert.assertTrue(count > 0);

        List<Country> countries = dao.findAll();

        Assert.assertEquals((int) count, countries.size());
    }

    @Test
    public void testFindOneCountry() {

        Assert.assertNotNull(dao);

        List<Country> countries = dao.findLikeName("Fra");

        Assert.assertEquals(1, countries.size());

    }
    @Test
    public void testFindOneCountryByName() {

        Assert.assertNotNull(dao);

        Country countries = dao.findByName("Brazil");

        Assert.assertEquals("Brazil", countries.getName());

    }
    @Test
    public void testSaveOneCountry() {

        Assert.assertNotNull(dao);
        
        Country countries = new Country();
        countries.setName("Russia");
        dao.save(countries);
        
        Country cTemp = dao.findByName("Russia");

        Assert.assertEquals("Russia", cTemp.getName());

    }
}

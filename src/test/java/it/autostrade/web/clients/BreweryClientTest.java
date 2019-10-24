package it.autostrade.web.clients;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import it.autostrade.web.models.BeerDto;

import java.net.URI;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BreweryClientTest {

	@Autowired
	BreweryClient client;
	
	@Test
	public void testGetBeerById() {
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}

	@Test
	public void testSaveNewBeer() {
		BeerDto beerDto = BeerDto.builder().uuid(UUID.randomUUID()).name("NEW_BEER").style("NEW_STYLE").ups((long) 999).build();
		URI uri = client.saveNewBeer(beerDto);
		//assertNotNull(uri);
		System.out.println(uri.toString());
	}
	
	@Test
	public void testUpdateBeer() {
		BeerDto beerDto = BeerDto.builder().uuid(UUID.randomUUID()).name("NEW_BEER").style("NEW_STYLE").ups((long) 999).build();
		client.updateBeer(UUID.randomUUID(), beerDto);
	}
}

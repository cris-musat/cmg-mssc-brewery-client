package it.autostrade.web.clients;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.autostrade.web.models.BeerDto;

@SpringBootTest
public class BreweryClientTest {

	@Autowired
	BreweryClient client;
	
	@Test
	public void testGetBeerById() {
		BeerDto dto = client.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}

}

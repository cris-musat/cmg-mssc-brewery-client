package it.autostrade.web.clients;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.autostrade.web.models.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties(prefix ="sfg.brewery", ignoreUnknownFields = false)
@Slf4j
public class BreweryClient {

	private final RestTemplate restTemplate;
	private String apihost;
	public final String BEER_PATH_V1 = "/api/v1/beer/";
	
	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}
	
	public BeerDto getBeerById(UUID uuid) {
		return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
	}
	
	public URI saveNewBeer(BeerDto beerDto) throws URISyntaxException {
		log.info("__________________ IN saveNewBeer {}", beerDto.getUuid().toString());
		log.info("__________________ IN saveNewBeer {}", beerDto.getName());
		log.info("__________________ IN saveNewBeer {}", beerDto.getStyle());
		log.info("__________________ IN saveNewBeer {}", beerDto.getUps());
		
		URI x = null;
		try {
		x = restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
		}
		catch (Exception e) {
			log.error("__________________ err_saveNewBeer: ", e);
		}
		return x;
	}
	
	public void updateBeer(UUID uuid, BeerDto beerDto) {
		log.info("__________________ IN updateBeer");
		restTemplate.put(apihost + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
		log.info("__________________ END updateBeer");
	}
	
	public void deleteBeer(UUID uuid) {
		// TODO Auto-generated method stub
		restTemplate.delete(apihost + BEER_PATH_V1 + "/" + uuid.toString());
	}
}

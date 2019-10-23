package it.autostrade.web.clients;

import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.autostrade.web.models.BeerDto;

@Component
@ConfigurationProperties(value="sfg.brewery", ignoreUnknownFields = false)
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
}

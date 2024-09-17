package com.techelevator.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	@Override
	public CatFact getFact() {
		RestTemplate restTemplate = new RestTemplate(); // Create a new client

		ResponseEntity<CatFact> response = restTemplate.getForEntity(
				"https://teapi.netlify.app/api/cats/facts/random",
				CatFact.class); // Make GET request using client

		CatFact catFact = response.getBody();

		if (catFact != null) {
			return catFact;
		} else {
			// Handle the case where the API response is null
			throw new RuntimeException("Failed to fetch cat fact");
		}
	}

}

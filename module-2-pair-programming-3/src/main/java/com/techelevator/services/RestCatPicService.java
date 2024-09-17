package com.techelevator.services;

import com.techelevator.model.CatPic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	@Override
	public CatPic getPic() {
		RestTemplate restTemplate = new RestTemplate(); // Create a new client
		ResponseEntity<CatPic> response = restTemplate.getForEntity(
				"https://teapi.netlify.app/api/cats/pictures/random",
				CatPic.class); // Make GET request using client
		CatPic catPic = response.getBody();
		if (catPic != null) {
			return catPic;
		} else {
			// Handle the case where the API response is null
			throw new RuntimeException("Failed to fetch cat picture");
		}
	}
}

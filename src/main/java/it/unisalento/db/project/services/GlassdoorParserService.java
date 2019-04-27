package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.GlassdoorJobDetail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GlassdoorParserService {

	public GlassdoorJobDetail parse(String url) {

		try{
			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

			HttpEntity entity = new HttpEntity(headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<GlassdoorJobDetail> response = restTemplate.exchange(
					"https://www.glassdoor.com/Job/json/details.htm?jobListingId="
							+ url, HttpMethod.GET, entity, GlassdoorJobDetail.class);

			return response.getBody();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}


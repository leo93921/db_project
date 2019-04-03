package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.MonsterJobDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonsterParserService {

    public MonsterJobDetails parse(String url) {

        RestTemplate restTemplate = new RestTemplate();

        MonsterJobDetails details = restTemplate.getForObject(url, MonsterJobDetails.class);

        return details;

    }
}

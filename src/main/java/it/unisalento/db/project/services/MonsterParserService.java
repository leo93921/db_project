package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.MonsterJobDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class MonsterParserService {

    public MonsterJobDetails parse(String stringUrl) throws MalformedURLException {

        // Get jobID to form the API URL
        URL url = new URL(stringUrl);
        String[] query = url.getQuery().split("&");

        String jobId = "";
        for (String queryParam : query) {
            String[] params = queryParam.split("=");
            if (params[0].equals("jobid")) {
                jobId = params[1];
                break;
            }
        }

        // Create the URL
        String apiUrl = "https://offerte-di-lavoro.monster.it/v2/job/pure-json-view?jobid=" + jobId;
        RestTemplate restTemplate = new RestTemplate();

        // Call the API and return details
        MonsterJobDetails details = restTemplate.getForObject(apiUrl, MonsterJobDetails.class);
        return details;

    }
}

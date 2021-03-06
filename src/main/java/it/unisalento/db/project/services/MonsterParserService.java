package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.MonsterJobDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

@Service
public class MonsterParserService {

    public MonsterJobDetails parse(String stringUrl) throws HttpClientErrorException{

        try{
            String[] id = stringUrl.split("/");

            String jobId = id[id.length - 1];

            // Create the URL
            String apiUrl = "https://offerte-di-lavoro.monster.it/v2/job/pure-json-view?jobid=" + jobId;
            RestTemplate restTemplate = new RestTemplate();

            // Call the API and return details
            return restTemplate.getForObject(apiUrl, MonsterJobDetails.class);


        } catch(Exception e) {
            //e.printStackTrace();
            return null;
        }

    }

}

package it.unisalento.db.project.services;


import it.unisalento.db.project.models.data.adapter.MongoAdapter;
import it.unisalento.db.project.models.dto.GlassdoorJobDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlassdoorJobsListService extends BaseService{

	public boolean saveJobs(String url){
		try{

			return super.saveJobs(jobsList(url));

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private List<MongoAdapter> jobsList(String url) {

		List<MongoAdapter> jobs = new ArrayList<>();


		try{
			Document doc = Jsoup.connect(url).get();
			Elements h1s = doc.getElementsByAttributeValue("property", "og:url");

			Document realDoc = Jsoup.connect(h1s.get(0).attributes().get("content")).get();

			Elements jobList = realDoc.getElementsByClass("jl");

			//TODO choose if retrieve by just a job or all jobs
			/*jobList.forEach(job -> System.out.println(job.attributes().get("data-id")));*/

			//TODO handle exception

			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

			HttpEntity entity = new HttpEntity(headers);

			RestTemplate restTemplate = new RestTemplate();
			jobList.forEach(job -> {
				ResponseEntity<GlassdoorJobDetail> response = restTemplate.exchange(
						"https://www.glassdoor.com/Job/json/details.htm?jobListingId="
								+ job.attributes().get("data-id"), HttpMethod.GET, entity, GlassdoorJobDetail.class);

				MongoAdapter dataAdapter = new MongoAdapter(response.getBody(), null, null);
				jobs.add(dataAdapter);
			});

			return jobs;

		} catch(Exception ex) {
			return jobs;
		}
	}

}

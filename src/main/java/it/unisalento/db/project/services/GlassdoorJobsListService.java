package it.unisalento.db.project.services;


import it.unisalento.db.project.models.data.adapter.MongoAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlassdoorJobsListService extends BaseService{

	@Autowired
	GlassdoorParserService glassdoorParserService;

	public boolean saveJobs(String url){
		try{
			return super.saveJobs(jobsList(jobListUrl(url)));
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private List<String> jobListUrl(String url){

		List<String> urls = new ArrayList<>();

		try{

			Document doc = Jsoup.connect(url).get();

			Elements h1s = doc.getElementsByAttributeValue("property", "og:url");

			Document realDoc = Jsoup.connect(h1s.get(0).attributes().get("content")).get();

			Elements jobList = realDoc.getElementsByClass("jl");

			for(Element job: jobList){
				urls.add(job.attributes().get("data-id"));
			}
			return urls;
		} catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private List<MongoAdapter> jobsList(List<String> urls) {

		List<MongoAdapter> jobs = new ArrayList<>();

		try{
			for(String url : urls){
				try{
					jobs.add(new MongoAdapter(glassdoorParserService.parse(url),
							null, null));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}

			return jobs;

		} catch(Exception ex) {
			return jobs;
		}
	}

}

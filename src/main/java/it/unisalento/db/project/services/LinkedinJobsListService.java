package it.unisalento.db.project.services;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import it.unisalento.db.project.models.data.adapter.MongoAdapter;
import it.unisalento.db.project.models.dto.LinkedinJobDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class LinkedinJobsListService extends BaseService{

	@Autowired
	private LinkedinParserService linkedinParserService;


	public boolean saveJobs(String url){
		try{
			super.saveJobs(parseItems(jobsList(url)));
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	private List<Map<String, String>> jobsList(String url){

		try{

			List<Map<String, String>> maps = new ArrayList<>();

			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.getElementsByClass("listed-job-posting--is-link");

			elements.forEach(element -> {
				try{
					maps.add(linkedinParserService.parse(element.attributes().get("href")));
				}catch(IOException pa){
					pa.printStackTrace();
				}
			});

			return maps;

		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<MongoAdapter> parseItems(List<Map<String, String>> maps){

		List<MongoAdapter> mongoAdapters = new ArrayList<>();

		try{

			for(Map<String, String> map : maps){

				ObjectMapper mapper = new ObjectMapper();

				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

				System.out.println(map.toString());

				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

				Gson gson = new Gson();

				String json = gson.toJson(map);

				LinkedinJobDetail linkedinJobDetail = mapper.readValue(json, LinkedinJobDetail.class);

				mongoAdapters.add(new MongoAdapter(null, linkedinJobDetail, null));

			}

			return mongoAdapters;

		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}

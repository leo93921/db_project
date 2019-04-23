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
public class MonsterJobsListService extends BaseService{

	@Autowired
	MonsterParserService monsterParserService;

	public boolean saveJobs(String url){
		try{
			List<MongoAdapter> adapters = getData(parseList(url));
			super.saveJobs(adapters);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	private List<String> parseList(String url) {

		try{
			List<String> urls = new ArrayList<>();
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.getElementsByAttribute("data-jobid");

			for(Element element : elements){
				String href = element.attributes().get("data-jobid");
				if(!href.isEmpty()){
					urls.add(href);
				}
			}
			return urls;
		} catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}


	private List<MongoAdapter> getData(List<String> urls){

		List<MongoAdapter> mongoAdapters = new ArrayList<>();

		for(String url: urls){
			try{
				mongoAdapters.add(new MongoAdapter(null, null, monsterParserService.parse(url)));
			} catch(Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
		return mongoAdapters;
	}

}

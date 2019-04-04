package it.unisalento.db.project.services;

import com.mongodb.util.JSONParseException;
import it.unisalento.db.project.models.dto.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class GlassdoorParserService {

	private JsonParser jparser = new JsonParser();

	public JSONObject parse(String url) throws IOException {

		System.out.println(url);
		Document doc =  Jsoup.connect(url).get();

		Elements h1s = doc.getElementsByAttributeValue("property", "og:url");

		Document realDoc = Jsoup.connect(h1s.get(0).attributes().get("content")).get();

		Elements jobList = realDoc.getElementsByClass("jl");

		//TODO choose if retrieve by just a job or all jobs
		jobList.forEach(job -> System.out.println(job.attributes().get("data-id")));

		URL dataUrl = new URL("https://www.glassdoor.com/Job/json/details.htm?jobListingId="
					+ jobList.get(0).attributes().get("data-id"));

		HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

		//TODO handle exception
		int status = con.getResponseCode();

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		con.disconnect();

		try{
			return jparser.parseToJson(content.toString());
		}catch(JSONParseException | ParseException ex) {
			ex.printStackTrace();
		}

		//TODO retrieve usefull data
		return new JSONObject();
	}

}


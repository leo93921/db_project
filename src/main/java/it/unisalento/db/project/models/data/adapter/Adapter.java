package it.unisalento.db.project.models.data.adapter;

import com.opencsv.CSVReader;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter implements IAdapter{

	List<String> formatText(String text){

		text = text.replace("<br/>", " ");
		text = text.replace("<br>", " ");
		text = text.replace("</u>", " ");
		text = text.replace(":", " ");
		text = text.replace("\\n", " ");
		text = text.replace("<u>", " ");
		text = text.replace(",", " ");
		text = text.replace(".", " ");
		text = text.replace("<li>", " ");
		text = text.replace("<div>", " ");
		text = text.replace("</div>", " ");
		text = text.replace("<strong>", " ");
		text = text.replace("</strong>", " ");
		text = text.replace("<ul>", " ");
		text = text.replace("?", " ");
		text = text.replace("</li>", " ");
		text = text.replace("</p>", " ");
		text = text.replace("</ul>", " ");
		text = text.replace(")", " ");
		text = text.replace("(", " ");
		text = text.replace("<p>", " ");
		text = text.replace("</p>", " ");
		text = text.replace("<h2>", " ");
		text = text.replace("</h2>", " ");
		text = text.replace("/", " ");

		List<String> formattedText = new ArrayList<>();
		Collections.addAll(formattedText, text.split(" "));

		return formattedText;

	}

	String findResponsibilities(List<String> formattedText){
		try{
			String LANGUAGE_CSV_PATH = System.getProperty("user.dir") +
					"/src/main/java/it/unisalento/db/project/data/responsibilities.csv";

			List<String> recordFound = new ArrayList<>();


			Reader langReader = Files.newBufferedReader(Paths.get(LANGUAGE_CSV_PATH));
			CSVReader csvReader = new CSVReader(langReader);
			List<String[]> responseList = csvReader.readAll();

			for(String s : formattedText){
				for(String[] lang : responseList){
					if(s.toLowerCase().matches(lang[0].toLowerCase())){
						if(! recordFound.contains(lang[0])){
							recordFound.add(lang[0]);
						}
					}
				}
			}

			return recordFound.toString();

		} catch(Exception e) {
			return null;
		}
	}

	String findRequirements(List<String> formattedText){
		try {

			String LANGUAGE_CSV_PATH = System.getProperty("user.dir") +
					"/src/main/java/it/unisalento/db/project/data/languages.csv";

			String DEG_CSV_PATH = System.getProperty("user.dir") +
					"/src/main/java/it/unisalento/db/project/data/degree.csv";

			List<String> recordFound = new ArrayList<>();


			Reader langReader = Files.newBufferedReader(Paths.get(LANGUAGE_CSV_PATH));
			CSVReader csvReader = new CSVReader(langReader);
			List<String[]> langList = csvReader.readAll();


			Reader degReader = Files.newBufferedReader(Paths.get(DEG_CSV_PATH));
			CSVReader degCSV = new CSVReader(degReader);
			List<String[]> degList = degCSV.readAll();

			for(String s : formattedText){
				for(String[] lang: langList) {
					if(s.toLowerCase().matches(lang[0].toLowerCase())){
						if(!recordFound.contains(lang[0])){
							recordFound.add(lang[0]);
						}
					}
				}
				for(String[] deg: degList) {
					if(s.toLowerCase().matches(deg[0].toLowerCase())){
						if(!recordFound.contains(deg[0])){
							recordFound.add(deg[0]);
						}
					}
				}
			}

			return recordFound.toString();

		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	@Override
	public Company getCompany(){
		return null;
	}

	@Override
	public Job getJob() throws ParseException{
		return null;
	}

	@Override
	public Location getLocation(){
		return null;
	}

	@Override
	public Platform getPlatform(){
		return null;
	}

}

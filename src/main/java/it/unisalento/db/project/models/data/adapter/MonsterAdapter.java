package it.unisalento.db.project.models.data.adapter;


import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.models.dto.MonsterJobDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonsterAdapter extends Adapter{

	private MonsterJobDetails monsterJobDetails;

	MonsterAdapter(MonsterJobDetails monsterJobDetails){
		this.monsterJobDetails = monsterJobDetails;
	}

	@Override
	public Company getCompany(){
		try{
			return new Company(monsterJobDetails.getCompanyInfo().getName());
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Job getJob() {
		try{
			List<String> formattedText = super.formatText(monsterJobDetails.getJobDescription());
			List<String> requirements = super.findRequirements(formattedText);
			List<String> responsibilities = super.findResponsibilities(formattedText);
			Date posted = dateParser(monsterJobDetails.getSummary().getInfo().get(1).getText());
			String link = monsterJobDetails.getJobId();

			return new Job(posted, monsterJobDetails.getJobCategory(), null, getLocation(), getPlatform(), getCompany(),
					responsibilities, requirements, link, null, null);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Location getLocation(){
		try{
			return new Location(monsterJobDetails.getLocations().get(0));
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Platform getPlatform(){
		return new Platform("Monster", "https://offerte-di-lavoro.monster.it/v2/job/pure-json-view?jobid=");
	}

	private Date dateParser(String dateToConvert) {

		try{
			String[] formatText = dateToConvert.split(" ");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			int day = Integer.parseInt(formatText[0]);
			String type = formatText[1];

			switch(type){
				case "giorni":
				case "giorno":
					cal.add(Calendar.DAY_OF_MONTH, day);
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				case "mesi":
				case "mese":
					cal.add(Calendar.MONTH, day);
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				default:
					return new Date();
			}

		}catch(Exception e) {
			return new Date();
		}

	}

}

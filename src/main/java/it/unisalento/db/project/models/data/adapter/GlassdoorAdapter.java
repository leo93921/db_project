package it.unisalento.db.project.models.data.adapter;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.models.dto.GlassdoorJobDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


class GlassdoorAdapter extends Adapter{
	private GlassdoorJobDetail glassdoorJobDetail;

	GlassdoorAdapter(GlassdoorJobDetail glassdoorJobDetail){
		this.glassdoorJobDetail = glassdoorJobDetail;
	}

	@Override
	public Company getCompany(){
		try{
			return new Company(glassdoorJobDetail.getHeader().getEmployerName());
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Job getJob() {
		try{
			List<String> formattedText = super.formatText(glassdoorJobDetail.getJob().getDescription());
			List<String> requirements = super.findRequirements(formattedText);
			List<String> responsibilities = super.findResponsibilities(formattedText);
			Date posted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(glassdoorJobDetail.getJob().getDiscoverDate());
			String link = glassdoorJobDetail.getJob().getListingId() + "";
			return new Job(posted, glassdoorJobDetail.getHeader().getJobTitle(), null, getLocation(), getPlatform(), getCompany(),
					responsibilities, requirements, link, null, null);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Location getLocation(){
		try{
			return new Location(glassdoorJobDetail.getHeader().getLocation().split(",")[0]);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Platform getPlatform(){
		return new Platform("Glassdoor", "https://www.glassdoor.com/Job/json/details.htm?jobListingId=");
	}

}

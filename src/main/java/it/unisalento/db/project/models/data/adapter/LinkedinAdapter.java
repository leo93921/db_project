package it.unisalento.db.project.models.data.adapter;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.models.dto.LinkedinJobDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class LinkedinAdapter extends Adapter{

	private LinkedinJobDetail linkedinJobDetail;


	LinkedinAdapter(LinkedinJobDetail linkedinJobDetail) {
		this.linkedinJobDetail = linkedinJobDetail;
	}

	@Override
	public Company getCompany() {
		try{
			return new Company(linkedinJobDetail.getCompany());
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Job getJob() {
		try{
			List<String> formattedText = super.formatText(linkedinJobDetail.getDescription());
			List<String> requirements = super.findRequirements(formattedText);
			List<String> responsibilities = super.findResponsibilities(formattedText);
			Date posted = convertDate(linkedinJobDetail.getPosted());
			String link = linkedinJobDetail.getLink() + "";
			return new Job(posted, linkedinJobDetail.getName(), null, getLocation(), getPlatform(), getCompany(),
					responsibilities, requirements, link, null,null);
		} catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public Location getLocation(){
		try{
			return new Location(linkedinJobDetail.getLocation());
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Platform getPlatform(){
		return new Platform("Linkedin", "https://www.linkedin.com/jobs/view/--?trk=jobs_jserp_job_listing_text");
	}

	private Date convertDate(String date){
		String[] dates = date.split(" ");


		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		try{
			switch(dates[1]){
				case "days":
					cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[0]));
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				case "weeks":
					cal.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(dates[0]));
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				case "month":
					cal.add(Calendar.MONTH, Integer.parseInt(dates[0]));
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				case "year":
					cal.add(Calendar.YEAR, Integer.parseInt(dates[0]));
					return dateFormat.parse(dateFormat.format(cal.getTime()));

				default:
					return new Date();

			}

		} catch(ParseException pa) {
			return new Date();
		}
	}

}

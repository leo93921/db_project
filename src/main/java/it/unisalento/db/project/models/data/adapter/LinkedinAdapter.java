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
		return new Company(linkedinJobDetail.getCompany());
	}

	@Override
	public Job getJob() {
		try{
			List<String> formattedText = super.formatText(linkedinJobDetail.getDescription());
			String requirements = super.findRequirements(formattedText);
			String responsibilities = super.findResponsibilities(formattedText);
			Date posted = convertDate(linkedinJobDetail.getPosted());
			String link = linkedinJobDetail.getLink() + "";
			return new Job(posted, null, getLocation().get_id(), getPlatform().get_id(), getCompany().get_id(),
					responsibilities, requirements, link);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Location getLocation(){
		return new Location(linkedinJobDetail.getLocation());
	}

	/**
	 * Bisogna dividere il link a metà in -- ed inserire li l'id dell'annuncio
	 */
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
					return null;

			}

		} catch(ParseException pa) {
			pa.printStackTrace();
			return null;
		}
	}

}

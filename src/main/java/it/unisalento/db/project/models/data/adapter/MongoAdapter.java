package it.unisalento.db.project.models.data.adapter;

import com.mongodb.lang.Nullable;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.models.dto.GlassdoorJobDetail;
import it.unisalento.db.project.models.dto.LinkedinJobDetail;
import it.unisalento.db.project.models.dto.MonsterJobDetails;

import java.text.ParseException;

public class MongoAdapter implements MongoDataAdapter{

	private GlassdoorJobDetail glassdoorJobDetail;
	private LinkedinJobDetail linkedinJobDetail;
	private MonsterJobDetails monsterJobDetails;


	public MongoAdapter(@Nullable GlassdoorJobDetail glassdoorJobDetail,
	                    @Nullable LinkedinJobDetail linkedinJobDetail,
	                    @Nullable MonsterJobDetails monsterJobDetails) {
		this.glassdoorJobDetail = glassdoorJobDetail;
		this.linkedinJobDetail = linkedinJobDetail;
		this.monsterJobDetails = monsterJobDetails;
	}


	@Override
	public Company getCompany(){
		if(glassdoorJobDetail != null) return new GlassdoorAdapter(glassdoorJobDetail).getCompany();
		if(linkedinJobDetail != null) return new LinkedinAdapter(linkedinJobDetail).getCompany();
		if(monsterJobDetails != null) return null;
		return null;
	}

	@Override
	public Job getJob(){
		try{
			if(glassdoorJobDetail != null) return new GlassdoorAdapter(glassdoorJobDetail).getJob();
			if(linkedinJobDetail != null) return new LinkedinAdapter(linkedinJobDetail).getJob();
		} catch(ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Location getLocation(){
		if(glassdoorJobDetail != null ) return new GlassdoorAdapter(glassdoorJobDetail).getLocation();
		if(linkedinJobDetail != null) return new LinkedinAdapter(linkedinJobDetail).getLocation();

		return null;
	}

	@Override
	public Platform getPlatform(){
		if(glassdoorJobDetail != null ) return new GlassdoorAdapter(glassdoorJobDetail).getPlatform();
		if(linkedinJobDetail != null) return new LinkedinAdapter(linkedinJobDetail).getPlatform();

		return null;
	}


}

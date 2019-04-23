package it.unisalento.db.project.models.domain;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Job")
public class Job{

	@Id
	private ObjectId _id;

	private Date posted;
	private Date hiringDate;

	private ObjectId locationId;
	private ObjectId platformId;
	private ObjectId companyId;

	private String responsibilities;
	private String requirements;
	private String link;

	public Job() {}

	public Job(Date posted, @Nullable Date hiringDate, @Nullable ObjectId locationId, @Nullable ObjectId platformId,
	           @Nullable ObjectId companyId, String responsibilities, String requirements, String link) {

		this.posted = posted;
		this.hiringDate = hiringDate;
		this.locationId = locationId;
		this.platformId = platformId;
		this.companyId = companyId;
		this.responsibilities = responsibilities;
		this.requirements = requirements;
		this.link = link;

	}

	public ObjectId get_id(){
		return _id;
	}

	public void set_id(ObjectId _id){
		this._id = _id;
	}

	public Date getPosted(){
		return posted;
	}

	public void setPosted(Date posted){
		this.posted = posted;
	}

	public Date getHiringDate(){
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate){
		this.hiringDate = hiringDate;
	}

	public ObjectId getLocationId(){
		return locationId;
	}

	public void setLocationId(ObjectId locationId){
		this.locationId = locationId;
	}

	public ObjectId getPlatformId(){
		return platformId;
	}

	public void setPlatformId(ObjectId platformId){
		this.platformId = platformId;
	}

	public ObjectId getCompanyId(){
		return companyId;
	}

	public void setCompanyId(ObjectId companyId){
		this.companyId = companyId;
	}

	public String getResponsibilities(){
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities){
		this.responsibilities = responsibilities;
	}

	public String getRequirements(){
		return requirements;
	}

	public void setRequirements(String requirements){
		this.requirements = requirements;
	}

	public String getLink(){
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}

}

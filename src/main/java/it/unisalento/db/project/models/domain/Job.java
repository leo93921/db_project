package it.unisalento.db.project.models.domain;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Job")
public class Job{

	@Id
	private ObjectId _id;

	private Date posted;
	private Date hiringDate;

	@DBRef
	private Location location;
	@DBRef
	private Platform platform;
	@DBRef
	private Company company;

	private List<String> responsibilities;
	private List<String> requirements;

	private String name;
	private String link;

	private Date firstFind;
	private Date updated;

	public Job() {}

	public Job(Date posted, String name, @Nullable Date hiringDate, @Nullable Location location, @Nullable Platform platform,
	           @Nullable Company company, List<String> responsibilities, List<String> requirements, String link,
	           @Nullable Date firstFind, @Nullable Date updated) {

		this.posted = posted;
		this.name = name;
		this.hiringDate = hiringDate;
		this.location = location;
		this.platform = platform;
		this.company = company;
		this.responsibilities = responsibilities;
		this.requirements = requirements;
		this.link = link;
		this.firstFind = firstFind;
		this.updated = updated;

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

	public Location getLocation(){
		return location;
	}

	public void setLocation(Location locationId){
		this.location = locationId;
	}

	public Platform getPlatform(){
		return platform;
	}

	public void setPlatformId(Platform platformId){
		this.platform = platformId;
	}

	public Company getCompanyId(){
		return company;
	}

	public void setCompanyId(Company companyId){
		this.company = companyId;
	}

	public List<String> getResponsibilities(){
		return responsibilities;
	}

	public void setResponsibilities(List<String> responsibilities){
		this.responsibilities = responsibilities;
	}

	public List<String> getRequirements(){
		return requirements;
	}

	public void setRequirements(List<String> requirements){
		this.requirements = requirements;
	}

	public String getLink(){
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}

	public Date getFirstFind(){
		return firstFind;
	}

	public void setFirstFind(Date firstFind){
		this.firstFind = firstFind;
	}

	public Date getUpdated(){
		return updated;
	}

	public void setUpdated(Date updated){
		this.updated = updated;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}

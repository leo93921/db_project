package it.unisalento.db.project.models.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Platform")
public class Platform{

	@Id
	private ObjectId _id;

	private String name;
	private String searchBaseUrl;

	public Platform() {}

	public Platform(String name, String searchBaseUrl) {
		this.name = name;
		this.searchBaseUrl = searchBaseUrl;
	}

	public ObjectId get_id(){
		return _id;
	}

	public void set_id(ObjectId _id){
		this._id = _id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSearchBaseUrl(){
		return searchBaseUrl;
	}

	public void setSearchBaseUrl(String searchBaseUrl){
		this.searchBaseUrl = searchBaseUrl;
	}
}

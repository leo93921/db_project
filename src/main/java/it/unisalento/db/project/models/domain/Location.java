package it.unisalento.db.project.models.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Location")
public class Location{

	@Id
	private ObjectId _id;

	private String name;

	public Location(){}

	public Location(String name){
		this.name = name;
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
}

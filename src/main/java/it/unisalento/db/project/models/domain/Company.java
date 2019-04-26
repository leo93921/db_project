package it.unisalento.db.project.models.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Company")
public class Company{
	@Id
	private ObjectId _id;

	private String name;

	private Date firstFind;


	public Company(){}

	public Company(String name){
		this.name = name;
	}

	public Company(String name, Date firstFind){
		this.name = name;
		this.firstFind = firstFind;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public ObjectId get_id(){
		return _id;
	}

	public void set_id(ObjectId _id){
		this._id = _id;
	}

	public Date getFirstFind(){
		return firstFind;
	}

	public void setFirstFind(Date firstFind){
		this.firstFind = firstFind;
	}

}

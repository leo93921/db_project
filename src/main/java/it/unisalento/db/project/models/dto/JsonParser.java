package it.unisalento.db.project.models.dto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

	public JSONObject parseToJson(String s) throws ParseException{
		JSONParser parse = new JSONParser();
		return (JSONObject) parse.parse(s);
	}

}

package com.example.demo.Services;

import java.io.*;
//import com.google.gson.Gson;    

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyService {
	
	public JSONArray readFile() {
		JSONParser jsonParser = new JSONParser();
		JSONArray employeeList = null;
        try (FileReader reader = new FileReader("F://TryJson.json"))
        {
            Object obj = jsonParser.parse(reader);
            employeeList = (JSONArray) obj;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return employeeList;
	}
	
	public void writeFile(JSONArray list, JSONObject str) {
		System.out.println("CHECKING STR: "+str);
		ObjectMapper Obj = new ObjectMapper();
		
        String jsonStr = null;
					list.add(str);
			        try (FileWriter file = new FileWriter("F://TryJson.json")) {
			            file.write(((JSONArray) list).toJSONString()); 
			            file.flush();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
	}
	
	//add a new json object.
	@SuppressWarnings("unchecked")
	public void addJson(JSONObject model){

		JSONObject obj=model;

		JSONArray list= readFile();
		writeFile(list, obj);
		System.out.println(list.get(0));

	}
	
	//read all json objects
	@SuppressWarnings("unchecked")
	public JSONArray getJson() {
		return readFile();
	}

}
package com.example.demo.Services;

import java.io.*;
//import com.google.gson.Gson;    
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyService {
	
	
	@SuppressWarnings("unchecked")
	public void addJson(JSONObject model){
		JSONObject obj=model;
			
		//extracting employeeId from postman body. (employeeId from the new record that is to be added via postman).
		String empID= (String) obj.get("empId");
		System.out.println("Checking ID: "+empID);
		String val= readRecordFile(empID);
		if(val.equals("freshRecord")) {
			// Add empId to records (perform writeOperation on record file) and create a new file with file name as empId.
			writeInRecordFile(empID);
			createNewFile(empID);
			//READ IN INTERN FILE.
			writeInInternFile(model, empID);
//			JSONArray list= readInternFile(empID);
//			writeInInternFile(list, obj, empID);
		}
		if(val.equals("alreadyExists")) {
			System.out.println("RECORD ALREADY EXISTS!!");
		}
	}		

	@SuppressWarnings("unchecked")
	public JSONObject getJson(String empId) {
		return readInternFile(empId);
	}

	//reading record file
	@SuppressWarnings("unchecked")
	public String readRecordFile(String empId) {
		JSONParser jsonParserReadRecords = new JSONParser();
		String res="";
		try(FileReader recordsReader= new FileReader("F://Assign2Data/Records.json");) 
		{
			System.out.println("RecordReader: "+recordsReader);
			System.out.println("tryCheck");
			List<String> listOfIds= new ArrayList<>();
			listOfIds = (List<String>) jsonParserReadRecords.parse(recordsReader);
			System.out.println("ListOfIds :"+listOfIds);
			if(listOfIds.size()==0) {
				res= "freshRecord";
			}
			else {
				for(int i=0; i<listOfIds.size(); i++) {
					if(empId.equals(listOfIds.get(i))) {
						res="alreadyExists";
						break;
					}
					else {
						res= "freshRecord";
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	//writing employee ids inside record file.
	@SuppressWarnings("unchecked")
	public void writeInRecordFile(String empId) {
		List<String> listOfIds = null;
		//before writing, we need to read the records file again and append the data into the list and then write that list back in records.
		JSONParser jsonParserReadRecords = new JSONParser();
		try {
			FileReader recordsReader= new FileReader("F://Assign2Data/Records.json");
			listOfIds= new ArrayList<>();
			listOfIds= (List<String>) jsonParserReadRecords.parse(recordsReader);
			listOfIds.add(empId);
			System.out.println("readingggggg: "+listOfIds);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//writing back to records file.
		try (FileWriter output= new FileWriter("F://Assign2Data/Records.json")){
			output.write(listOfIds+System.lineSeparator());
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//function to create a new file with file name as the empId.
		public void createNewFile(String empId){
			String strPath="F://Assign2Data/";
			try {
				File file1 = new File(strPath + "" + empId + ".json");
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void writeInInternFile(JSONObject model, String empID) {
			try (FileWriter file = new FileWriter("F://Assign2Data/"+empID+".json")) {
	            file.write(model.toJSONString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		

		public JSONObject readInternFile(String empId) {
			JSONParser jsonParser = new JSONParser();
			JSONObject employeeList = null;
			try (FileReader reader = new FileReader("F://Assign2Data/"+"INT"+empId+".json"))
			{
				Object obj = jsonParser.parse(reader);
				employeeList= (JSONObject) obj;
//	            employeeList = (JSONArray) obj;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return employeeList;
		}
		
//	public JSONArray readInternFile(String empId) {
//		JSONParser jsonParser = new JSONParser();
//		JSONArray employeeList = null;
//
//        try (FileReader reader = new FileReader("F://Assign2Data/"+empId))
//        {
//            Object obj = jsonParser.parse(reader);
//            employeeList = (JSONArray) obj;
//            System.out.println("DATA THAT HAS BEEN READ: "+employeeList);
//            for(int i=0; i<employeeList.size();i++) {
//            	JSONObject o= (JSONObject) employeeList.get(i);
//            	System.out.println(o.get("empId"));
//            }
//            //System.out.println("GETTING ID OF READ DATA: "+employeeList.get("empId"));
//        } catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//        return employeeList;
//	}
	
	
	//write inside the new intern file
//	public void writeInInternFile(JSONArray list, JSONObject str, String empId) {
//	    try (FileWriter file = new FileWriter("F://Assign2Data/"+empId)) {
//	           file.write(((JSONArray) list).toJSONString()); 
//	            file.flush();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	}
	

}
package com.example.demo.Controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Services.MyService;

@RestController
public class MyController {
	
	@Autowired
	MyService myService;
	
	@PostMapping("/objects")
	public void addJsonObject(@RequestBody JSONObject model) {
		myService.addJson(model);
	}
	
	@GetMapping("/objects/{empId}")
	public JSONObject getJsonObject(@PathVariable String empId) {
		return myService.getJson(empId);
	}
	
//	@PostMapping(name = "/objects/{empId}")
//	public void updateJsonObject() {
//		
//	}
//	
//	@DeleteMapping(name = "/objects/{empId}")
//	public void deleteJsonObject() {
//		
//	}
}

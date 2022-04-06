package com.example.demo.Controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Services.MyService;

@RestController
public class MyController {
	
	@Autowired
	MyService myService;
	
	@PostMapping("/objects")
	public ResponseEntity<HttpStatus> addJsonObject(@RequestBody JSONObject model) {
		int statusCode= myService.addJson(model);
		if(statusCode == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/objects/{empId}")
	public JSONObject getJsonObject(@PathVariable String empId) {
		return myService.getJson(empId);
	}
	
	@PutMapping("/objects/{empId}")
	public ResponseEntity<HttpStatus> updateJsonObject(@RequestBody JSONObject model, @PathVariable String empId) {
		int statusCode=  myService.updateEmployee(model, empId);
		if(statusCode==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/objects/{empId}")
	public ResponseEntity<HttpStatus> deleteJsonObject(@PathVariable String empId) {
		int statusCode= myService.deleteEmployeeRecord(empId);
		if(statusCode==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

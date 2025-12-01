package com.studfees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.studfees.entity.StudentFees;
import com.studfees.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService service;

	@GetMapping("/br")
	public String newstart() {
		return " start the application ..!";

	}

	@PostMapping("/adddetails")
	public StudentFees adddeatails(@RequestBody StudentFees studentfees) {
		return service.adddetails(studentfees);

	}
	
	@GetMapping("/list")
	public List<StudentFees> getStudents() {
		return service.getAllStudents();

	}
	@GetMapping("/getbystudentid/{id}")
	public StudentFees getstudentbyid(@PathVariable Long id)
	{
		return service.getbyid(id);
		
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentFees studentfees)
	
	{
		boolean updated=service.updateStudent(id, studentfees);
		if(updated)
		{
			return ResponseEntity.ok("student details updated...!");
		}else {
			
			return ResponseEntity.status(200).body("Student not found..!");
			
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteStudent(@PathVariable Long id)
	{
		return service.deletebyId(id);
		
	}
	
	
	
}

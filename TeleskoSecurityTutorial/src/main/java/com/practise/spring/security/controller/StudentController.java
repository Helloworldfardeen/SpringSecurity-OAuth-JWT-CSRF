package com.practise.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.spring.security.model.Student;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class StudentController {

	private List<Student> students = new ArrayList<>(
			List.of(new Student(49, "Fardeen Khan", 66.5), new Student(15, "Ali Saad Khan", 63.2),
					new Student(96, "Nitesh Kumar Garewhal", 61.25), new Student(92, "Owais Ali Khan ", 58.95)

			));

	@GetMapping("/studentData")
	public List<Student> getStudent() {

		return students;
	}
	
	// while add this method while enable spring security  method like put post will not work 
	// due to CSRF token is not there lets check...
	@PostMapping("/studentAdd")
	public Student addStudentData(@RequestBody Student student) {
		
		students.add(student);
		return student;
	}
	

}

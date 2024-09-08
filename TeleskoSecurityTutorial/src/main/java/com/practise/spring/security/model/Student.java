package com.practise.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Student {
	private int id;
	private String name;
	private double marks;
	

}

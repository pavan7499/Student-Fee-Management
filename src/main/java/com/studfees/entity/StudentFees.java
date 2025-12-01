package com.studfees.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentFees {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
long id;
String name;
String grade;
long number;
String sname;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public long getNumber() {
	return number;
}
public void setNumber(long number) {
	this.number = number;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public StudentFees(long id, String name, String grade, long number, String sname) {
	super();
	this.id = id;
	this.name = name;
	this.grade = grade;
	this.number = number;
	this.sname = sname;
}
@Override
public String toString() {
	return "StudentFees [id=" + id + ", name=" + name + ", grade=" + grade + ", number=" + number + ", sname=" + sname
			+ "]";
}
public StudentFees() {
	super();
	// TODO Auto-generated constructor stub
}


}

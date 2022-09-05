package com.ideas2it.model;

import java.time.LocalDate;
import java.time.Period;

public class Employee {

    private String employeeFirstName;
    private String employeeLastName;
    private long employeePhoneNumber;
    private String employeeId;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoin;
    private String employeeEmailId;

    public Employee() {
        
    }

    public Employee(String id,String firstName,String lastName,long phoneNumber,LocalDate dateOfBirth,
	     LocalDate dateOfJoin,String emailId) {

	this.employeeFirstName = firstName;
	this.employeeLastName = lastName;
	this.employeePhoneNumber = phoneNumber;
	this.employeeId = id;
	this.employeeDateOfBirth = dateOfBirth;
	this.employeeDateOfJoin = dateOfJoin;
	this.employeeEmailId = emailId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }
    
    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public long getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }    

    public String  getEmployeeId() {
        return employeeId;
    }
    
    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return employeeDateOfJoin;
    }   
    
    public String getEmployeeEmailId() {
        return employeeEmailId;
    }     

    public int getAgeFromDateOfBirth() {
        LocalDate currentDate = LocalDate.now();
        int employeeAge = Period.between(employeeDateOfBirth, currentDate).getYears();
        return employeeAge; 
    }

    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("ID :").append(getEmployeeId()).append("\nName :").append(getEmployeeFirstName())
		     .append("\nEmail ID ").append(getEmployeeEmailId()).append("\nAge :")
		     .append(getAgeFromDateOfBirth()).append("\nPhone Number :").append(getEmployeePhoneNumber());
	
	return stringBuilder.toString();
    }
}
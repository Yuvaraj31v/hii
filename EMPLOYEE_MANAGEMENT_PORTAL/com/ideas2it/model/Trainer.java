package com.ideas2it.model;

import java.time.LocalDate;
import java.util.List;

public class Trainer extends Employee {

     public Trainer(String id,String firstName,String lastName,long phoneNumber,LocalDate dateOfBirth,
	     LocalDate dateOfJoin,String emailId) {

	super(id,firstName,lastName, phoneNumber,dateOfBirth,dateOfJoin,emailId);
    }


     public Trainer() {
	super();
    }

    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("ID :").append(getEmployeeId()).append("\nName :").append(getEmployeeFirstName())
		     .append("\nEmail ID ").append(getEmployeeEmailId()).append("\nAge :")
		     .append(getAgeFromDateOfBirth()).append("\nPhone Number :").append(getEmployeePhoneNumber());
	
	return stringBuilder.toString();
    }
}
            

    
    
    
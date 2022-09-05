package com.ideas2it.util;

import java.time.LocalDate;

public class Utility {
    
    private final static String employeIdPrefix = "I";
    private static int count = 1; 

    public static int extractYearFromDate(String dateOfJoin) {
        String dateParts[] = dateOfJoin.split("-");
        String yearOfBirth = dateParts[0] ;
        int year = Integer.parseInt(yearOfBirth );
	return year;
    }	
	
    public static String  generateEmployeeId(int year) {
	StringBuilder employeeId = new StringBuilder();
	employeeId.append(employeIdPrefix).append(year%100).append(count);
	count=count+1;
        return employeeId.toString();
    }

    public static String generateMailId(String firstName, String lastName) {
	StringBuilder employeeEmailId = new StringBuilder();
	employeeEmailId.append(firstName.toLowerCase()).append(".")
	               .append(lastName.toLowerCase()).append("@ideas2it.com");   
	return employeeEmailId.toString();
    } 
                  
}

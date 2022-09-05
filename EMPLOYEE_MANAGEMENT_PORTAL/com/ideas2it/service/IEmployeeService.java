package com.ideas2it.service;

import java.util.List; 

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.IEmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;


public interface IEmployeeService<T extends Employee> {

    public void addEmployee(T employee);

    public List<T> getAllEmployees(); 
   
    public T getEmployeeById(String employeeId);    

    public void association(String employeeId ,String employeeID);

    public List<T> displayTrainersAndTrainees(String id);

    public void updateEmployeePhoneNumber(String employeeId,long phoneNumber);

    public void deleteEmployeeById(String employeId);

      
    

} 


   
		 
	
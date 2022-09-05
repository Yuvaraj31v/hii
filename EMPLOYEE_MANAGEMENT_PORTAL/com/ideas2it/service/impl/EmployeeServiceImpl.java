package com.ideas2it.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.IEmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.service.IEmployeeService;

public class EmployeeServiceImpl<T extends Employee> implements IEmployeeService<T> {   
	
    private static IEmployeeDao<Trainer> trainerEmployeeDao = new EmployeeDaoImpl(new Trainer());
    private static IEmployeeDao<Trainee> traineeEmployeeDao = new EmployeeDaoImpl(new Trainee());

    private T value;

    public EmployeeServiceImpl(T value) {
	this.value = value;
    }
   
    public void addEmployee(T employee) {
	if (employee instanceof Trainer) { 
	    trainerEmployeeDao.insertEmployee((Trainer)employee);

        } else {
	    traineeEmployeeDao.insertEmployee((Trainee)employee);
	}
    }
    
    public List<T> getAllEmployees() {
        if (value instanceof Trainer) {
            return (List<T>)trainerEmployeeDao.retrieveAllEmployees();
	
        } else {
	    return (List<T>)traineeEmployeeDao.retrieveAllEmployees();
        }
    }

    
    public T getEmployeeById(String employeeId) {
	if (value instanceof Trainer ) {
       	     return (T)trainerEmployeeDao.retrieveEmployeeById(employeeId);

        } else {
	     return (T)traineeEmployeeDao.retrieveEmployeeById(employeeId);
	    
        }
    }

    public void association(String employeeId ,String employeeID) {
	if (value instanceof Trainer) {
	    trainerEmployeeDao.association(employeeId,employeeID);
	}
	else {
	    traineeEmployeeDao.association(employeeId,employeeID);
	}    
    }


    public List<T> displayTrainersAndTrainees(String id) {
	if (value instanceof Trainer) {	
            return (List<T>)trainerEmployeeDao.displayTrainersAndTrainees(id);
	}
	else {
	    return (List<T>)traineeEmployeeDao.displayTrainersAndTrainees(id);
	}
   
    }

    public void  deleteEmployeeById(String employeId) {
        if (value instanceof Trainer) {
	    trainerEmployeeDao.removeEmployeeById(employeId);
	}
	else {
	    traineeEmployeeDao.removeEmployeeById(employeId);
	}
    }	    


    public void updateEmployeePhoneNumber(String employeeId,long phoneNumber) {

	if (value instanceof Trainer) {
	    trainerEmployeeDao.updateEmployeePhoneNumber(employeeId,phoneNumber);
 	}
	else {
	    traineeEmployeeDao.updateEmployeePhoneNumber(employeeId,phoneNumber);
	}
    }    	
}
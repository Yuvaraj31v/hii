package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;

public interface IEmployeeDao<T extends Employee> {

    public void insertEmployee(T t);

    public List<T> retrieveAllEmployees();

    public T retrieveEmployeeById(String employeeId);

    public void association(String employeeId,String employeeID);

    public List<T> displayTrainersAndTrainees(String id);

    public void updateEmployeePhoneNumber(String employeeId,long phoneNumber);

    public void removeEmployeeById(String employeeId);

}  

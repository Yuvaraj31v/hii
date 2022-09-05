package com.ideas2it.dao.impl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

import com.ideas2it.singleton.ConnectionEstablishment;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.dao.IEmployeeDao;

public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> { 
  
    private T value;

    public EmployeeDaoImpl(T value) {
	this.value = value;
    }
    
    public void insertEmployee(T employee) {
	if (employee instanceof Trainer)  {
	    String insertTrainer = "insert into Trainers(ID,First_Name,LAST_Name, Phone_Number,Date_of_Birth,Date_of_Join,Email_Id)"+ "values(?,?,?,?,?,?,?)";
	    try (Connection connect = ConnectionEstablishment.getConnection();PreparedStatement pst = connect.prepareStatement(insertTrainer);) {	            
		java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(employee.getEmployeeDateOfBirth());
		java.sql.Date sqlDateOfJoin = java.sql.Date.valueOf(employee.getEmployeeDateOfJoin());
	        pst.setString(1,employee.getEmployeeId()); 
		pst.setString(2,employee.getEmployeeFirstName());
		pst.setString(3,employee.getEmployeeLastName());
		pst.setLong(4,employee.getEmployeePhoneNumber());
		pst.setDate(5,sqlDateOfBirth );
		pst.setDate(6,sqlDateOfJoin );
		pst.setString(7,employee.getEmployeeEmailId());
	        pst.executeUpdate();
	    }

	    catch(SQLException e) {
	       e.printStackTrace();
	    }
        } else {
            try (Connection connect = ConnectionEstablishment.getConnection();) {	    
	        String insertTrainee = "insert into Trainees(ID,First_Name,LAST_Name, Phone_Number,Date_of_Birth,Date_of_Join,Email_Id)"+ "values(?,?,?,?,?,?,?)";
	        PreparedStatement pst = connect.prepareStatement(insertTrainee);
		java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(employee.getEmployeeDateOfBirth());
		java.sql.Date sqlDateOfJoin = java.sql.Date.valueOf(employee.getEmployeeDateOfJoin()); 
	        pst.setString(1,employee.getEmployeeId()); 
		pst.setString(2,employee.getEmployeeFirstName());
		pst.setString(3,employee.getEmployeeLastName());
		pst.setLong(4,employee.getEmployeePhoneNumber());
		pst.setDate(5,sqlDateOfBirth);
		pst.setDate(6,sqlDateOfJoin);
		pst.setString(7,employee.getEmployeeEmailId());
	        pst.executeUpdate();
	    }

	    catch(SQLException e) {
	       e.printStackTrace();
	    }
        }
    }
    
    public List<T> retrieveAllEmployees() {
        if (value instanceof Trainer) {
	    List<Trainer> trainers = new LinkedList();
	    try(Connection connect = ConnectionEstablishment.getConnection();Statement statement = connect.createStatement();ResultSet rs = statement.executeQuery("select * from Trainers");){			    	    
	        while(rs.next()) {
	            Trainer trainer = new Trainer(rs.getString("Id"),rs.getString("First_Name"),rs.getString("LAST_Name"),rs.getLong("Phone_Number"),rs.getDate("Date_of_Birth").toLocalDate(),rs.getDate("Date_of_Join").toLocalDate(),rs.getString("Email_Id"));
		    trainers.add(trainer);

	        }        
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }
	    return (List<T>)trainers;        
	} else {
	    List<Trainee> trainees = new LinkedList();

	    try(Connection connect = ConnectionEstablishment.getConnection();Statement statement = connect.createStatement();ResultSet rs = statement.executeQuery("select * from Trainees");){			    	    	       
	        while(rs.next()) {
	            Trainee trainee = new Trainee(rs.getString("Id"),rs.getString("First_Name"),rs.getString("LAST_Name"),rs.getLong("Phone_Number"),rs.getDate("Date_of_Birth").toLocalDate(),rs.getDate("Date_of_Join").toLocalDate(),rs.getString("Email_Id"));
		    trainees.add(trainee);
	        }        
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }		
	    return (List<T>)trainees;
         }
    }

    public T retrieveEmployeeById(String employeeId) {

	if (value instanceof Trainer) {	    
	    String displayQuery = " select * from trainers "+" where ID = ? ";
	    ResultSet rs = null;
	    List<Trainer> specificTrainer = new LinkedList();    
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(displayQuery);) {			    	    
	        pst.setString(1,employeeId); 
	        rs = pst.executeQuery();
	        while(rs.next()) {
	           Trainer trainer = new Trainer(rs.getString("Id"),rs.getString("First_Name"),rs.getString("LAST_Name"),rs.getLong("Phone_Number"),rs.getDate("Date_of_Birth").toLocalDate(),rs.getDate("Date_of_Join").toLocalDate(),rs.getString("Email_Id"));
		   specificTrainer.add(trainer);
	        }    
	     }catch(SQLException e) {
	       e.printStackTrace();
	    }
	    Trainer choosedTrainer = null;
	     for (Trainer trainer: specificTrainer) {
	        if ((trainer.getEmployeeId()).equals(employeeId)) {
	            choosedTrainer = trainer;
	        }
	    }
	     	     
	    return (T)choosedTrainer;
        } else {
	    String displayQuery = " select * from trainees "+" where ID = ? ";
	    ResultSet rs = null;
	    List<Trainee> specificTrainee = new LinkedList();
	        
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(displayQuery);) {			    	    
	        pst.setString(1,employeeId); 
	        rs = pst.executeQuery();
	        while(rs.next()) {
	            Trainee trainee = new Trainee(rs.getString("Id"),rs.getString("First_Name"),rs.getString("LAST_Name"),rs.getLong("Phone_Number"),rs.getDate("Date_of_Birth").toLocalDate(),rs.getDate("Date_of_Join").toLocalDate(),rs.getString("Email_Id"));
		    specificTrainee.add(trainee);
	        }
	    }catch(SQLException e) {
	       e.printStackTrace();
	    }
	    Trainee choosedTrainee = null;
	    for (Trainee trainee: specificTrainee) {
	        if ((trainee.getEmployeeId()).equals(employeeId)) {
	            choosedTrainee = trainee;
	        }
	    }
	    return (T)choosedTrainee;
        }
    }

    public void association(String employeeId,String employeeID) {

	if (value instanceof Trainer) {
	    String associateQuery = "insert into Employees(Trainer_id, Trainee_id)"+ "values(?,?)";
            try (Connection connect = ConnectionEstablishment.getConnection();PreparedStatement pst = connect.prepareStatement(associateQuery );) {	      
	        pst.setString(1,employeeId); 
	        pst.setString(2,employeeID);
	        pst.executeUpdate();
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }	
        }
	else {	    
	    String associateQuery  = "insert into Employees(Trainer_id, Trainee_id)"+ "values(?,?)";
	    try (Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(associateQuery );) {	    
	        pst.setString(1,employeeID); 
	        pst.setString(2,employeeId);
	        pst.executeUpdate();
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }
	}
    }	

    public List<T> displayTrainersAndTrainees(String id) {
	
	if (value instanceof Trainer) {
            List<T> associationList = new LinkedList();
	    ResultSet rs = null;
	    String displayQuery = " select * FROM Trainers INNER JOIN Employees ON Trainers.id = Employees.Trainer_id INNER JOIN Trainees ON Trainees.id = Employees.Trainee_id "+" where Employees.Trainer_id = ? ";
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(displayQuery);) {			    	    
	        pst.setString(1,id); 
	        rs = pst.executeQuery();
		while(rs.next()) {
		    Trainer trainer = new Trainer(rs.getString("Trainers.Id"),rs.getString("Trainers.First_Name"),rs.getString("Trainers.LAST_Name"),rs.getLong("Trainers.Phone_Number"),rs.getDate("Trainers.Date_of_Birth").toLocalDate(),rs.getDate("Trainers.Date_of_Join").toLocalDate(),rs.getString("Trainers.Email_Id"));
		    associationList.add((T)trainer); 	   		   
		    Trainee trainee = new Trainee(rs.getString("Trainees.Id"),rs.getString("Trainees.First_Name"),rs.getString("Trainees.LAST_Name"),rs.getLong("Trainees.Phone_Number"),rs.getDate("Trainees.Date_of_Birth").toLocalDate(),rs.getDate("Trainees.Date_of_Join").toLocalDate(),rs.getString("Trainees.Email_Id"));
		    associationList.add((T)trainee);  
	        }		        
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }
            return (List<T>)associationList;
        }
	else {

	    List<T> associationList = new LinkedList();
	    ResultSet rs = null;
	    String displayQuery = " select * FROM Trainers INNER JOIN Employees ON Trainers.id = Employees.Trainer_id INNER JOIN Trainees ON Trainees.id = Employees.Trainee_id "+" where Employees.Trainee_id = ? ";
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(displayQuery);) {
	        pst.setString(1,id); 
	        rs = pst.executeQuery(); 	        
		while(rs.next()){
		    Trainee trainee = new Trainee(rs.getString("Trainees.Id"),rs.getString("Trainees.First_Name"),rs.getString("Trainees.LAST_Name"),rs.getLong("Trainees.Phone_Number"),rs.getDate("Trainees.Date_of_Birth").toLocalDate(),rs.getDate("Trainees.Date_of_Join").toLocalDate(),rs.getString("Trainees.Email_Id"));
		    associationList.add((T)trainee);		    
		    Trainer trainer = new Trainer(rs.getString("Trainers.Id"),rs.getString("Trainers.First_Name"),rs.getString("Trainers.LAST_Name"),rs.getLong("Trainers.Phone_Number"),rs.getDate("Trainers.Date_of_Birth").toLocalDate(),rs.getDate("Trainers.Date_of_Join").toLocalDate(),rs.getString("Trainers.Email_Id"));
		    associationList.add((T)trainer);  
	        }		        
	    }
	    catch(SQLException e) {
	       e.printStackTrace();
	    }
            return (List<T>)associationList;
        }
    }	    

    public void updateEmployeePhoneNumber(String employeeId,long phoneNumber) {

        if (value instanceof Trainer) {
	    String updateQuery = "update Trainers set Phone_Number= ? where ID = ?";
	    try(Connection connect = ConnectionEstablishment.getConnection();PreparedStatement pst = connect.prepareStatement(updateQuery);) {
	        pst.setLong(1,phoneNumber);
		pst.setString(2,employeeId);
		pst.executeUpdate(); 
	    }
	    catch (SQLException e) {
		e.printStackTrace();
	    }
	}else {
	    String updateQuery = "update Trainees set Phone_Number= ? where ID = ?";
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement pst = connect.prepareStatement(updateQuery);) {	        
		pst.setLong(1,phoneNumber);
		pst.setString(2,employeeId); 
		pst.executeUpdate(); 
	    }
	    catch (SQLException e) {
		e.printStackTrace();
	    }
	}       
    }


    public void removeEmployeeById(String employeeId) {
	if (value instanceof Trainer) {
	    String deleteEmployee = "DELETE FROM EMPLOYEES WHERE Trainer_id = ?";
	    String deleteTrainer =  "DELETE FROM TRAINERS WHERE ID = ?";
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement employeeStatement = connect.prepareStatement(deleteEmployee);PreparedStatement trainerStatement = connect.prepareStatement(deleteTrainer);) {	        
		employeeStatement.setString(1,employeeId); 
		employeeStatement.executeUpdate();
		trainerStatement.setString(1,employeeId); 
		trainerStatement.executeUpdate();
		  
	    }
	    catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	else {
	    String deleteEmployee = "DELETE FROM EMPLOYEES WHERE Trainee_id = ?";
	    String deleteTrainee =  "DELETE FROM TRAINEES WHERE ID = ?";
	    try(Connection connect = ConnectionEstablishment.getConnection(); PreparedStatement employeeStatement = connect.prepareStatement(deleteEmployee);PreparedStatement traineeStatement = connect.prepareStatement(deleteTrainee);) {	        
		employeeStatement.setString(1,employeeId); 
		employeeStatement.executeUpdate();
		traineeStatement.setString(1,employeeId); 
		traineeStatement.executeUpdate();  
	    }
	    catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }		     	               
}

    
   
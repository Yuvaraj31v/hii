package com.ideas2it.controller;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.service.IEmployeeService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.util.Utility;
import com.ideas2it.exception.EmptyListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController {

    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private IEmployeeService<Trainer> trainerEmployeeService = new EmployeeServiceImpl(new Trainer());
    private IEmployeeService<Trainee> traineeEmployeeService = new EmployeeServiceImpl(new Trainee());

    public static void main( String[] args) {
        EmployeeController controller = new EmployeeController ();
	System.out.println("Welcome to Ideas2IT Employee management portal!!\n");
        controller.init();
    }

    public void init() {
	boolean isContinue = true;
        while (isContinue) {
	    try {		
                logger.info("Enter 1 to add Employee");
            	logger.info("Enter 2 to display Employe ");
	    	logger.info("Enter 3 to asociate Employee");
	    	logger.info("Enter 4 to display Trainers and Trainees");
	    	logger.info("Press 5 to update Employee Phone Number");
	    	logger.info("Press 6 to delete Employee records");	    
            	logger.info("Enter any other to exit");
	    	int userChoice = scanner.nextInt();	
	    	switch (userChoice) {

            	case 1: 
	            createEmployee();
	            break;
    
	        case 2: 
	            logger.info("Press 1 to display trainer details");
	            logger.info("Press 2 to display trainee details");
	            logger.info("Press 3 to dispaly Trainer or Trainee by entering their EmployeId"); 
	            int usersChoice = scanner.nextInt();
	            if (usersChoice == 1 ) {
			try {
	                    displayAllTrainers();
			}
			catch (EmptyListException  e ) {
			    logger.info(e.getMessage());
			}
		    } else if (usersChoice == 2) {
	                displayAllTrainees();
		    } else if(usersChoice == 3) {
	                displayEmployeById();
		    }
                    break;
	    
	       case 3:
                    associateTrainersAndTrainees();
	            break;

                case 4:
		    displayTrainersAndTrainees();
		    break;
		
	        case 5:
		    updateEmployeePhoneNumber();
		    break;

	        case 6 :
		    deleteEmployeeById ();
		    break;
		
	        default:
	            isContinue = false;
	      
       	        }
	    }
	    catch (InputMismatchException ex) {
		scanner.next();
		logger.info("Invalid Input please Enter again");
	    }
        }
    }
	
    public void createEmployee() throws InputMismatchException{
	String employeeFirstName,employeeLastName,employeeDateOfBirth,employeeDateOfJoin;
	long employeePhoneNumber;	       
	logger.info("Press 1 to add Trainer details");
	logger.info("Press 2 to add Trainees details");
        int userChoice = scanner.nextInt();
	if (userChoice == 1 || userChoice == 2 ) {
	    logger.info("Enter Number of detail to add");
        int employeesToAdd = scanner.nextInt();
	        
            for (int i = 0; i < employeesToAdd; i++) {
 	        logger.info("Enter First name");
	        employeeFirstName = scanner.next();
	        logger.info("Enter Last name");
	        employeeLastName = scanner.next();
	        logger.info("Enter DOB in YYYY-MM-DD format");
	        employeeDateOfBirth= scanner.next();
	        LocalDate dateOfBirth = LocalDate.parse(employeeDateOfBirth);
	        logger.info("Enter PhoneNumber");
	        employeePhoneNumber = scanner.nextLong();	    	    	               
	        logger.info("Enter DateOfJoin");
	        employeeDateOfJoin = scanner.next();	    
	        LocalDate dateOfJoin = LocalDate.parse(employeeDateOfJoin);
	        int yearOfJoin = Utility.extractYearFromDate(employeeDateOfJoin);
	        String employeeId = Utility.generateEmployeeId(yearOfJoin);
	        String emailId = Utility.generateMailId(employeeFirstName,employeeLastName);
	        logger.info("Your employee id is "+ employeeId+"\n"+" Email id is"+ emailId);
	        logger.info("Kindly take a note of it");
	 
	        if (userChoice == 1) {			

		    Trainer trainer = new Trainer(employeeId,employeeFirstName ,employeeLastName, employeePhoneNumber,dateOfBirth,dateOfJoin,emailId);
		    trainerEmployeeService.addEmployee(trainer);

	        } else if ( userChoice == 2 ) {

		    Trainee trainee = new Trainee(employeeId,employeeFirstName ,employeeLastName, employeePhoneNumber,dateOfBirth,dateOfJoin,emailId);
		    traineeEmployeeService.addEmployee(trainee); 
	        }
	    }
        }
	else {
	    logger.info("Invalid input ");
	}
    }  

    public void displayAllTrainers() throws EmptyListException{

	if (trainerEmployeeService.getAllEmployees().size() == 0) {
	    throw new EmptyListException("List is Empty");
	}
	else {
	    for (Trainer trainer : trainerEmployeeService.getAllEmployees() ) {
	        logger.info(trainer.toString());
            }
	}    	
    }

    public void displayAllTrainees() {

	for (Trainee trainee : traineeEmployeeService.getAllEmployees() ) {
	    logger.info(trainee.toString());
        }
    } 

    public void  displayEmployeById() {
	
	    logger.info("Press 1 to display respective Trainer by entering their ");
	    logger.info("Press 2 to display respective Trainee by entering their");
	    int choiceOfUser = scanner.nextInt();
	    if (choiceOfUser == 1) {
		String employeeId= getEmployeeIdFromUser();
	        if (trainerEmployeeService.getEmployeeById(employeeId) != null) {
	            Trainer trainer = trainerEmployeeService.getEmployeeById(employeeId);
	            logger.info(trainer.toString());
	        }
	        else {
		     logger.info("NO SUCH ID");
	        }
	    } 
	    else if (choiceOfUser == 2) {
		String employeeId= getEmployeeIdFromUser();
	        if (traineeEmployeeService.getEmployeeById(employeeId) != null) {
	            Trainee trainee = traineeEmployeeService.getEmployeeById(employeeId);
	            logger.info(trainee.toString());
	        }
	        else {
		    logger.info("NO SUCH ID");
	        }
	    }
    }

    public String getEmployeeIdFromUser() {
	logger.info("Enter Employee Id");
	String employeeId = scanner.next();
	return employeeId;
    }


   public void associateTrainersAndTrainees() throws InputMismatchException {
	logger.info("Enter 1 to add trainees to trainer");
	logger.info("Enter 2 to add trainers to trainee");
	
	int choiceOfUser = scanner.nextInt();	
	if (choiceOfUser == 1) {	
	    logger.info("Enter Trainer id");
	    String trainerEmployeId = scanner.next();	    
	    logger.info("Enter your Trainee ID");	
	    String[] traineeIds = scanner.next().split("-");
	    for (int i = 0; i < traineeIds.length; i++) {
	        trainerEmployeeService.association(trainerEmployeId,traineeIds[i]);	        
	    }	
   	}

	else if (choiceOfUser  == 2 ) {
	    logger.info("Enter Trainee id");
	    String traineeEmployeId = scanner.next();	    
	    logger.info("Enter your Trainer ID seprated by -");	
	    String[] trainerIds = scanner.next().split("-");
	    for (int i = 0; i < trainerIds.length; i++) {
	        traineeEmployeeService.association(traineeEmployeId,trainerIds[i]);	        
	    }	
   	}	    
    }

    public void displayTrainersAndTrainees() throws InputMismatchException {

	
	    logger.info("Enter 1 to get details by trainer Id ");
	    logger.info("Enter 2 to get details by Trainee Id ");
	    int choice = scanner.nextInt();
	    if (choice == 1) {
		logger.info("Enter Trainer Id");
	        String employeeId = scanner.next();
	        int count = 1 ;
                for (Employee employee : trainerEmployeeService.displayTrainersAndTrainees(employeeId)) {
	            if (employee  instanceof Trainer) {
		        if (count == 1 ) {
	                    logger.info("Trainer details "+employee.toString());
			    count++;
		        }
	            }
	            else {
		        logger.info("Trainee details  \n");
		        logger.info(employee.toString());
	            }		
                }
            }
	    else if (choice == 2 ) {
		logger.info("Enter Trainer Id");
	        String employeeId = scanner.next();
		int count = 1 ;
                for (Employee employee : traineeEmployeeService.displayTrainersAndTrainees(employeeId)) {
	            if (employee instanceof Trainee) {
		        if (count == 1) {
	                    logger.info("Trainee details "+employee.toString());
			    count++;
		        }
	            }
	            else {
		        logger.info("Trainer details");
		        logger.info(employee.toString());
	            }		
                }
	    }
       
    }
	    
    public void updateEmployeePhoneNumber() throws InputMismatchException {
	logger.info("Enter 1 to update Trainer phoneNumber");
	logger.info("Enter 2 to update Trainee phoneNumber");
	int choice = scanner.nextInt();
	
	if (choice == 1) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (Trainer trainer: trainerEmployeeService.getAllEmployees()) {
	        if ((trainer.getEmployeeId()).equals(employeeId)) {
		    logger.info("Enter phoneNumber to update"); 
	            long employeePhoneNumber = scanner.nextLong();
	            trainerEmployeeService.updateEmployeePhoneNumber(employeeId,employeePhoneNumber);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	    
	}
	else if(choice == 2) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (Trainee trainee: traineeEmployeeService.getAllEmployees()) {
	        if ((trainee.getEmployeeId()).equals(employeeId)) {
		    logger.info("Enter phoneNumber to update"); 
	            long employeePhoneNumber = scanner.nextLong();
	            traineeEmployeeService.updateEmployeePhoneNumber(employeeId,employeePhoneNumber);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
    }

    public void deleteEmployeeById () throws InputMismatchException  {
	logger.info("Enter 1 to delete Trainer details");
	logger.info("Enter 2 to delete Trainee details");
	int choice = scanner.nextInt();
	if (choice == 1) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (Trainer trainer: trainerEmployeeService.getAllEmployees()) {
	        if ((trainer.getEmployeeId()).equals(employeeId)) {
		    trainerEmployeeService.deleteEmployeeById(employeeId);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
	else if(choice == 2) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (Trainee trainee: traineeEmployeeService.getAllEmployees()) {
	        if ((trainee.getEmployeeId()).equals(employeeId)) {
		    traineeEmployeeService.deleteEmployeeById(employeeId);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
    }
}
	    
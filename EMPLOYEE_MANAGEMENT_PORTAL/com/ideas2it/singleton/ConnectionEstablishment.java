package com.ideas2it.singleton;

import java.sql.Date;
import java.sql.Connection;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionEstablishment{
      
    private static String  URL = "jdbc:mysql://localhost:3306/employee_records";
    private static String  UNAME = "root";
    private static String  PASSWORD = "Sitsit555@";
    private static Connection connect = null;
    private static ConnectionEstablishment  connectionEstablishment = null;
  
    private ConnectionEstablishment() {
	try {
            connect = DriverManager.getConnection(URL,UNAME,PASSWORD);
	}
	catch(SQLException e ) {
	    e.printStackTrace();
	}
    }
	
   
    public static  Connection getConnection() throws SQLException {

	if (connect == null || connect.isClosed() ) {
	    connectionEstablishment  = new  ConnectionEstablishment(); 
	}  	
	return connect;
	
    }
}

  
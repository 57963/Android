package com.quiz.jdbc;
 
import java.sql.*;
import java.util.ArrayList;

 public class JDBC {
	static Connection conn = null;
 	static String dbURL = "jdbc:postgresql://localhost:5432/Quiz";
 	static String user = "postgres";
 	static String pass = "root";
 	static Statement stat;
 	static ResultSet result;
    public static void main(String[] args) throws SQLException {
    	setup();
    	setRecord(1,"ollie","pass1");
    	System.out.print(getColumnArray("username"));
    	conn.close();
    }
    
    public static void setup() throws SQLException{
    	conn = DriverManager.getConnection(dbURL, user, pass);
    	stat = conn.createStatement();
    }
    
    public static void setRecord(int id, String username, String password) throws SQLException{
    	if(username != ""){
    		stat.executeUpdate("update userstore set username='"+username+"' where id='"+id+"'");
        }
    	if(password != ""){
    		stat.executeUpdate("update userstore set password='"+password+"' where id='"+id+"'");
        }
    }
 
    public static ArrayList <String> getColumnArray(String columnName) throws SQLException{
    	ArrayList <String> out = new ArrayList <String>();
    	result = stat.executeQuery("select "+columnName+" from userstore");
    	while(result.next()){
    		out.add(result.getString(1));
    	}
		return out;
    }
 }
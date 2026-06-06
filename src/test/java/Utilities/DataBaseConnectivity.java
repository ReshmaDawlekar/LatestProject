package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DataBaseConnectivity {
	
	 private static final String URL= "jdbc:mysql://localhost:8080/mydb";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "password";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    }
	    public static void main(String[] args) {
	        try (Connection conn = getConnection()) {
	            System.out.println("Connected: " + !conn.isClosed());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public HashMap<String,String> returnQueryResults(String query)
	    {
			HashMap<String, String> dataFromDB = new HashMap<String, String>();
			try (Connection conn = getConnection()) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					dataFromDB.put(rs.getString("column1"), rs.getString("column2"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dataFromDB;
	    }
	}



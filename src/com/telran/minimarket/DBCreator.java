package com.telran.minimarket;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCreator {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "";
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement st = conn.createStatement();	
//		String query = "create database market";	
		String query = "use market";
		st.executeUpdate(query);
		query = "drop table milkproduct";
		st.executeUpdate(query);
		query = "CREATE TABLE milkproduct ("
				+ "groups         INTEGER,"
				+ "name      VARCHAR(254),"
				+ "code           INTEGER,"
				+ "munit     VARCHAR(254),"
				+ "price      DOUBLE(9,2),"
				+ "origin    VARCHAR(254),"
				+ "calories       INTEGER,"
				+ "fatness    DOUBLE(5,2),"
				+ "temperature    INTEGER,"
				+ "kosher         BOOLEAN,"
				+ "expdate         BIGINT,"
				+ "quantity      INTEGER);";
		st.executeUpdate(query);
		
		
	}
}

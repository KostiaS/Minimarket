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
//		String query = "create database minimarket";	
		String query = "USE minimarket";
		st.executeUpdate(query);
//		query = "DROP TABLE milkproduct";
//		st.executeUpdate(query);
/*		query = "CREATE TABLE milkproduct ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "origin       VARCHAR(254),"
				+ "calories          INTEGER,"
				+ "fatness       DOUBLE(5,2),"
				+ "temperature       INTEGER,"
				+ "kosher            BOOLEAN,"
				+ "expdate            BIGINT,"
				+ "quantity          INTEGER)";*/
		/*query = "CREATE TABLE meatproduct ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "meat_type    VARCHAR(254),"
				+ "calories          INTEGER,"
				+ "temperature       INTEGER,"
				+ "kosher            BOOLEAN,"
				+ "expdate            BIGINT,"
				+ "quantity          INTEGER)";*/
		/*query = "CREATE TABLE drinkproduct ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "calories          INTEGER,"
				+ "temperature       INTEGER,"
				+ "kosher            BOOLEAN,"
				+ "expdate            BIGINT,"
				+ "fizzy	         BOOLEAN,"
				+ "quantity          INTEGER)";*/
		/*query = "CREATE TABLE alcohol_drinkproduct ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "calories          INTEGER,"
				+ "temperature       INTEGER,"
				+ "kosher            BOOLEAN,"
				+ "expdate            BIGINT,"
				+ "fizzy		     BOOLEAN,"
				+ "volume	     DOUBLE(9,2),"
				+ "age			     INTEGER,"
				+ "quantity          INTEGER)";*/
		/*query = "CREATE TABLE bakeryproduct ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "flour_type   VARCHAR(254),"
				+ "calories          INTEGER,"
				+ "temperature       INTEGER,"
				+ "kosher            BOOLEAN,"
				+ "expdate            BIGINT,"
				+ "quantity          INTEGER)";*/
		/*query = "CREATE TABLE home_chemistry ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "poison            BOOLEAN,"
				+ "quantity          INTEGER)";*/
		query = "CREATE TABLE home_tools ("
				+ "group_type        INTEGER,"
				+ "name         VARCHAR(254),"
				+ "code              INTEGER,"
				+ "munit        VARCHAR(254),"
				+ "price         DOUBLE(9,2),"
				+ "area         VARCHAR(254),"
				+ "quantity          INTEGER)";
		st.executeUpdate(query);
	}
}

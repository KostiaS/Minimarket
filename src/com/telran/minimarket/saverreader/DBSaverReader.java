package com.telran.minimarket.saverreader;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import com.telran.minimarket.products.MilkProduct;
import com.telran.minimarket.products.Product;

public class DBSaverReader implements ISaverReader {
	private static final String MILK_TABLE_NAME = "milkproduct";
	private String url = null;
	private String user = null;
	private String password = null;
	private Statement state = null;
	
	public DBSaverReader(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
		boolean result = dbConnect(url, user, password);
		if(!result)
			System.err.println("Please check database and try again");
	}
	private boolean dbConnect(String url, String user, String password){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection conn = DriverManager.getConnection(url, user, password);
			state = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Error in DB connection !!!");
			return false;
		}
		return true;
	}
	public void save(Map<Product, Integer> collection) {
		Set<Product> keys = collection.keySet();
		for(Product product : keys){
			int group = product.getGroupType();
			String query = null;
			switch(group) {
			case MilkProduct.MILK_GROUP_TYPE: 
				String sQuery = "select code from " + MILK_TABLE_NAME 
								+ " where code =" + product.getCode();
				ResultSet rs = null;
				try {
					rs = state.executeQuery(sQuery);
					rs.last();							
					int count = rs.getRow();
					if(count != 0){						
						sQuery = "update " + MILK_TABLE_NAME + " set " + "quantity=" + collection.get(product) + " where code=" + 
							product.getCode();
						state.executeUpdate(sQuery);
					}
					else {
						sQuery = prepareMilkProductQuery(product);
						query = "insert into " + MILK_TABLE_NAME + sQuery
								+ collection.get(product) + ");";
						state.executeUpdate(query);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private String prepareMilkProductQuery(Product prod) {
		MilkProduct product = (MilkProduct)prod;
		String query = " (group_type, name, code, munit, price, origin, "
				+ "calories, fatness, temperature, kosher, expdate, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + "'" + product.getOrigin() + "'"
				+ "," + product.getCalories()
				+ "," + product.getFatness()
				+ "," + product.getTemperature()
				+ "," + product.isKosher()
				+ "," + product.getExpDate().getTime()
				+ "," ;				
		return query;
	}
	public void read(Map<Product, Integer> collection) {	
		readMilkProducts(collection);	
	}
	private void readMilkProducts(Map<Product, Integer> collection){
		String query = "select * from " + MILK_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
//				int groupType = rs.getInt("group_type");
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				String origin = rs.getString("origin");
				int calories = rs.getInt("calories");
				double fatness = rs.getInt("fatness");
				int temperature = rs.getInt("temperature");
				boolean kosher = rs.getBoolean("kosher");
				Date expDate = new Date(rs.getLong("expdate"));
				int quantity = rs.getInt("quantity");
				MilkProduct mp = new MilkProduct(name, price, code, mUnit, expDate,
						calories, kosher, temperature, fatness, origin);
				collection.put(mp, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}

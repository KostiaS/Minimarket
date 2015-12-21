package com.telran.minimarket.saverreader;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import com.telran.minimarket.products.AlcoholDrinkProduct;
import com.telran.minimarket.products.BakeryProduct;
import com.telran.minimarket.products.DrinkProduct;
import com.telran.minimarket.products.HomeChemistry;
import com.telran.minimarket.products.HomeTools;
import com.telran.minimarket.products.MeatProduct;
import com.telran.minimarket.products.MilkProduct;
import com.telran.minimarket.products.Product;

public class DBSaverReader implements ISaverReader {
	private static final String MILK_TABLE_NAME = "milkproduct";
	private static final String MEAT_TABLE_NAME = "meatproduct";
	private static final String DRINK_TABLE_NAME = "drinkproduct";
	private static final String ALCOHOL_DRINK_TABLE_NAME = "alcohol_drinkproduct";
	private static final String BAKERY_TABLE_NAME = "bakeryproduct";
	private static final String HOME_CHEMISTRY_TABLE_NAME = "home_chemistry";
	private static final String HOME_TOOLS_TABLE_NAME = "home_tools";
//	private String url = null;
//	private String user = null;
//	private String password = null;
	private Statement state = null;
	
	public DBSaverReader(String url, String user, String password){
//		this.url = url;
//		this.user = user;
//		this.password = password;
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
		for(Product product: keys){
			int group = product.getGroupType();
			switch(group) {
			case MilkProduct.MILK_GROUP_TYPE: 
				saveMilkProduct(collection, product);
				break;
			case MeatProduct.MEAT_GROUP_TYPE:
				saveMeatProduct(collection, product);
				break;
			case DrinkProduct.DRINK_GROUP_TYPE:
				saveDrinkProduct(collection, product);
				break;
			case AlcoholDrinkProduct.ALCOHOL_GROUP_TYPE:
				saveAlcoholDrinkProduct(collection, product);
				break;
			case BakeryProduct.BAKERY_GROUP_TYPE:
				saveBakeryProduct(collection, product);
				break;
			case HomeChemistry.CHEMISTRY_GROUP_TYPE:
				saveHomeChemistry(collection, product);
				break;
			case HomeTools.HOME_TOOLS_GROUP_TYPE:
				saveHomeTools(collection, product);
				break;
			default:
				break;
			}
		}
	}
	private void saveMilkProduct(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + MILK_TABLE_NAME 
				+ " WHERE code =" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();							
			if(rs.getRow() != 0){						
				query = "UPDATE " + MILK_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareMilkProductQuery(product);
				query = "INSERT INTO " + MILK_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	private void saveMeatProduct(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + MEAT_TABLE_NAME
				+ " WHERE code=" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + MEAT_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareMeatProductQuery(product);
				query = "INSERT INTO " + MEAT_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String prepareMeatProductQuery(Product prod) {
		MeatProduct product = (MeatProduct)prod;
		String query = " (group_type, name, code, munit, price, meat_type, "
				+ "calories, temperature, kosher, expdate, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + "'" + product.getMeatType() + "'"
				+ "," + product.getCalories()
				+ "," + product.getTemperature()
				+ "," + product.isKosher()
				+ "," + product.getExpDate().getTime()
				+ "," ;	
		return query;
	}
	private void saveDrinkProduct(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + DRINK_TABLE_NAME
						+ " WHERE code="  + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + DRINK_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareDrinkProductQuery(product);
				query = "INSERT INTO " + DRINK_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String prepareDrinkProductQuery(Product prod) {
		DrinkProduct product = (DrinkProduct)prod;
		String query = " (group_type, name, code, munit, price, calories, "
				+ "temperature, kosher, expdate, fizzy, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + product.getCalories()
				+ "," + product.getTemperature()
				+ "," + product.isKosher()
				+ "," + product.getExpDate().getTime()
				+ "," + product.isFizzy()
				+ ",";	
		return query;
	}
	private void saveAlcoholDrinkProduct(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + ALCOHOL_DRINK_TABLE_NAME
				+ " WHERE code=" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + ALCOHOL_DRINK_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareAlcoholDrinkProductQuery(product);
				query = "INSERT INTO " + ALCOHOL_DRINK_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String prepareAlcoholDrinkProductQuery(Product prod) {
		AlcoholDrinkProduct product = (AlcoholDrinkProduct)prod;
		String query = " (group_type, name, code, munit, price, calories, "
				+ "temperature, kosher, expdate, fizzy, volume, age, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + product.getCalories()
				+ "," + product.getTemperature()
				+ "," + product.isKosher()
				+ "," + product.getExpDate().getTime()
				+ "," + product.isFizzy()
				+ "," + product.getVolume()
				+ "," + product.getAge() + ",";	
		return query;
	}
	private void saveBakeryProduct(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + BAKERY_TABLE_NAME
				+ " WHERE code=" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + BAKERY_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareBakeryProductQuery(product);
				query = "INSERT INTO " + BAKERY_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private String prepareBakeryProductQuery(Product prod) {
		BakeryProduct product = (BakeryProduct)prod;
		String query = " (group_type, name, code, munit, price, flour_type,"
				+ "calories, temperature, kosher, expdate, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + "'" + product.getFlourType() + "'"
				+ "," + product.getCalories()
				+ "," + product.getTemperature()
				+ "," + product.isKosher()
				+ "," + product.getExpDate().getTime()
				+ ",";	
		return query;
	}
	private void saveHomeChemistry(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + HOME_CHEMISTRY_TABLE_NAME
				+ " WHERE code=" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + HOME_CHEMISTRY_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareHomeChemistryQuery(product);
				query = "INSERT INTO " + HOME_CHEMISTRY_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String prepareHomeChemistryQuery(Product prod) {
		HomeChemistry product = (HomeChemistry)prod;
		String query = " (group_type, name, code, munit, price, poison, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + product.isPoison()
				+ ",";	
		return query;
	}
	private void saveHomeTools(Map<Product, Integer> collection, Product product) {
		String query = null;
		String sQuery = "SELECT code FROM " + HOME_TOOLS_TABLE_NAME
				+ " WHERE code=" + product.getCode();
		try {
			ResultSet rs = state.executeQuery(sQuery);
			rs.last();
			if(rs.getRow() != 0) {
				query = "UPDATE " + HOME_TOOLS_TABLE_NAME + " SET quantity="
						+ collection.get(product) + " WHERE code=" + product.getCode();
				state.executeUpdate(query);
			}
			else {
				sQuery = prepareHomeToolsQuery(product);
				query = "INSERT INTO " + HOME_TOOLS_TABLE_NAME + sQuery
						+ collection.get(product) + ")";
				state.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String prepareHomeToolsQuery(Product prod) {
		HomeTools product = (HomeTools)prod;
		String query = " (group_type, name, code, munit, price, area, quantity)"
				+ " VALUES("
				+ product.getGroupType()
				+ "," + "'" + product.getName() + "'"
				+ "," + product.getCode()
				+ "," + "'" + product.getmUnit() + "'"
				+ "," + product.getPrice()
				+ "," + "'" + product.getArea() + "'"
				+ ",";
		return query;
	}
	public void read(Map<Product, Integer> collection) {	
		readMilkProducts(collection);	
		readMeatProducts(collection);
		readDrinkProducts(collection);
		readAlcoholDrinkProducts(collection);
		readBakeryProducts(collection);
		readHomeChemistry(collection);
		readHomeTools(collection);
	}
	private void readHomeTools(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + HOME_TOOLS_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				String area = rs.getString("area");
				int quantity = rs.getInt("quantity");
				HomeTools ht = new HomeTools(name, price, code, mUnit, area);
				collection.put(ht, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readHomeChemistry(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + HOME_CHEMISTRY_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				boolean isPoison = rs.getBoolean("poison");
				int quantity = rs.getInt("quantity");
				HomeChemistry hc = new HomeChemistry(name, price, code, mUnit, isPoison);
				collection.put(hc, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readBakeryProducts(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + BAKERY_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				String flourType = rs.getString("flour_type");
				int calories = rs.getInt("calories");
				int temperature = rs.getInt("temperature");
				boolean kosher = rs.getBoolean("kosher");
				Date expDate = new Date(rs.getLong("expdate"));
				int quantity = rs.getInt("quantity");
				BakeryProduct bp = new BakeryProduct(name, price, code, mUnit, expDate,
						calories, kosher, temperature, flourType);
				collection.put(bp, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readAlcoholDrinkProducts(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + ALCOHOL_DRINK_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				int calories = rs.getInt("calories");
				int temperature = rs.getInt("temperature");
				boolean kosher = rs.getBoolean("kosher");
				Date expDate = new Date(rs.getLong("expdate"));
				boolean fizzy = rs.getBoolean("fizzy");
				double volume = rs.getDouble("volume");
				int age = rs.getInt("age");
				int quantity = rs.getInt("quantity");
				AlcoholDrinkProduct adp = new AlcoholDrinkProduct(name, price, code, mUnit, expDate,
						calories, kosher, temperature, fizzy, volume, age);
				collection.put(adp, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readDrinkProducts(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + DRINK_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				int calories = rs.getInt("calories");
				int temperature = rs.getInt("temperature");
				boolean kosher = rs.getBoolean("kosher");
				Date expDate = new Date(rs.getLong("expdate"));
				boolean fizzy = rs.getBoolean("fizzy");
				int quantity = rs.getInt("quantity");
				DrinkProduct dp = new DrinkProduct(name, price, code, mUnit, expDate,
						calories, kosher, temperature, fizzy);
				collection.put(dp, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readMeatProducts(Map<Product, Integer> collection) {
		String query = "SELECT * FROM " + MEAT_TABLE_NAME;
		try {
			ResultSet rs = state.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int code = rs.getInt("code");
				String mUnit = rs.getString("munit");
				double price = rs.getDouble("price");
				String meatType = rs.getString("meat_type");
				int calories = rs.getInt("calories");
				int temperature = rs.getInt("temperature");
				boolean kosher = rs.getBoolean("kosher");
				Date expDate = new Date(rs.getLong("expdate"));
				int quantity = rs.getInt("quantity");
				MeatProduct mp = new MeatProduct(name, price, code, mUnit, expDate,
						calories, kosher, temperature, meatType);
				collection.put(mp, quantity);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	private void readMilkProducts(Map<Product, Integer> collection){
		String query = "SELECT * FROM " + MILK_TABLE_NAME;
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
	public void remove(Map<Integer, Integer> listOfItemsForRemoving) {
		Set<Integer> codesSet = listOfItemsForRemoving.keySet();
		for(Integer code: codesSet) {
			int group = listOfItemsForRemoving.get(code);
			String query = null;
			try {
				switch(group) {
				case MilkProduct.MILK_GROUP_TYPE:
					query = "DELETE FROM " + MILK_TABLE_NAME
						+ " WHERE code=" + code;
					break;
				case MeatProduct.MEAT_GROUP_TYPE:
					query = "DELETE FROM " + MEAT_TABLE_NAME
						+ " WHERE code=" + code;
					break;
				case DrinkProduct.DRINK_GROUP_TYPE:
					query = "DELETE FROM " + DRINK_TABLE_NAME
					+ " WHERE code=" + code;
					break;
				case AlcoholDrinkProduct.ALCOHOL_GROUP_TYPE:
					query = "DELETE FROM " + ALCOHOL_DRINK_TABLE_NAME
					+ " WHERE code=" + code;
					break;
				case BakeryProduct.BAKERY_GROUP_TYPE:
					query = "DELETE FROM " + BAKERY_TABLE_NAME
					+ " WHERE code=" + code;
					break;
				case HomeChemistry.CHEMISTRY_GROUP_TYPE:
					query = "DELETE FROM " + HOME_CHEMISTRY_TABLE_NAME
					+ " WHERE code=" + code;
					break;
				case HomeTools.HOME_TOOLS_GROUP_TYPE:
					query = "DELETE FROM " + HOME_TOOLS_TABLE_NAME
					+ " WHERE code=" + code;
					break;
				default:
					System.err.println("The product with code " + code
							+ "was not removed from database");
					break;
				}
				state.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package com.telran.minimarket.saverreader;
import java.io.*;
import java.util.Date;
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

public class FileSaverReader implements ISaverReader {
	private String filePath = null;
	private String fileName = null;
	private static final String delimiter = "::";
	public FileSaverReader(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	public void save(Map<Product, Integer> collection) {
		File folder = new File(filePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		String fullPath = filePath + "\\" + fileName;
		File file = new File(fullPath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Creation file problemm: " + fullPath);
				return;
			}
		}
		FileWriter fr = null;
		BufferedWriter bWriter = null;
		try {
			fr = new FileWriter(file);
			bWriter = new BufferedWriter(fr);
			Set<Map.Entry<Product, Integer>> products = collection.entrySet();
			for(Map.Entry<Product, Integer> product: products) {
				Product key = product.getKey();
				int value = product.getValue();
				String record = createProductRecord(key);
				bWriter.write(record + value);
				bWriter.newLine();
			}
		} catch (IOException e) {
			System.err.println("Writing to file problemm: " + fullPath);
			return;
		} finally {
			try {
				bWriter.close();
			} catch (IOException e) {
				System.err.println("Stream closing problem: " + fullPath);
				return;
			}
		}
	}
	private String createProductRecord(Product product) {
		int group = product.getGroupType();
		String record = null;
		switch(group) {
		case MilkProduct.MILK_GROUP_TYPE:
			createMilkProductRecord(product, record, group);
			break;
		case MeatProduct.MEAT_GROUP_TYPE:
			createMeatProductRecord(product, record, group);
			break;
		case DrinkProduct.DRINK_GROUP_TYPE:
			createDrinkProductRecord(product, record, group);
			break;
		case AlcoholDrinkProduct.ALCOHOL_GROUP_TYPE:
			createAlcoholDrinkProductRecord(product, record, group);
			break;
		case BakeryProduct.BAKERY_GROUP_TYPE:
			createBakeryProductRecord(product, record, group);
			break;
		case HomeChemistry.CHEMISTRY_GROUP_TYPE:
			createHomeChemistryRecord(product, record, group);
			break;
		case HomeTools.HOME_TOOLS_GROUP_TYPE:
			createHomeToolsRecord(product, record, group);
			break;
		default:
			break;
		}
		return record;
	}
	private void createMilkProductRecord(Product product, String record, int group) {
		MilkProduct mp = (MilkProduct) product;
		record = group + delimiter + mp.getName() + delimiter + mp.getCode()
				+ delimiter + mp.getmUnit() + delimiter + mp.getPrice()
				+ delimiter + mp.getOrigin() + delimiter + mp.getCalories()
				+ delimiter + mp.getFatness() + delimiter + mp.getTemperature()
				+ delimiter + mp.isKosher() + delimiter + mp.getExpDate().getTime()
				+ delimiter;
	}
	private void createMeatProductRecord(Product product, String record, int group) {
		MeatProduct mp = (MeatProduct) product;
		record = group + delimiter + mp.getName() + delimiter + mp.getCode()
		+ delimiter + mp.getmUnit() + delimiter + mp.getPrice()
		+ delimiter + mp.getMeatType() + delimiter + mp.getCalories()
		+ delimiter + mp.getTemperature() + delimiter + mp.isKosher()
		+ delimiter + mp.getExpDate().getTime() + delimiter;
	}
	private void createDrinkProductRecord(Product product, String record, int group) {
		DrinkProduct dp = (DrinkProduct) product;
		record = group + delimiter + dp.getName() + delimiter + dp.getCode()
		+ delimiter + dp.getmUnit() + delimiter + dp.getPrice()
		+ delimiter + dp.getCalories() + delimiter + dp.getTemperature()
		+ delimiter + dp.isKosher()	+ delimiter + dp.getExpDate().getTime()
		+ delimiter + dp.isFizzy();
	}
	private void createAlcoholDrinkProductRecord(Product product, String record, int group) {
		AlcoholDrinkProduct adp = (AlcoholDrinkProduct) product;
		record = group + delimiter + adp.getName() + delimiter + adp.getCode()
		+ delimiter + adp.getmUnit() + delimiter + adp.getPrice()
		+ delimiter + adp.getCalories() + delimiter + adp.getTemperature()
		+ delimiter + adp.isKosher()	+ delimiter + adp.getExpDate().getTime()
		+ delimiter + adp.isFizzy() + delimiter + adp.getVolume()
		+ delimiter + adp.getAge();
	}
	private void createBakeryProductRecord(Product product, String record, int group) {
		BakeryProduct bp = (BakeryProduct) product;
		record = group + delimiter + bp.getName() + delimiter + bp.getCode()
		+ delimiter + bp.getmUnit() + delimiter + bp.getPrice()
		+ delimiter + bp.getFlourType() + delimiter + bp.getCalories()
		+ delimiter + bp.getTemperature() + delimiter + bp.isKosher()
		+ delimiter + bp.getExpDate().getTime();
	}
	private void createHomeChemistryRecord(Product product, String record, int group) {
		HomeChemistry hc = (HomeChemistry) product;
		record = group + delimiter + hc.getName() + delimiter + hc.getCode()
		+ delimiter + hc.getmUnit() + delimiter + hc.getPrice()
		+ delimiter + hc.isPoison();
	}
	private void createHomeToolsRecord(Product product, String record, int group) {
		HomeTools ht = (HomeTools) product;
		record = group + delimiter + ht.getName() + delimiter + ht.getCode()
		+ delimiter + ht.getmUnit() + delimiter + ht.getPrice()
		+ delimiter + ht.getArea();
	}
	public void read(Map<Product, Integer> collection) {
		String fullPath = filePath + "\\" + fileName;
		File file = new File(fullPath);
		BufferedReader bReader = null;
		if(!file.exists()) {
			System.err.println("The file: " + fullPath + " doesn't exist");
			return;
		}
		try {
			FileReader fr = new FileReader(file);
			bReader = new BufferedReader(fr);
			while(bReader.ready()) {
				String str = bReader.readLine();
				addProductToCollection(collection, str);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	private void addProductToCollection(Map<Product, Integer> collection,
			String str) {
		String[] strs = str.split(delimiter);
		int group = Integer.parseInt(strs[0]);
		switch(group) {
		case MilkProduct.MILK_GROUP_TYPE:
			addMilkProductToCollection(collection, strs);
			break;
		case MeatProduct.MEAT_GROUP_TYPE:
			addMeatProductToCollection(collection, strs);
			break;
		case DrinkProduct.DRINK_GROUP_TYPE:
			addDrinkProductToCollection(collection, strs);
			break;
		case AlcoholDrinkProduct.ALCOHOL_GROUP_TYPE:
			addAlcoholDrinkProductToCollection(collection, strs);
			break;
		case BakeryProduct.BAKERY_GROUP_TYPE:
			addBakeryProductToCollection(collection, strs);
			break;
		case HomeChemistry.CHEMISTRY_GROUP_TYPE:
			addHomeChemistryToCollection(collection, strs);
			break;
		case HomeTools.HOME_TOOLS_GROUP_TYPE:
			addHomeToolsToCollection(collection, strs);
			break;
		default:
			break;
		}
	}
	private void addMilkProductToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		String origin = strs[5];
		int calories = Integer.parseInt(strs[6]);
		double fatness = Double.parseDouble(strs[7]);
		int temperature = Integer.parseInt(strs[8]);
		boolean isCosher = Boolean.parseBoolean(strs[9]);
		Date date = new Date(Long.parseLong(strs[10]));
		MilkProduct mp = new MilkProduct(name, price, code, mUnit, date, calories,
				isCosher, temperature, fatness, origin);
		int quantity = Integer.parseInt(strs[11]);
		collection.put(mp, quantity);
	}
	private void addMeatProductToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		String meatType = strs[5];
		int calories = Integer.parseInt(strs[6]);
		int temperature = Integer.parseInt(strs[7]);
		boolean isCosher = Boolean.parseBoolean(strs[8]);
		Date date = new Date(Long.parseLong(strs[9]));
		MeatProduct mp = new MeatProduct(name, price, code, mUnit, date, calories,
				isCosher, temperature, meatType);
		int quantity = Integer.parseInt(strs[10]);
		collection.put(mp, quantity);
	}
	private void addDrinkProductToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		int calories = Integer.parseInt(strs[5]);
		int temperature = Integer.parseInt(strs[6]);
		boolean isCosher = Boolean.parseBoolean(strs[7]);
		Date date = new Date(Long.parseLong(strs[8]));
		boolean isFizzy = Boolean.parseBoolean(strs[9]);
		DrinkProduct dp = new DrinkProduct(name, price, code, mUnit, date, calories,
				isCosher, temperature, isFizzy);
		int quantity = Integer.parseInt(strs[10]);
		collection.put(dp, quantity);
	}
	private void addAlcoholDrinkProductToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		int calories = Integer.parseInt(strs[5]);
		int temperature = Integer.parseInt(strs[6]);
		boolean isCosher = Boolean.parseBoolean(strs[7]);
		Date date = new Date(Long.parseLong(strs[8]));
		boolean isFizzy = Boolean.parseBoolean(strs[9]);
		double volume = Double.parseDouble(strs[10]);
		int age = Integer.parseInt(strs[11]);
		AlcoholDrinkProduct adp = new AlcoholDrinkProduct(name, price, code, mUnit, date, calories,
				isCosher, temperature, isFizzy, volume, age);
		int quantity = Integer.parseInt(strs[12]);
		collection.put(adp, quantity);
	}
	private void addBakeryProductToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		String flourType = strs[5];
		int calories = Integer.parseInt(strs[6]);
		int temperature = Integer.parseInt(strs[7]);
		boolean isCosher = Boolean.parseBoolean(strs[8]);
		Date date = new Date(Long.parseLong(strs[9]));
		BakeryProduct bp = new BakeryProduct(name, price, code, mUnit, date, calories,
				isCosher, temperature, flourType);
		int quantity = Integer.parseInt(strs[10]);
		collection.put(bp, quantity);
	}
	private void addHomeChemistryToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		boolean isPoison = Boolean.parseBoolean(strs[5]);
		HomeChemistry hc = new HomeChemistry(name, price, code, mUnit, isPoison);
		int quantity = Integer.parseInt(strs[6]);
		collection.put(hc, quantity);
	}
	private void addHomeToolsToCollection(Map<Product, Integer> collection, String[] strs) {
		String name = strs[1];
		int code = Integer.parseInt(strs[2]);
		String mUnit = strs[3];
		double price = Double.parseDouble(strs[4]);
		String area = strs[5];
		HomeTools ht = new HomeTools(name, price, code, mUnit, area);
		int quantity = Integer.parseInt(strs[6]);
		collection.put(ht, quantity);
	}
	public void remove(Map<Integer, Integer> listOfItemsForRemoving) {
		
	}
}

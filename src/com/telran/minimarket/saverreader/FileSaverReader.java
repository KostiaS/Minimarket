package com.telran.minimarket.saverreader;
import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
				System.err.println("Stream closing problemm: " + fullPath);
				return;
			}
		}
	}
	private String createProductRecord(Product product) {
		int group = product.getGroupType();
		String record = null;
		switch(group) {
		case MilkProduct.MILK_GROUP_TYPE:
			MilkProduct mp = (MilkProduct) product;
			record = group + delimiter + mp.getName() + delimiter + mp.getCode()
					+ delimiter + mp.getmUnit() + delimiter + mp.getPrice()
					+ delimiter + mp.getOrigin() + delimiter + mp.getCalories()
					+ delimiter + mp.getFatness() + delimiter + mp.getTemperature()
					+ delimiter + mp.isKosher() + delimiter + mp.getExpDate().getTime()
					+ delimiter;
			break;
		default:
			break;
		}
		return record;
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
			MilkProduct mp = new MilkProduct(name, price, code, mUnit, date, calories, isCosher, temperature, fatness, origin);
			int quantity = Integer.parseInt(strs[11]);
			collection.put(mp, quantity);
			break;
		default:
			break;
		}
	}
}

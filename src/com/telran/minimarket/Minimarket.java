package com.telran.minimarket;
import java.util.*;
import java.util.Map.Entry;
import com.telran.minimarket.products.Product;
import com.telran.minimarket.saverreader.ISaverReader;

public class Minimarket {
	private String name = null;
	private String address = null;
	private HashMap<Product, Integer> productsList = null;
	HashMap<Integer, Integer> listOfItemsForRemoving = null;
	List<Product> TempListOfItemsForRemoving = null;
	private ISaverReader saver = null;
	public Minimarket(String name, String address, ISaverReader saver) {
		super();
		this.name = name;
		this.address = address;
		this.saver = saver;
		productsList = new HashMap<Product, Integer>();
		listOfItemsForRemoving = new HashMap<Integer, Integer>();
		TempListOfItemsForRemoving = new LinkedList<Product>();
	}
	public void addProduct(Product product, int quantity) {
		if(productsList.containsKey(product)) {
			quantity += productsList.get(product);
		}
		else {
			product.setCode(product.hashCode());
		}
		productsList.put(product, quantity);
/*		boolean isExist = false;
		int code = product.hashCode();
		Set<Product> keys = productsList.keySet();
		for(Product p: keys) {
			if(p.hashCode() == code) {
				int value = productsList.get(p);
				int newValue = value + quantity;
				productsList.put(p, newValue);
				isExist = true;
				break;
			}
		}
		if(!isExist) {
			product.setCode(code);
			productsList.put(product, quantity);			
		}*/
	}
	public void saveProductList() {
		saver.save(productsList);
		if(!listOfItemsForRemoving.isEmpty()) {
			saver.remove(listOfItemsForRemoving);
		}
	}
	public void readProductsList() {
		saver.read(productsList);
	}
	public void displayMinimarket() {
		System.out.println("Name: " + name);
		System.out.println("Address: " + address);
		System.out.println("================================================================================================================================================================================================");
		System.out.println("Products list:");
//		Set<Product> keys = productsList.keySet();
//		for(Product p: keys) {
//			System.out.println(p + ", quantity: " + productsList.get(p));
//		}
		Set<Entry<Product, Integer>> set = productsList.entrySet();
		for (Entry<Product, Integer> item: set) {
		      System.out.print(item.getKey());
		      System.out.println(", quantity=" + item.getValue());
		    }
		System.out.println("================================================================================================================================================================================================");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public HashMap<Product, Integer> getProductsList() {
		return productsList;
	}
	public void setProductsList(HashMap<Product, Integer> productsList) {
		this.productsList = productsList;
	}
	public ISaverReader getSaver() {
		return saver;
	}
	public void setSaver(ISaverReader saver) {
		this.saver = saver;
	}
	public HashMap<Integer, Integer> getListOfItemsForRemoving() {
		return listOfItemsForRemoving;
	}
	public void setListOfItemsForRemoving(HashMap<Integer, Integer> listOfItemsForRemoving) {
		this.listOfItemsForRemoving = listOfItemsForRemoving;
	}
}

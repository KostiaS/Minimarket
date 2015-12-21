package com.telran.minimarket.saverreader;
import java.util.Map;
import com.telran.minimarket.products.Product;

public interface ISaverReader {
	public void save(Map<Product, Integer> collection);
	public void read(Map<Product, Integer> collection);
	public void remove(Map<Integer, Integer> listOfItemsForRemoving);
}

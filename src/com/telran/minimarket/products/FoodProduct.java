package com.telran.minimarket.products;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FoodProduct extends Product {
	private Date expDate = null;
	private int calories;
	private boolean isKosher = false;
	private int temperature = 0;
	
	public FoodProduct() {
		
	}
	public FoodProduct(String name, double price, int code, String mUnit,
			int groupType, Date expDate, int calories, boolean isKosher,
			int temperature) {
		super(name, price, code, mUnit, groupType);
		this.expDate = expDate;
		this.calories = calories;
		this.isKosher = isKosher;
		this.temperature = temperature;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public boolean isKosher() {
		return isKosher;
	}
	public void setKosher(boolean isKosher) {
		this.isKosher = isKosher;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
		String date = df.format(expDate);
		return super.toString() + ", expDate=" + date + ", calories=" + calories
				+ ", isKosher=" + isKosher + ", temperature=" + temperature;
	}
}

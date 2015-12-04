package com.telran.minimarket.products;

import java.util.Date;

public class MeatProduct extends FoodProduct {
	public static final int MEAT_GROUP_TYPE = 2; 
	private String meatType = null;
	
	public MeatProduct() {
		this.setGroupType(MEAT_GROUP_TYPE);
	}
	public MeatProduct(String name, double price, int code, String mUnit,
			Date expDate, int calories, boolean isKosher,
			int temperature, String meatType) {
		super(name, price, code, mUnit, MEAT_GROUP_TYPE, expDate, calories, isKosher,
				temperature);
		this.meatType = meatType;
	}
	public String getMeatType() {
		return meatType;
	}
	public void setMeatType(String meatType) {
		this.meatType = meatType;
	}
	public static int getMeatGroupType() {
		return MEAT_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", meatType=" + meatType;
	}
}

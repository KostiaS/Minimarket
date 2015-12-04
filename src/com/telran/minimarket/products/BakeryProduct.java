package com.telran.minimarket.products;

import java.util.Date;

public class BakeryProduct extends FoodProduct {
	public static final int BAKERY_GROUP_TYPE = 5;
	private String flourType = null;
	public BakeryProduct() {
		this.setGroupType(BAKERY_GROUP_TYPE);
	}
	public BakeryProduct(String name, double price, int code, String mUnit,
			Date expDate, int calories, boolean isKosher,
			int temperature, String flourType) {
		super(name, price, code, mUnit, BAKERY_GROUP_TYPE, expDate, calories, isKosher,
				temperature);
		this.flourType = flourType;
	}
	public String getFlourType() {
		return flourType;
	}
	public void setFlourType(String flourType) {
		this.flourType = flourType;
	}
	public static int getBakeryGroupType() {
		return BAKERY_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", flourType=" + flourType;
	}
}

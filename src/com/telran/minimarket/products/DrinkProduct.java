package com.telran.minimarket.products;

import java.util.Date;

public class DrinkProduct extends FoodProduct {
	public static final int DRINK_GROUP_TYPE = 3;
	private boolean fizzy = true;
	public DrinkProduct() {
		this.setGroupType(DRINK_GROUP_TYPE);
	}
	public DrinkProduct(String name, double price, int code, String mUnit,
			Date expDate, int calories, boolean isKosher,
			int temperature, boolean fizzy) {
		super(name, price, code, mUnit, DRINK_GROUP_TYPE, expDate, calories, isKosher,
				temperature);
		this.fizzy = fizzy;
	}
	public boolean isFizzy() {
		return fizzy;
	}
	public void setFizzy(boolean fizzy) {
		this.fizzy = fizzy;
	}
	public static int getDrinkGroupType() {
		return DRINK_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", fizzy=" + fizzy;
	}
}

package com.telran.minimarket.products;

import java.util.Date;

public class MilkProduct extends FoodProduct {
	public static final int MILK_GROUP_TYPE = 1;
	private double fatness = 0;
	private String origin = null;
	
	public MilkProduct() {
		this.setGroupType(MILK_GROUP_TYPE);
	}
	public MilkProduct(String name, double price, int code, String mUnit,
			Date expDate, int calories, boolean isKosher,
			int temperature, double fatness, String origin) {
		super(name, price, code, mUnit, MILK_GROUP_TYPE, expDate, calories, isKosher,
				temperature);
		this.fatness = fatness;
		this.origin = origin;
	}
	public double getFatness() {
		return fatness;
	}
	public void setFatness(double fatness) {
		this.fatness = fatness;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String toString() {
		return super.toString() + ", fatness=" + fatness + ", origin=" + origin;
	}
}

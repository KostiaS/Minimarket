package com.telran.minimarket.products;

import java.util.Date;

public class AlcoholDrinkProduct extends DrinkProduct {
	public static final int ALCOHOL_GROUP_TYPE = 4;
	private double volume = 0;
	private int age = 0;
	public AlcoholDrinkProduct() {
		this.setGroupType(ALCOHOL_GROUP_TYPE);
	}
	public AlcoholDrinkProduct(String name, double price, int code,
			String mUnit, Date expDate, int calories, boolean isKosher,
			int temperature, boolean fizzy, double volume, int age) {
		super(name, price, code, mUnit, expDate, calories, isKosher,
				temperature, fizzy);
		this.setGroupType(ALCOHOL_GROUP_TYPE);
		this.volume = volume;
		this.age = age;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public static int getAlcoholGroupType() {
		return ALCOHOL_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", volume=" + volume + ", age=" + age;
	}
}

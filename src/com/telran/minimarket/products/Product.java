package com.telran.minimarket.products;

public abstract class Product {
	private String name = null;
	private double price = 0;
	private int code = 0;
	private String mUnit = null;
	private int groupType = 0;
	
	public Product() {
		
	}
	public Product(String name, double price, int code, String mUnit,
			int groupType) {
		super();
		this.name = name;
		this.price = price;
//		this.code = code;
		this.mUnit = mUnit;
		this.groupType = groupType;
		this.code = hashCode();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getmUnit() {
		return mUnit;
	}
	public void setmUnit(String mUnit) {
		this.mUnit = mUnit;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String toString() {
		return "name=" + name + ", price=" + price + ", code=" + code
				+ ", mUnit=" + mUnit;
	}
	public boolean equals(Object obj) {
		Product product = (Product)obj;
		return(this.code == product.code);
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		int res = name.hashCode() + new Double(price).hashCode()
				+ new Integer(groupType).hashCode() + mUnit.hashCode();
		return res > 0 ? res : -res;
	}
}

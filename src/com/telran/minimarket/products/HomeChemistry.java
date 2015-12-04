package com.telran.minimarket.products;

public class HomeChemistry extends NonFoodProduct {
	public static final int CHEMISTRY_GROUP_TYPE = 6;
	private boolean isPoison = true;
	public HomeChemistry() {
		this.setGroupType(CHEMISTRY_GROUP_TYPE);
	}
	public HomeChemistry(String name, double price, int code, String mUnit,
			boolean isPoison) {
		super(name, price, code, mUnit, CHEMISTRY_GROUP_TYPE);
		this.isPoison = isPoison;
	}
	public boolean isPoison() {
		return isPoison;
	}
	public void setPoison(boolean isPoison) {
		this.isPoison = isPoison;
	}
	public static int getChemistryGroupType() {
		return CHEMISTRY_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", isPoison=" + isPoison;
	}
}

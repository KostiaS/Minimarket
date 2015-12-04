package com.telran.minimarket.products;

public class HomeTools extends NonFoodProduct {
	public static final int HOME_TOOLS_GROUP_TYPE = 7;
	private String area = null;
	public HomeTools() {
		this.setGroupType(HOME_TOOLS_GROUP_TYPE);
	}
	public HomeTools(String name, double price, int code, String mUnit,
			String area) {
		super(name, price, code, mUnit, HOME_TOOLS_GROUP_TYPE);
		this.area = area;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public static int getHomeToolsGroupType() {
		return HOME_TOOLS_GROUP_TYPE;
	}
	public String toString() {
		return super.toString() + ", area=" + area;
	}
}

package com.telran.minimarket;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.telran.minimarket.products.MilkProduct;

public class MainMenu {
	public static void mainMenu(Minimarket mmrk) {
//		InputStreamReader isr = new InputStreamReader(System.in);
		Scanner reader = new Scanner(System.in);
		String answer = null;
		while(true) {
			System.out.println("Choose option:");
			System.out.println("1 -> Add product");
			System.out.println("2 -> Display product list");
			System.out.println("3 -> Save product list");
			System.out.println("4 -> Save and exit");
			System.out.println("5 -> Exit without saving");
			answer = reader.nextLine();
			int option = Integer.parseInt(answer);
			switch(option) {
			case 1:
				addProductMenu(mmrk, reader);
				break;
			case 2:
				mmrk.displayMinimarket();
				break;
			case 3:
				mmrk.saveProductList();
				break;
			case 4:
				mmrk.saveProductList();
				return;
			case 5:
				return;
			}
		}
	}
	private static void addProductMenu(Minimarket mmrk, Scanner reader) {
		String answer = null;
		while(true) {
			System.out.println("Choose product type:");
			System.out.println("1 -> Milk product");
			System.out.println("2 -> Meat product");
			System.out.println("3 -> Drink product");
			System.out.println("4 -> Alcohol product");
			System.out.println("5 -> Bakery product");
			System.out.println("6 -> Home chemistry product");
			System.out.println("7 -> Home tools product");
			answer = reader.nextLine();
			int type = Integer.parseInt(answer);
			switch(type) {
			case 1:
				addMilkProductMenu(mmrk, reader);
				break;
			}
			System.out.println("Do you want to add another product: yes or no...");
			answer = reader.nextLine();
			if(answer.equalsIgnoreCase("no")) {
				break;
			}
		}
	}
	private static void addMilkProductMenu(Minimarket mmrk, Scanner reader) {
		System.out.println("Please enter name...");
		String name = reader.nextLine();
		System.out.println("Please enter price...");
		String str = reader.nextLine();
		double price = Double.parseDouble(str);
		System.out.println("Please enter measure unit...");
		String mUnit = reader.nextLine();
		System.out.println("Please enter exp. day of month...");
		String day = reader.nextLine();
		System.out.println("Please enter exp. month...");
		String month = reader.nextLine();
		System.out.println("Please enter exp. year...");
		String year = reader.nextLine();
		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		Date date = gc.getTime();
		System.out.println("Please enter calories...");
		str = reader.nextLine();
		int calories = Integer.parseInt(str);
		System.out.println("Please enter is the product kosher: yes or no...");
		str = reader.nextLine();
		boolean isKosher = str.equalsIgnoreCase("yes");
		System.out.println("Please enter temperature...");
		str = reader.nextLine();
		int temperature = Integer.parseInt(str);
		System.out.println("Please enter fatness...");
		str = reader.nextLine();
		double fatness = Double.parseDouble(str);
		System.out.println("Please enter origin of product...");
		String origin = reader.nextLine();
		MilkProduct mp = new MilkProduct(name, price, 0, mUnit, date, calories, isKosher, temperature, fatness, origin);
		System.out.println("How many units do you want to add...");
		str = reader.nextLine();
		int quantity = Integer.parseInt(str);
		mmrk.addProduct(mp, quantity);
	}
}

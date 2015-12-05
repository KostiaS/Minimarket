package com.telran.minimarket;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import com.telran.minimarket.products.*;

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
			while(true) {
				boolean flag = false;
				System.out.println("Choose product type:");
				System.out.println("1 -> Milk product");
				System.out.println("2 -> Meat product");
				System.out.println("3 -> Drink product");
				System.out.println("4 -> Alcohol product");
				System.out.println("5 -> Bakery product");
				System.out.println("6 -> Home chemistry product");
				System.out.println("7 -> Home tools product");
				try {
					answer = reader.nextLine();
					int type = Integer.parseInt(answer);
					switch(type) {
					case MilkProduct.MILK_GROUP_TYPE:
						addMilkProductMenu(mmrk, reader);
						flag = true;
						break;
					case MeatProduct.MEAT_GROUP_TYPE:
						addMeatProductMenu(mmrk, reader);
						flag = true;
						break;
					case DrinkProduct.DRINK_GROUP_TYPE:
						addDrinkProductMenu(mmrk, reader);
						flag = true;
						break;
					case AlcoholDrinkProduct.ALCOHOL_GROUP_TYPE:
						addAlcoholDrinkProductMenu(mmrk, reader);
						flag = true;
						break;
					case BakeryProduct.BAKERY_GROUP_TYPE:
						addBakeryProductMenu(mmrk, reader);
						flag = true;
						break;
					case HomeChemistry.CHEMISTRY_GROUP_TYPE:
						addHomeChemistryMenu(mmrk, reader);
						flag = true;
						break;
					case HomeTools.HOME_TOOLS_GROUP_TYPE:
						addHomeToolsMenu(mmrk, reader);
						flag = true;
						break;
					default:	
						System.err.println("Please type one of the proposed number");
						break;
					}
					if(flag) {
						break;
					}
				} catch (NumberFormatException e) {
					System.err.println("Please type one of the proposed number");
				}
			}
			System.out.println("Do you want to add another product: yes or no...");
			answer = reader.nextLine();
			if(answer.equalsIgnoreCase("no")) {
				break;
			}
		}
	}
	private static void addMilkProductMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		Date date = addDateMenu(reader);
		int calories = addCaloriesMenu(reader);
		boolean isKosher = addKosherMenu(reader);
		int temperature = addTemperatureMenu(reader);
		double fatness = addFatnessMenu(reader);
		String origin = addOriginMenu(reader);
		MilkProduct mp = new MilkProduct(name, price, 0, mUnit, date, calories,
				isKosher, temperature, fatness, origin);
		addProductToMinimarket(mmrk, mp, reader);
	}
	private static void addMeatProductMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		Date date = addDateMenu(reader);
		int calories = addCaloriesMenu(reader);
		boolean isKosher = addKosherMenu(reader);
		int temperature = addTemperatureMenu(reader);
		String meatType = addMeatTypeMenu(reader);
		MeatProduct mp = new MeatProduct(name, price, 0, mUnit, date, calories,
				isKosher, temperature, meatType);
		addProductToMinimarket(mmrk, mp, reader);
	}
	private static void addDrinkProductMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		Date date = addDateMenu(reader);
		int calories = addCaloriesMenu(reader);
		boolean isKosher = addKosherMenu(reader);
		int temperature = addTemperatureMenu(reader);
		boolean fizzy = addFizzyMenu(reader);
		DrinkProduct dp = new DrinkProduct(name, price, 0, mUnit, date, calories,
				isKosher, temperature, fizzy);
		addProductToMinimarket(mmrk, dp, reader);
	}
	private static void addAlcoholDrinkProductMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		Date date = addDateMenu(reader);
		int calories = addCaloriesMenu(reader);
		boolean isKosher = addKosherMenu(reader);
		int temperature = addTemperatureMenu(reader);
		boolean fizzy = addFizzyMenu(reader);
		double volume = addVolumeMenu(reader);
		int age = addAgeMenu(reader);
		AlcoholDrinkProduct ap = new AlcoholDrinkProduct(name, price, 0, mUnit, date, calories,
				isKosher, temperature, fizzy, volume, age);
		addProductToMinimarket(mmrk, ap, reader);
	}
	private static void addBakeryProductMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		Date date = addDateMenu(reader);
		int calories = addCaloriesMenu(reader);
		boolean isKosher = addKosherMenu(reader);
		int temperature = addTemperatureMenu(reader);
		String flourType = addFlourTypeMenu(reader);
		BakeryProduct mp = new BakeryProduct(name, price, 0, mUnit, date, calories,
				isKosher, temperature, flourType);
		addProductToMinimarket(mmrk, mp, reader);
	}
	private static void addHomeChemistryMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		boolean isPoison = addPoisonMenu(reader);
		HomeChemistry hc = new HomeChemistry(name, price, 0, mUnit, isPoison);
		addProductToMinimarket(mmrk, hc, reader);
	}
	private static void addHomeToolsMenu(Minimarket mmrk, Scanner reader) {
		String name = addNameMenu(reader);
		double price = addPriceMenu(reader);
		String mUnit = addMUnitMenu(reader);
		String area = addAreaMenu(reader);
		HomeTools ht = new HomeTools(name, price, 0, mUnit, area);
		addProductToMinimarket(mmrk, ht, reader);
	}
	private static void addProductToMinimarket(Minimarket mmrk, Product product, Scanner reader) {
		while(true) {	
			System.out.println("How many units do you want to add...");
			String str = reader.nextLine();
			try {
				double quantityD = Double.parseDouble(str);
				int quantity = Integer.parseInt(str);
				if(quantity > 0 && quantityD == quantity) {
					mmrk.addProduct(product, quantity);
					break;
				}
			} catch(NumberFormatException e) {
				System.err.println("Please enter only integer numbers");
			}
		}
	}
	private static String addNameMenu(Scanner reader) {
		String name = null;
		while(true) {
			System.out.println("Please enter name...");
			name = reader.nextLine();
			if(!name.isEmpty()) {
				return name;
			}
			else {
				System.err.println("Please type name");
			}
		}
	}
	private static double addPriceMenu(Scanner reader) {
		double price = 0;
		while (true) {
			System.out.println("Please enter price...");
			String str = reader.nextLine();
			try {
				price = Double.parseDouble(str);
				if(price >= 0) {
					return price;
				}
				else {
					System.err.println("Please enter positive price");
				}
			} catch(NumberFormatException e) {
				System.err.println("Please enter only numbers");
			}
		}
	}
	private static String addMUnitMenu(Scanner reader) {
		String mUnit = null;
		while(true) {
			System.out.println("Please enter measure unit...");
			mUnit = reader.nextLine();
			if(!mUnit.isEmpty()) {
				return mUnit;
			}
			else {
				System.err.println("Please type measury unit");
			}
		}
	}
	private static Date addDateMenu(Scanner reader) {
		Date date = null;
		while(true) {
			System.out.println("Please enter date in format DD/MM/YY...");
			String expDate = reader.nextLine();
			int first = expDate.indexOf('/');
			int last = expDate.lastIndexOf('/');
			int length = expDate.length();
			if(first == 2 && last == 5 && length == 8) {
				String[] arExpDate = expDate.split("/");
				String strDay = arExpDate[0];
				String strMonth = arExpDate[1];
				String strYear = arExpDate[2];
				try {
					int day = Integer.parseInt(strDay);
					if(day > 0 && day < 32) {
						int month = Integer.parseInt(strMonth) - 1;
						if(month > 0 && month < 13) {
							int year = Integer.parseInt(strYear) + 2000;
							if(year >= 15) {
								GregorianCalendar gc = new GregorianCalendar(year, month, day);
								date = gc.getTime();
								return date;
							}
							else {
								System.err.println("Please enter year correctly");
							}
						}
						else{
							System.err.println("Please enter month correctly: from 1 to 12");
						}
					}
					else {
						System.err.println("Please enter day correctly: from 1 to 31");
					}
				} catch(NumberFormatException e) {
					System.err.println("Please type date correctly, taking format into account");
				}
			}
			else {
				System.err.println("Please type date correctly, taking format into account");
			}
		}
	}
	private static int addCaloriesMenu(Scanner reader) {
		int calories = 0;
		while(true) {
			System.out.println("Please enter calories...");
			String str = reader.nextLine();
			try {
				calories = Integer.parseInt(str);
				if(calories >= 0) {
					return calories;
				}
				else {
					System.err.println("Please enter positive calories");
				}
			} catch(NumberFormatException e) {
				System.err.println("Please type only numbers");
			}
		}
	}
	private static boolean addKosherMenu(Scanner reader) {
		boolean isKosher = false;
		while(true) {
			System.out.println("Please enter is the product kosher: yes or no...");
			String str = reader.nextLine();
			if(str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("no")) {
				isKosher = str.equalsIgnoreCase("yes");
				return isKosher;
			}
			else {
				System.err.println("Please enter only yes or no");
			}
		}
	}
	private static int addTemperatureMenu(Scanner reader) {
		int temperature = 0;
		while(true) {
			System.out.println("Please enter temperature...");
			String str = reader.nextLine();
			try {
				temperature = Integer.parseInt(str);
				return temperature;
			} catch(NumberFormatException e) {
				System.err.println("Please type only numbers");
			}
		}
	}
	private static double addFatnessMenu(Scanner reader) {
		double fatness = 0;
		while(true) {
			System.out.println("Please enter fatness...");
			String str = reader.nextLine();
			try {
				fatness = Double.parseDouble(str);
				if(fatness >= 0 && fatness <= 100) {
					return fatness;
				}
				else {
					System.err.println("Please enter fatness correctly:"
							+ "it should be number from 0 to 100");
				}
			} catch(NumberFormatException e) {
				System.err.println("Please type only numbers");
			}
		}
	}
	private static String addOriginMenu(Scanner reader) {
		String origin = null;
		while(true) {
			System.out.println("Please enter origin of product...");
			origin = reader.nextLine();
			if(!origin.isEmpty()) {
				return origin;
			}
			else {
				System.err.println("Please enter origin");
			}
		}
	}
	private static String addMeatTypeMenu(Scanner reader) {
		String meatType = null;
		while(true) {
			System.out.println("Please enter meat type...");
			meatType = reader.nextLine();
			if(!meatType.isEmpty()) {
				return meatType;
			}
			else {
				System.err.println("Please enter meat type");
			}
		}
	}
	private static boolean addFizzyMenu(Scanner reader) {
		boolean fizzy = false;
		while(true) {
			System.out.println("Please enter is the product fizzy: yes or no...");
			String str = reader.nextLine();
			if(str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("no")) {
				fizzy = str.equalsIgnoreCase("yes");
				return fizzy;
			}
			else {
				System.err.println("Please enter only yes or no");
			}
		}
	}
	private static double addVolumeMenu(Scanner reader) {
		double volume = 0;
		while (true) {
			System.out.println("Please enter volume...");
			String str = reader.nextLine();
			try {
				volume = Double.parseDouble(str);
				if(volume >= 0) {
					return volume;
				}
				else {
					System.err.println("Please enter positive price");
				}
			} catch(NumberFormatException e) {
				System.err.println("Please enter only numbers");
			}
		}
	}
	private static int addAgeMenu(Scanner reader) {
		int age = 0;
		while(true) {
			System.out.println("Please enter age...");
			String str = reader.nextLine();
			try {
				age = Integer.parseInt(str);
				if(age >= 0) {
					return age;
				}
				else {
					System.err.println("Please enter positive age");
				}
			} catch(NumberFormatException e) {
				System.err.println("Please type only numbers");
			}
		}
	}
	private static String addFlourTypeMenu(Scanner reader) {
		String flourType = null;
		while(true) {
			System.out.println("Please enter flour type...");
			flourType = reader.nextLine();
			if(!flourType.isEmpty()) {
				return flourType;
			}
			else {
				System.err.println("Please enter flour type");
			}
		}
	}
	private static boolean addPoisonMenu(Scanner reader) {
		boolean isPoison = false;
		while(true) {
			System.out.println("Please enter is the product poison: yes or no...");
			String str = reader.nextLine();
			if(str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("no")) {
				isPoison = str.equalsIgnoreCase("yes");
				return isPoison;
			}
			else {
				System.err.println("Please enter only yes or no");
			}
		}
	}
	private static String addAreaMenu(Scanner reader) {
		String area = null;
		while(true) {
			System.out.println("Please enter area...");
			area = reader.nextLine();
			if(!area.isEmpty()) {
				return area;
			}
			else {
				System.err.println("Please enter area");
			}
		}
	}
}

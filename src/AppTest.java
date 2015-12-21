import java.util.*;
import com.telran.minimarket.*;
import com.telran.minimarket.products.*;
import com.telran.minimarket.saverreader.*;

public class AppTest {

	public static void main(String[] args) {
		FileSaverReader fsr = new FileSaverReader("c:\\_Minimarket", "Minimarket.txt");
		DBSaverReader dsr = new DBSaverReader("jdbc:mysql://localhost:3306/minimarket", "root", "");
		Minimarket mrk = new Minimarket("Hi Jack", "Main street", dsr);
		mrk.readProductsList();
		MainMenu.mainMenu(mrk);
//		Calendar calendar = new GregorianCalendar(2015, 10, 28);
//		MilkProduct mp1 = new MilkProduct("Yogurt Tnuva 0.31", 5.06, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		Date date = new Date();
//		MilkProduct mp2 = new MilkProduct("Yogurt Tnuva 0.31", 5.06, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		MilkProduct mp3 = new MilkProduct("Yogurt Tara 0.31", 6.10, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		MeatProduct mp4 = new MeatProduct("Porky Farm", 62, 0, "kg", calendar.getTime(), 300, false, 5, "pork");		
//		DrinkProduct dp1 = new DrinkProduct("Fanta", 10, 0, "bottle", calendar.getTime(), 100, false, 25, true);		
//		AlcoholDrinkProduct adp1 = new AlcoholDrinkProduct("Bushmills", 280, 0, "bottle", calendar.getTime(),
//				600, true, 25, false, 0.7, 16);		
//		BakeryProduct bp1 = new BakeryProduct("White bread", 5, 0, "g", calendar.getTime(), 70, true, 25, "wheat");		
//		HomeChemistry hc1 = new HomeChemistry("Tide", 20.5, 0, "pack", true);
//		HomeTools ht1 = new HomeTools("Glass", 15, 0, "unit", "kitchen");
//		mrk.addProduct(mp1, 10);
//		mrk.addProduct(mp2, 40);
//		mrk.addProduct(mp3, 15);
//		mrk.addProduct(mp4, 38);
//		mrk.addProduct(dp1, 45);
//		mrk.addProduct(adp1, 30);
//		mrk.addProduct(bp1, 16);
//		mrk.addProduct(ht1, 20);
//		mrk.addProduct(hc1, 100);
//		mrk.displayMinimarket();
//		mrk.saveProductList();
	}
}

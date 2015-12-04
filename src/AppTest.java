import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.telran.minimarket.MainMenu;
import com.telran.minimarket.Minimarket;
import com.telran.minimarket.products.HomeChemistry;
import com.telran.minimarket.products.MilkProduct;
import com.telran.minimarket.saverreader.FileSaverReader;

public class AppTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSaverReader fsr = new FileSaverReader("c:\\_Minimarket", "Minimarket.txt");
		Minimarket mrk = new Minimarket("Hi Jack", "Main street", fsr);
		mrk.readProductsList();
		MainMenu.mainMenu(mrk);
//		Calendar calendar = new GregorianCalendar(2015, 10, 28);
//		MilkProduct mp1 = new MilkProduct("Yogurt Tnuva 0.31", 5.06, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		Date date = new Date();
//		MilkProduct mp2 = new MilkProduct("Yogurt Tnuva 0.31", 5.06, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		MilkProduct mp3 = new MilkProduct("Yogurt Tara 0.31", 6.10, 0, "bottle", calendar.getTime(), 100, true, 5, 10, "Cow");
//		HomeChemistry hch1 = new HomeChemistry("Tide", 20.5, 0, "pack", true);
//		mrk.addProduct(mp1, 10);
//		mrk.addProduct(mp2, 40);
//		mrk.addProduct(mp3, 15);
//		mrk.addProduct(hch1, 100);
//		mrk.displayMinimarket();
//		mrk.saveProductList();
	}
}

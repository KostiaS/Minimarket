import java.util.*;
import java.util.Map.*;

public class MapTest {
	public static void main(String[] args) {
//		TreeMap<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		LinkedHashSet<Integer> set2 = new LinkedHashSet<Integer>();
		map1.put(1, 1);
		map1.put(1, 1);
		set2.add(2);
		set2.add(2);
		set2.add(3);
		Set<Entry<Integer, Integer>> set1 = map1.entrySet();
		for(Entry<Integer, Integer> entry: set1) {
			System.out.print(entry.getKey() + ", ");
			System.out.println(entry.getValue());
		}
		System.out.println("-----end of map-----");
		for(Integer integ: set2) {
			System.out.print(integ + ", ");
		}
		System.out.println("\n-----end of set-----");
	}
}

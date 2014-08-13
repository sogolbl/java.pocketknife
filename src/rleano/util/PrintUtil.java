package rleano.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintUtil {

	public static String printCollection(Collection<String> collection, String separator) {
		String result = "";
		for (String item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printArray(String[] collection, String separator) {
		String result = "";
		for (String item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}
	
	public static String printArray(float[] collection, String separator) {
		String result = "";
		for (float item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}
	
	public static String printArray(int[] collection, String separator) {
		String result = "";
		for (int item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}
	
	public static String printArray(Integer[] collection, String separator) {
		String result = "";
		for (int item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printIntCollection(Collection<Integer> collection, String separator) {
		String result = "";
		for (Integer item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printDoubleCollection(Collection<Double> collection, String separator) {
		String result = "";
		for (Double item : collection) {
			result += item + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printObjectCollection(Collection<?> collection, String separator) {
		String result = "";
		for (Object item : collection) {
			result += item.toString() + separator;
		}
		
		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printMapDouble(Map<String, Double> map, String title) {
		String result = "map: " + title + "\n";
		List<String> keys = CollectionUtil.cloneSetToListString(map.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			result += "\t " + key + ": " + MathUtil.trunk(map.get(key), 2) + "\n";
		}

		return result;
	}

	public static String printMapInteger(Map<String, Integer> map, String title) {
		String result = "map: " + title + "\n";
		List<String> keys = CollectionUtil.cloneSetToListString(map.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			result += "\t " + key + ": " + map.get(key) + "\n";
		}

		return result;
	}

	public static String printStrings(List<String> instances, String separator, boolean order) {
		String result = "";
		if (order == true) {
			Collections.sort(instances);
		}
		for (String string : instances) {
			result += string + separator;
		}
		if (result.isEmpty()) {
			return "";
		}

		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}

	public static String printStrings(Set<String> instances, String separator, boolean order) {
		String result = "";
		if (order == true) {
			return printStrings(CollectionUtil.cloneSetToListString(instances), separator, true);
		}

		for (String string : instances) {
			result += string + separator;
		}
		if (result.isEmpty()) {
			return "";
		}

		if (separator.equals(" ") || separator.equals("\t")) {
			return result.trim();
		}
		return PrintUtil.removeLast(result.trim());
	}
	
	public static String removeLast(String string) {
		string = string.trim();
		if (string == null || string.isEmpty()) {
			return string;
		}
		
		return string.substring(0, string.length() - 1);
	}
	
	public static void printProgress(int last, int current, int total) {
		String result = "Progress: [";
		int step = total / 20;
		
		double progress = Math.floor(MathUtil.div(current + step, step));
		double before = Math.floor(MathUtil.div(last + step, step));
		
		if (progress - before > 0) {
			
			for (int i = 0; i < 20; i++) {
				if (i < progress) {
					result += "=";
				} else {
					result += " ";
				}
			}
			result += "]";
			String print = "\r" + result + " -- (" + current + ", " + total + ")\r";
			try {
				System.out.write(print.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
//		try {
//			int last = -1;
//			for (int i = 0; i < 100; i = i + 2){
//				Thread.sleep(1000);
//				PrintUtil.printProgress(last, i, 100);
//				last = i;
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		 String anim= "|/-\\";
         for (int x =0 ; x < 100 ; x++){
                 String data = "\r" + anim.charAt(x % anim.length())  + " " + x ;
                 System.out.write(data.getBytes());
                 Thread.sleep(100);
         }
	}

}

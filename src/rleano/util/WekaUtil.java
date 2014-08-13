package rleano.util;

import java.util.ArrayList;
import java.util.List;

public class WekaUtil {

	public static String[] printResults(String results, String sep) {
		String[] lines = results.split("\n");
		for (int i = 0; i < lines.length; i++) {
			lines[i] = lines[i].trim().replaceAll(" +", " ").replaceAll("(\\d)\\s", "$1" + sep);
		}
		
		return lines;
	}
	
	public static List<String> getSelectedFeatures(String file, String sep) {
		String contents = FileUtil.readFile(file);
		String[] lines = contents.split("\n");
		List<String> atts = new ArrayList<String>();
		boolean start = false;
		
		for (int i = 0; i < lines.length; i++) {
			if (start == false) {
				if (lines[i].contains("Ranked attributes:") == true) {
					start = true;
				} 
			} else {
				if (lines[i].isEmpty() == false) {
					String newLine = lines[i].trim().replaceAll(" +", " ").replaceAll("(\\d)\\s", "$1" + sep);
					atts.add(newLine);
				}
				else {
					return atts;
				}
			}
		}
		
		return atts;
	}
	
//	public static List<String> breakResults(String contents) {
//		String[] lines = contents.split("\n");
//		String lineBefore = "";
//		List<String> results = new ArrayList<String>();
//		
//		for (int i = 0; i < lines.length; i++) {
//			if (lines[i].isEmpty() == true) {
//				
//			} else {
//				
//			}
//			
//			lineBefore = lines[i];
//		}
//		
//		return results;
//	}

	public static void main(String[] args) {
		
	}

}

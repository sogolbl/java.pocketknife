package rleano.util;

import java.util.ArrayList;
import java.util.List;

public class RUtil {

	/**
	 * Runs the specified R script
	 * @param script	R script to run (e.g. 'script.r')
	 * @param folder	The folder from where the file is run
	 * @param args		Array of strings passed as parameters to the Script d
	 * @return
	 */
	public static String runScript(String script, String folder, String[] args) {
		String command = "Rscript --vanilla " + script + " " + PrintUtil.printArray(args, " ");
		String result = Util.cmdExec(command.split(" "), folder + "/");
		return result;
	}
	
	public static void formatFile(String filename, String separator) {
		String contents = FileUtil.readFile(filename);
		
		String[] pieces = contents.split(separator);
		
		FileUtil.writeLine(filename, "", true);
		for (String piece : pieces) {
			
			String[] results = formatResults(piece, "\t", true);

			for (String line : results) {
				FileUtil.writeLine(filename, line + "\n", false);
			}
			
			FileUtil.writeLine(filename, "\n", false);
		}
	}
	
	public static String formatString(String contents, String separator, boolean hasHeader) {
		
		String[] pieces = contents.split(separator);
		contents = "";

		for (String piece : pieces) {
			
			String title = "";
			String header = "";
			int index = piece.indexOf("\n");
			title = piece.substring(0, index);
			piece = piece.substring(index + 1);
			
			if (hasHeader == true) {
				index = piece.indexOf("\n");
				header = StringUtil.removeExtraSpaced(piece.substring(0, index));
				header = header.replace(" ", "\t");
				piece = piece.substring(index + 1);
			}
			
			String[] results = formatResults(piece, "\t", false);

			contents += title + "\n" + header + "\n";
			for (String line : results) {
				contents += line + "\n";
			}
		}
		
		return contents;
	}

	public static String[] formatResults(String results, String sep, boolean header) {

		String[] lines = results.split("\n");

		int index = 0;
		int i = header ? 1 : 0; 
		boolean column = true;
		char[] chars = lines[i].toCharArray();

		for (char c : chars) {
			if (c == ' ') {
				i = header ? 1 : 0;
				while (i < lines.length) {
					try {
						if (lines[i].charAt(index) != ' ') {
							column = false;
						}
					} catch (Exception e) {
						column = false;
					}
					i++;
				}
				if (column == true) {
					i = header ? 1 : 0; 
					while (i < lines.length) {
						try {
							if (index == 0) {
								lines[i] = sep + lines[i].substring(index + 1);
							} else if (index >= lines[i].length()) {
								lines[i] = lines[i].substring(0, index) + sep;
							} else {
								lines[i] = lines[i].substring(0, index) + sep + lines[i].substring(index + 1);
							}
						} catch (Exception e) {
							System.out.println("column= " + index);
							System.out.println("line= " + i);
							System.out.println(lines[i]);
							e.printStackTrace();
						}
						i++;
					}
				}
			}
			index++;
			column = true;
		}

		i = header ? 1 : 0;
		while (i < lines.length) {
			while (lines[i].contains(sep + sep)) {
				lines[i] = lines[i].replaceAll(sep + sep, sep);
			}
			i++;
		}

		return lines;
	}

	public static void main(String[] args) {
//		String[] params = { "~/git/git2db/rcode", "files/2005-10.csv", "files/2005-10-cent.csv"};
//		String result = RUtil.runScript("centrality.r", "~/git/git2db/rcode", params);
//		System.out.println(result);

		String test = "     date              authors            LOC              code           comment          centrality    \n Length:18336       Min.   :  1.00   Min.   :   0.0   Min.   :   0.0   Min.   :   0.00   Min.   :0.0000  \n Class :character   1st Qu.:  6.00   1st Qu.:  67.0   1st Qu.:  49.0   1st Qu.:   1.00   1st Qu.:0.0000  \n Mode  :character   Median : 16.00   Median : 164.0   Median : 119.0   Median :  12.00   Median :0.0000  \n                    Mean   : 23.23   Mean   : 309.6   Mean   : 231.5   Mean   :  78.14   Mean   :0.1103  \n                    3rd Qu.: 33.00   3rd Qu.: 403.0   3rd Qu.: 265.2   3rd Qu.:  71.00   3rd Qu.:0.1107  \n                    Max.   :151.00   Max.   :3317.0   Max.   :3308.0   Max.   :1497.00   Max.   :1.0000 ";
		String[] lines = formatResults(test, "\t", false);

		for (String line : lines) {
			System.out.println(line);
		}
	}
	
	

}

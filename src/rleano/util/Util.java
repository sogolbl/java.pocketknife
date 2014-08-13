package rleano.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

	public static enum NType {INT, DOUBLE, FLOAT};

	
	/**
	 * Executes a command as if on the command line, returns the printout
	 * (This version does not throw exceptions)
	 * @param cmdLine
	 * @return
	 */
	public static String cmdExec(String[] cmdLine, String folder) {
		try {
			Runtime rt = Runtime.getRuntime();
			folder = fixPath(folder);
			Process proc = rt.exec(cmdLine, null, new File(folder));

			String errorMessage = "";
//			errorMessage = readCmdExec(proc.getErrorStream());
			String result = readCmdExec(proc.getInputStream());

			int exitVal = proc.waitFor();
			if (exitVal != 0) {
				String error = "ERROR:: Executing [" + PrintUtil.printArray(cmdLine, " ") + "]\nMessage: " + errorMessage;
				throw new Exception(error);
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		} catch (Throwable t) {
			t.printStackTrace();
			return "";
		}
	}

	public static String fixPath(String folder) {
		if (folder.startsWith("~/") == true) {
			folder = folder.replaceFirst("~/", System.getProperty("user.home") + "/");
		} else if (folder.startsWith("./") == true) {
			folder = folder.replaceFirst("./", System.getProperty("user.dir") + "/");
		}
		return folder;
	}

	/**
	 * Executes a command as if on the command line, returns the printout
	 * (This version does not throw exceptions)
	 * @param cmdLine
	 * @return
	 */
	public static List<String> cmdExecLines(String[] cmdLine, String folder) {
		List<String> result = new ArrayList<String>();
		try {
			Runtime rt = Runtime.getRuntime();
			folder = fixPath(folder);
			Process proc = rt.exec(cmdLine, null, new File(folder));
			
			// Outputs result if ok
			result = readCmdExecLines(proc.getInputStream());

			int exitVal = proc.waitFor();
			if (exitVal != 0) {
				throw new Exception("ERROR:: Executing [" + PrintUtil.printArray(cmdLine, " ") + "]");
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
	
	public static List<String> cmdExecLinesDebug(String[] cmdLine, String folder) {
		List<String> result = new ArrayList<String>();
		try {
			Runtime rt = Runtime.getRuntime();
			folder = fixPath(folder);
			Process proc = rt.exec(cmdLine, null, new File(folder));

			// Outputs result if ok
			result = readCmdExecLinesDebug(proc.getInputStream());

			int exitVal = proc.waitFor();
			if (exitVal != 0) {
				return null;
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	public static List<String> cmdExec(String cmdLine, String folder) {
		List<String> list = Util.cmdExecLines(cmdLine.split(" "), folder);
		return list;
	}
	 
	public static List<String> cmdExecDebug(String cmdLine, String folder) {
		List<String> list = Util.cmdExecLinesDebug(cmdLine.split(" "), folder);
		return list;
	}

	public static String cmdExecLiner(String cmdLine, String folder) {
		String result = Util.cmdExec(cmdLine.split(" "), folder);
		return result;
	}

	private static String readCmdExec(InputStream is) throws IOException {
		// Outputs if there's an error
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String message = "";
		try {
			int value = 0;
			// reads to the end of the stream 
			while ((value = br.read()) != -1) {
				char c = (char) value;
				message += c;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}

		return message;
	}

	private static List<String> readCmdExecLines(InputStream is) throws IOException {
		// Outputs if there's an error
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		List<String> lines = new ArrayList<String>();
		try {
			int value = 0;
			// reads to the end of the stream 
			String line = "";
			while ((value = br.read()) != -1) {
				char c = (char) value;
				if (c == '\n') {
					lines.add(line);
					line = "";
				} else {
					line += c;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}

		return lines;
	}
	
	private static List<String> readCmdExecLinesDebug(InputStream is) throws IOException {
		System.out.println("readCmdExecLinesDebug -- Start");
		// Outputs if there's an error
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		List<String> lines = new ArrayList<String>();
		try {
			int value = 0;
			// reads to the end of the stream 
			String line = "";
			while ((value = br.read()) != -1) {
				char c = (char) value;
				if (c == '\n') {
					lines.add(line);
					line = "";
				} else {
					line += c;
				}
				FileUtil.writeLine("output.log", "" + c, false);
				System.out.print("" + c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}

		System.out.println("readCmdExecLinesDebug -- End");
		return lines;
	}

	public static String truncate(String string, int size) {
		String result = string;
		if (result.length() > size) {
			result = result.substring(0, size);
		}
		return result;
	}

	public static void log(String string) {
		FileUtil.writeLine("output.log", string, false);
		System.out.println(string);
	}
	
	public static String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getWorkingDir() {
		return System.getProperty("user.dir");
	}
	
	public static String getHomeDir() {
		return System.getProperty("home.dir");
	}
	
	/**
	 * Converts double to String using the given format. <br>
	 * Examples: <br>
	 * formatNumber("00", 1) -- "01" <br>
	 * formatNumber("0000.000", 102.2899) -- 0102.290 <br>
	 * formatNumber("##00.000", 102.2) -- 102.200 <br>
	 * @return
	 */
	public static String formatNumber(String pattern, double value) {
	    DecimalFormat formatter = new DecimalFormat(pattern);
	    return formatter.format(value);
	 }


	public static void main(String[] args) {
//		String res = Util.cmdExecLiner("git log -1 --format=%H%n%P%n%an%n%ae%n%ai%n%cn%n%ce%n%ci c88d89a91a2076e24f2a8922148ea50eb039e091", "/Users/rafaelleano/git/work/jenkins/");
//		System.out.println(res);
//		
//		System.out.println("===");
//		
//		List<String> res2 = Util.cmdExec("git log -1 --format=%H%n%P%n%an%n%ae%n%ai%n%cn%n%ce%n%ci c88d89a91a2076e24f2a8922148ea50eb039e091", "/Users/rafaelleano/git/work/jenkins/");
//		System.out.println(PrintUtil.printStrings(res2, ", ", false));
//		
//		System.out.println("===");
//		
//		List<String> res3 = GitUtil.getCommit("/Users/rafaelleano/git/work/jenkins/", "c88d89a91a2076e24f2a8922148ea50eb039e091" );
//		System.out.println(PrintUtil.printStrings(res3, ", ", false));
		
		System.out.println(formatNumber("00", 1));
		System.out.println(formatNumber("0000.000", 102.2899));
		System.out.println(formatNumber("##00.000", 102.2));
	}
	
}

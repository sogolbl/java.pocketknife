package rleano.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles reading/writing to text files
 * 
 * @author rleano
 */
public class FileUtil {
	
	/**
	 * Returns the whole text file as a String. Should not be used for large
	 * text files.
	 * 
	 * @param file		The file that is going to be read.
	 * @return String	containing all the text in the file.
	 */
	public static String readFile(File file) {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append("\n");
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	/**
	 * Returns the whole text file as a String. Should not be used for large
	 * text files.
	 * 
	 * @param filename	The name of the file that is going to be read.
	 * @return String	containing all the text in the file.
	 */
	public static String readFile(String filename) {
		File file = new File(filename);
		if (file.exists() == false) {
			System.err.println("File not found [" + filename + "]");
			return "";
		}
		return readFile(file);
	}

	/**
	 * Returns the whole text file as a collection of Strings, it is not
	 * efficient for large files
	 * 
	 * @param file		The file that is going to be read.
	 * @return List		of Strings containing all the text in the file line by line
	 */
	public static List<String> readFileList(File file) {
		List<String> lines = new ArrayList<String>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}
	
	/**
	 * Returns the first line of the file
	 * @param filename		The file that is going to be read.
	 * @return String	content of the first line
	 */
	public static String readFileFirst(String filename) {
		File file = new File(filename);
		if (file.exists() == false) {
			System.err.println("File not found [" + filename + "]");
			return "";
		}
		return readFileFirst(file);
	}
	
	/**
	 * Returns the first line of the file
	 * @param file		The file that is going to be read.
	 * @return String	content of the first line
	 */
	public static String readFileFirst(File file) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = input.readLine()) != null) {
				return line;
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * Reads a file from a specific line onwards
	 * @param file
	 * @param start
	 * @return
	 */
	public static String readFileFrom(File file, int start) {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		int currLine = 1;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = input.readLine()) != null) {
				if (currLine >= start) {
					contents.append(line);
					contents.append("\n");
				}
				currLine++;
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	/**
	 * Returns the whole text file as a collection of Strings, it is not
	 * efficient for large files
	 * 
	 * @param filename		The name of the file that is going to be read.
	 * @return Collection	of Strings containing all the text in the file line by line
	 */
	public static List<String> readFileList(String filename) {
		File file = new File(filename);
		if (file.exists() == false) {
			System.err.println("File not found [" + filename + "]");
			return new ArrayList<String>();
		}
		return readFileList(file);
	}

	/**
	 * Reads a CSV file into a List<String>
	 * @param file	file to read
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return
	 */
	public static List<String[]> readCSV(String filename, boolean header) {
		File file = new File(filename);
		return readCSV(file, header);
	}

	/**
	 * Reads a CSV file into a List<String>
	 * @param file	file to read
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return
	 */
	public static List<String[]> readCSV(File file, boolean header) {
		List<String[]> lines = new ArrayList<String[]>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			if (line != null && header == true) {
				lines.add(line.split(","));
			}
			while ((line = input.readLine()) != null) {
				lines.add(line.split(","));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}
	
	/**
	 * Reads a CSV file into a List<String>
	 * @param file	file to read
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return
	 */
	public static String[] readCSVFirst(String filename, boolean header) {
		File file = new File(filename);
		return readCSVFirst(file, header);
	}

	/**
	 * Reads the first line of a CSV file
	 * @param file	file to read
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return String[]	the first line of the CVS file
	 */
	public static String[] readCSVFirst(File file, boolean header) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			if (line != null && header == true) {
				return line.split(",");
			}
			while ((line = input.readLine()) != null) {
				return line.split(",");
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String[] error = { "" };
		return error;
	}
	
	/**
	 * Reads a CSV file into a List<String>, keeping only one column
	 * @param file	file to read
	 * @param column	column to keep (starts at 0)
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return
	 */
	public static List<String> readCSVColumn(String filename, int column, boolean header) {
		File file = new File(filename);
		return readCSVColumn(file, column, header);
	}

	/**
	 * Reads a CSV file into a List<String>, keeping only one column
	 * @param file	file to read
	 * @param column	column to keep (starts at 0)
	 * @param header	true to keep the first line (header), false to not keep it
	 * @return
	 */
	public static List<String> readCSVColumn(File file, int column, boolean header) {
		List<String> lines = new ArrayList<String>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			if (line != null && header == true) {
				lines.add(line.split(",")[column]);
			}
			while ((line = input.readLine()) != null) {
				lines.add(line.split(",")[column]);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}
	
	public List<String[]> readCVS(String filename, int start, int end) {
		File file = new File(filename);
		return readCVS(file, start, end);
	}
	
	public List<String[]> readCVS(File file, int start, int end) {
		List<String[]> lines = new ArrayList<String[]>();
		BufferedReader input = null;
		int counter = 1;
		try {
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			while ((line = input.readLine()) != null && counter >= start && counter <= end) {
				lines.add(line.split(","));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}

	/**
	 * Writes the given String as the full content for the given file
	 * 
	 * @param file						The file to overwrite
	 * @param contents					The contents to write to the file
	 * @throws FileNotFoundException	If the file does not exist
	 * @throws IOException				If there is an exception while writing
	 */
	public static void writeFile(File file, String contents) throws FileNotFoundException, IOException {
		if (file == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (!file.exists()) {
			throw new FileNotFoundException("File does not exist: " + file);
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + file);
		}
		if (!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + file);
		}

		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write(contents);
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	/**
	 * Writes the given String as the full content for the given file. 
	 * WARNING: It will overwrite the file if it exists
	 * 
	 * @param filename	The name of the file to overwrite
	 * @param contents	The contents to write to the file
	 */
	public static void writeFile(String filename, String contents) {
		File file = new File(filename);
		if (file.exists() == true) {
			file.delete();
		}
		try {
			File parent = file.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			file.createNewFile();
			writeFile(file, contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the given String as the full content for the given file
	 * 
	 * @param file						The file to overwrite
	 * @param contents					The contents to write to the file
	 * @throws FileNotFoundException	If the file does not exist
	 * @throws IOException				If there is an exception while writing
	 */
	public static void writeFileList(String filename, List<String> contents, String header){
		File file = new File(filename);
		if (file.exists() == true) {
			file.delete();
		}
		try {
			File parent = file.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + file);
		}
		if (!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + file);
		}
		
		Writer output = null;
		try {
			try {
				output = new BufferedWriter(new FileWriter(file, true));
				if (header != null && header.isEmpty() == false) {
					output.write(header + "\n");
				}
				for (String line : contents) {
					output.write(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * It will write the given line at the end of the file
	 * @param filename	the name of the file to write to
	 * @param line		the contents of the line to write
	 * @param delete	if TRUE, it will delete the current content of the file and write on the first line
	 */
	public static void writeLine(String filename, String line, boolean delete) {
		File file = new File(filename);
		if (delete == true && file.isDirectory() == false) {
			file.delete();
		}
		if (!file.exists()) {
			try {
				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + file);
		}
		if (!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + file);
		}

		Writer output = null;
		try {
			try {
				output = new BufferedWriter(new FileWriter(file, true));
				output.write(line);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * It will write the given line at the end of the file
	 * @param filename	the name of the file to write to
	 * @param line		the contents of the line to write
	 * @param delete	if TRUE, it will delete the current content of the file and write on the first line
	 */
	public static void writeLineArrayInt(String filename, int[] lines, String separator, boolean delete) {
		File file = new File(filename);
		if (delete == true && file.isDirectory() == false) {
			file.delete();
		}
		if (!file.exists()) {
			try {
				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + file);
		}
		if (!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + file);
		}

		Writer output = null;
		try {
			try {
				output = new BufferedWriter(new FileWriter(file, true));
				int index = 0;
				while( index < lines.length - 1) {
					output.write(lines[index] + separator);
					index++;
				}
				output.write(lines[index] + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public static String getExtension(String file) {
		int dot = file.lastIndexOf(".");
		if (dot == -1) {
			return "";
		}
		return file.substring(dot + 1);
	}
	
	public static String getSimpleFilename(String filename) {
		int last = filename.lastIndexOf("/");
		if (last == -1) {
			return filename;
		}
		return filename.substring(last + 1);
	}
	
	public static void main(String[] args) {
//		String filename = "/Users/rafaelleano/git/git2db/new/folder/test/test.txt";
//		writeFile(filename, "hello world");
		
//		String filename = "contrib/stargate/core/src/main/java/org/apache/hadoop/hbase/stargate/RESTServlet.java";
//		String simple = FileUtil.getSimpleFilename(filename);
//		System.out.println(simple);
	}

}

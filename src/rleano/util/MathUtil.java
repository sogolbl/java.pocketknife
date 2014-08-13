package rleano.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class MathUtil {

	private static final double log2 = Math.log(2);

	public static double log2(double a) {
		if (a == 0.0) {
			return 0.0;
		}
		return Math.log(a) / log2;
	}

	public static String trunk(double number, int zeroes) {
		String hashes = "";
		for (int i = 0; i < zeroes; i++) {
			hashes += "#";
		}

		DecimalFormat df = new DecimalFormat("#." + hashes);
		return df.format(number);
	}

	public static double random() {
		Random randomGenerator = new Random();

		int random = randomGenerator.nextInt(101);
		double result = random / 100.0;
		return result - 0.5;

	}

	public static double randomPositive() {
		Random randomGenerator = new Random();

		int random = randomGenerator.nextInt(101);
		double result = random / 100.0;
		return result;
	}

	public static double random(int top) {
		Random randomGenerator = new Random();

		int random = randomGenerator.nextInt(top * 100 + 1);
		double result = random / 100.0;
		return result - 0.5;

	}

	public static double arrayMean(double[] results) {
		double total = 0;
		for (double value : results) {
			total += value;
		}

		return total / (double) results.length;
	}

	public static double listMean(List<Double> results) {
		double total = 0;
		for (double value : results) {
			total += value;
		}

		return total / (double) results.size();
	}

	public static double arrayDeviation(double[] results, double mean) {
		// use results[] to store square diff
		for (int i = 0; i < results.length; i++) {
			double diff = results[i] - mean;
			results[i] = Math.pow(diff, 2);
		}

		double squareMean = arrayMean(results);
		return Math.sqrt(squareMean);
	}

	public static double listDeviation(List<Double> results, double mean) {
		// use results[] to store square diff
		for (int i = 0; i < results.size(); i++) {
			double diff = results.get(i) - mean;
			double temp = Math.pow(diff, 2);
			results.set(i, temp);
		}

		double squareMean = listMean(results);
		return Math.sqrt(squareMean);
	}

	public static double s_dmean(List<Double> delta_is, double delta_mean) {
		double variance_temp = 0.0;
		int size = delta_is.size();

		for (Double delta_i : delta_is) {
			variance_temp += Math.pow(delta_i - delta_mean, 2);
		}
		variance_temp = variance_temp / (double) (size * (size - 1));

		return variance_temp;
	}

	public static boolean isNumeric(String word) {
		if (word.matches("(-|\\+)?[0-9]+(\\.[0-9]+)")) {
			return true;
		}
		try {
			Integer.parseInt(word);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Double div(int a, int b) {
		double ad = new Double(a);
		double bd = new Double(b);

		return ad / bd;
	}

	public static Integer getIntPercentage(int number, double percentage) {
		double value = Math.ceil(number * percentage);
		return (int) value;
	}

	static public void main(String[] args) {

	}

}

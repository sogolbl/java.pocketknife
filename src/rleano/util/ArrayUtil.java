package rleano.util;

import java.lang.reflect.Array;

public class ArrayUtil {
	
	@SuppressWarnings("unchecked")
	public static <T extends Object> T[] reduceArray(T[] array, int[] keep) {
		int size = keep.length;
		int count = 0;
		T[] newArray = (T[]) Array.newInstance(array[0].getClass(), size);
		for (int col : keep) {
			newArray[count++] = array[col];
		}
		return newArray;
	}
	
	public static int[] StringToInt(String[] array) {
		int[] newArray = new int[array.length];
		int counter = 0;
		for (String number : array) {
			newArray[counter++] = Integer.parseInt(number);
		}
		return newArray;
	}

	public static void main(String[] args) {
		String[] array = { "1", "2", "3", "4", "5", "6" };
		int[] keep = {0, 2, 4};
		
		String[] reduced = reduceArray(array, keep);
		System.out.println(PrintUtil.printArray(array, ", "));
		System.out.println(PrintUtil.printArray(reduced, ", "));
	}
}

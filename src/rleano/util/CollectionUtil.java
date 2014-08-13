package rleano.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rleano.util.Util.NType;

public class CollectionUtil {

	public static List<Cloneable> cloneList(List<Cloneable> list) {
		List<Cloneable> cloned = new ArrayList<Cloneable>();
		for (Cloneable element : list) {
			Cloneable clone = element.clone();
			cloned.add(clone);
		}
		return cloned;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> List<T> cloneListNumber(List<T> list, NType type) {
		List<T> cloned = new ArrayList<T>();
		for (Number instance : list) {
			Number clone = null;
			switch (type) {
			case INT:
				clone = instance.intValue();
				break;
			case DOUBLE:
				clone = instance.doubleValue();
				break;
			case FLOAT:
				clone = instance.floatValue();
				break;
			}
			cloned.add((T) clone);
		}
		return cloned;
	}

	public static List<String> cloneListString(List<String> collection) {
		List<String> clone = new ArrayList<String>();
		for (String instance : collection) {
			String temp = "" + instance;
			clone.add(temp);
		}
		return clone;
	}

	public static Set<String> cloneSetString(Set<String> collection) {
		Set<String> clone = new LinkedHashSet<String>();
		for (String instance : collection) {
			String temp = "" + instance;
			clone.add(temp);
		}
		return clone;
	}

	public static List<String> cloneToListString(Collection<String> collection) {
		List<String> clone = new ArrayList<String>();
		for (String instance : collection) {
			String temp = "" + instance;
			clone.add(temp);
		}
		return clone;
	}

	public static List<String> cloneSetToListString(Set<String> set) {
		List<String> clone = new ArrayList<String>();
		for (String instance : set) {
			String temp = "" + instance;
			clone.add(temp);
		}
		return clone;
	}

	public static Set<String> cloneArrayToSetString(String[] array) {
		Set<String> clone = new HashSet<String>(Arrays.asList(array));
		return clone;
	}

	public static List<String> cloneArrayToListString(String[] array) {
		List<String> clone = new ArrayList<String>();
		for (String instance : array) {
			String temp = "" + instance;
			clone.add(temp);
		}
		return clone;
	}

	public static int[] StringListToArray(List<String> list) {
		String[] array = list.toArray(new String[list.size()]);
		return ArrayUtil.StringToInt(array);
	}

	public static int getPosition(List<?> list, Object element, boolean array) {
		int position = -1;
		for (Object object : list) {
			position++;
			if (element.equals(object)) {
				position = (array == true) ? position : position + 1;
				return position;
			}
		}

		return -1;
	}

	/**
	 * Disclaimer: Not written by me, I found them on stackoverflow a while ago...
	 * Sorts a Map by its values instead of by its keys
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * Disclaimer: Not written by me, I found them on stackoverflow a while ago...
	 * Sorts a Map by its values instead of by its keys (descending order)
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueReverse(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return -(o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}

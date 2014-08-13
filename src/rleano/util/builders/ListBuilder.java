package rleano.util.builders;

import java.util.List;

import rleano.util.PrintUtil;
import rleano.util.Util;

public class ListBuilder {

	private String separator;
	private String string;
	private int size;
	
	public ListBuilder(){
		this.separator = ",";
		this.string = "";
		this.size = 0;
	}
	
	public ListBuilder(String separator){
		this();
		this.separator = separator;
	}
	
	public ListBuilder(String[] elements, String separator) {
		this();
		this.separator = separator;
		for (String element : elements) {
			this.add(element);
		}
	}
	
	public ListBuilder(String element1, String separator) {
		this();
		this.separator = separator;
		this.add(element1);
	}
	
	public ListBuilder(String element1, String element2, String separator) {
		this(element1, separator);
		this.add(element2);
	}
	
	public ListBuilder(String element1, String element2, String element3, String separator) {
		this(element1, element2, separator);
		this.add(element3);
	}
	
	public void add(String value){
		this.string += value + separator;
		size++;
	}
	
	public void add(Integer value){
		this.string += value + separator;
		size++;
	}
	
	public void add(Double value){
		this.string += value + separator;
		size++;
	}
	
	public String get(boolean endSeparator)
	{
		if (endSeparator == false) {
			return PrintUtil.removeLast(this.string.trim());
		}
		return this.string;
	}
	
	public String getPrettyPrint()
	{
		String result = PrintUtil.removeLast(this.string.trim());
		return result.replaceAll(separator, separator + " ").trim();
	}

	public void add(List<String> list, String prefix) {
		for (String value : list) {
			this.add(prefix + value);
		}
	}

	public int size() {
		return size;
	}
}

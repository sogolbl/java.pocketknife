package rleano.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropUtil{
	
	private Properties properties;
	
	public PropUtil(String file){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.properties = properties;
	}
	
	public String get(String key){
		return this.properties.getProperty(key);
	}
	
	public Integer getInt(String key){
		Integer value = Integer.parseInt(this.properties.getProperty(key));
		return value;
	}
	
	public Double getDouble(String key){
		Double value = Double.parseDouble(this.properties.getProperty(key));
		return value;
	}

	public List<String> getList(String key) {
		String[] array = this.getArray(key);
		return Arrays.asList(array);
	}

	public String[] getArray(String key) {
		return this.properties.getProperty(key).split(",");
	}

}

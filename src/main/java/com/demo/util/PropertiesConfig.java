package com.demo.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * [描述]:<br>
 * @author
 * @version [1.0, 2015年7月14日]
 * @since ias 1.0
 */
public abstract class PropertiesConfig {
	
	public static final String DEFAULT_PROPERTIES_FILE = "SystemConfig.properties";

	private static Map<String, PropertiesConfiguration> configMap = new HashMap<String, PropertiesConfiguration>();
	
	public static PropertiesConfiguration getInstance() {
		if(configMap.containsKey(DEFAULT_PROPERTIES_FILE) && null != configMap.get(DEFAULT_PROPERTIES_FILE)) {
			return configMap.get(DEFAULT_PROPERTIES_FILE);
		}else {
			return getPropertiesConfig(DEFAULT_PROPERTIES_FILE);
		}
	}
	/**
	 * 
	 * [描述]:<br>
	 * 
	 * @param fileName
	 * @return
	 */
	public static synchronized PropertiesConfiguration getPropertiesConfig(String fileName) {
		if(null == fileName) return null;
		PropertiesConfiguration tconfig = null;
		try {
			tconfig = new PropertiesConfiguration(fileName);
			configMap.put(fileName, tconfig);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return tconfig;
	}
	/**
	 * 
	 * [描述]:<br>
	 * 
	 * @param key
	 * @return
	 */
	public static synchronized String getProperties(String key) {
		return getProperties(DEFAULT_PROPERTIES_FILE, key) ;
	}
	/**
	 * 
	 * [描述]:<br>
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static synchronized String getProperties(String fileName, String key) {
		if(null == fileName || "".equals(fileName.trim())) fileName = DEFAULT_PROPERTIES_FILE;
		PropertiesConfiguration configuration = null;
		if(configMap.containsKey(fileName) && null != configMap.get(fileName)) {
			configuration = configMap.get(fileName);
		}else {
			configuration = getPropertiesConfig(fileName);
		}
		if(null == configuration) return null;
		return configuration.getString(key);
	}
	/**
	 * 
	 * [描述]:<br>
	 * 
	 * @param fileName
	 * @param propertiesMap
	 */
	public static synchronized void saveProperties(String fileName, Map<String, String> propertiesMap) {
		if(null == propertiesMap || propertiesMap.isEmpty()) return;
		if(null == fileName || "".equals(fileName.trim())) fileName = DEFAULT_PROPERTIES_FILE;
		PropertiesConfiguration configuration = getPropertiesConfig(fileName);
		if(null == configuration) return;
		for(Map.Entry<String, String> entry : propertiesMap.entrySet()) {
			configuration.addProperty(entry.getKey(), entry.getValue());
		}
		try {
			configuration.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}

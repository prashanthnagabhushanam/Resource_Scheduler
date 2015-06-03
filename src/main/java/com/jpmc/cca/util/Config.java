
package com.jpmc.cca.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Config			config;

	private static Object			lock	= new Object();

	private Properties				props	= new Properties();

	private Config() {

		FileInputStream fileinputstream = null;
		try {
			File propFileDir = new File(System.getProperty("user.dir"));
			fileinputstream = new FileInputStream(propFileDir + "/../src/main/config/resources.properties");
			props.load(fileinputstream);
		} catch (Exception e) {
			try {
				File propFileDir = new File(System.getProperty("user.dir"));
				fileinputstream = new FileInputStream(propFileDir + "/./src/main/config/resources.properties");
				props.load(fileinputstream);
			} catch (Exception e1) {
			}
		} finally {
			if (fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Config getConfig() {

		if (config == null) {
			synchronized (lock) {
				if (config == null) {
					config = new Config();
				}
			}
		}
		return config;
	}

	public static void reloadProperties() {

		config = null;
	}

	public String getProperty(String key) {

		return getProperty(key, "");
	}

	public Properties getProperties() {

		return props;
	}

	public String getProperty(String key, String val) {

		if (props.containsKey(key))
			return props.getProperty(key, val);
		return val;
	}

}

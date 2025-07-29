package com.test.utilities;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	public Properties readProperty(String filepath) throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(filepath);
		props.load(fis);
		return props;
	}
	
	public static Properties getLogInProperties() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\logIn.properties");
		Properties p = new Properties();
		p.load(file);
		return p;
	}
	
	public static Properties getMovieSeatProperties() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\movieSeat.properties");
		Properties p = new Properties();
		p.load(file);
		return p;
	}
}

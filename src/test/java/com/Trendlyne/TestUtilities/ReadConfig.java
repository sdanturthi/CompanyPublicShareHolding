package com.Trendlyne.TestUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	String configFilePath = System.getProperty("user.dir") + "/Configuration/Config.Properties";
	Properties pro;

	public ReadConfig() {
		File file = new File(configFilePath);

		try {
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getUrl() {
		String url = pro.getProperty("url");
		return url;
	}
	
	public String getChromeDriverPath() {
		String chromeDriverPath = pro.getProperty("chromeDriverPath");
		return chromeDriverPath;
	}
}

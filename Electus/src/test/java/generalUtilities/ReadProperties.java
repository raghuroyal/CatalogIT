package generalUtilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {
	Properties myProp;
	FileInputStream myFile;
	String filePath;

	public ReadProperties(String filePath) {
		this.filePath = filePath;
	}

	public String readValue(String key) {
		try {
			myFile = new FileInputStream(filePath);
			myProp = new Properties();
			myProp.load(myFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myProp.getProperty(key);
	}

}

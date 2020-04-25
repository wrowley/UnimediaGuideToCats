package prog;

import java.io.*;

public class TextFileReader {

	static private String newline = System.getProperty("line.separator");

	static public String getContents(File aFile) {
		StringBuilder fileContents = new StringBuilder();
		String line = null;

		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			while ((line = input.readLine()) != null) {
				fileContents.append(line + newline);
			}
			input.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return fileContents.toString();
	}
}

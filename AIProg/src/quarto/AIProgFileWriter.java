package quarto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AIProgFileWriter {
	/**Appends the text to write to filename*/
	public static void writeToExistingFile(String fileName, String textToWrite){
		try {
			PrintWriter toFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			toFileWriter.println(textToWrite);
			toFileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

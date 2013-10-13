package gps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GCFileReader {
	
	public static String readFile(String fileUrl){
		String everyThing = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileUrl));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line != null){
				sb.append(line);
				sb.append('\n');
				line = br.readLine();
			}
			everyThing = sb.toString();
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return everyThing;
	}
	
	/**Skal hente ut matrisen fra filen man leser inn*/
	public static boolean[][] getNeighbourMatrix(String gcText){
		for (int i = 0; i < gcText.split("\n").length; i++) {
			
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(readFile("Files/GCinput1"));
	}
}

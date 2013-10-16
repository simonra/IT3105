package gps;

import java.io.BufferedReader;
import java.io.FileReader;

//Reads files for graph color
public class GCFileReader {
	public static String readFile(String fileUrl) {
		String everyThing = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileUrl));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
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

	/** Skal hente ut matrisen fra filen man leser inn */
	public static boolean[][] getNeighborMatrix(String gcText) {
		String[] gcEdgeArray = gcText.split("\n");
		int numberOfVerices = Integer.parseInt(gcEdgeArray[0].split(" ")[0]);
		boolean[][] neighborMatrix = new boolean[numberOfVerices][numberOfVerices];
		int currentNode1;
		int currentNode2;
		for (int i = 1; i < gcEdgeArray.length; i++) {
			currentNode1 = Integer.parseInt(gcEdgeArray[i].split(" ")[0]);
			currentNode2 = Integer.parseInt(gcEdgeArray[i].split(" ")[1]);
			neighborMatrix[currentNode1][currentNode2] = true;
			neighborMatrix[currentNode2][currentNode1] = true;
		}
		return neighborMatrix;
	}
}

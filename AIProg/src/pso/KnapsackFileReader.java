package pso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class KnapsackFileReader {

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

	public static Double[][] standardKnapsack(String ksText, Random r) {
		Double[][] solutionSpace = new Double[Constants.KNAPSACKSIZE][3];
		String[] itemLines = ksText.split("\n");
		System.out.println(itemLines[0]);
		System.out.println(itemLines[0].split(",")[1]);
		for (int i = 0; i < itemLines.length; i++) {
			solutionSpace[i][0] = Double
					.parseDouble(itemLines[i].split(",")[0]);
			solutionSpace[i][1] = Double
					.parseDouble(itemLines[i].split(",")[1]);
			solutionSpace[i][2] = 1 + Constants.KNAPSACKMAXVOLUMEPERITEM
					* r.nextDouble();
		}

		return solutionSpace;
	}

}

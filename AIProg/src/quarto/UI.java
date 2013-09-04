package quarto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {

	/** Leser input fra konsollen */
	public String readInput() {
		String s = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			s = br.readLine();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return s;
	}

	public boolean isValidPlayer(String s) {

		if (s.equals("human") || s.equals("random") || s.equals("novice"))
			return true;

		if (s.equals("ai1") || s.equals("ai2") || s.equals("ai3")
				|| s.equals("ai4"))
			return true;

		return false;
	}

	public String selectPlayer() {

		String player = readInput();

		while (true) {
			if (isValidPlayer(player)) {
				break;
			} else {
				System.out
						.println("Please enter a valid string: human, random, novice or ai#");
				player = readInput();
			}
		}

		return player;
	}

	public String readCoordinates() {
		String xy = readInput();

		while (true) {
			int x = Integer.parseInt("" + xy.charAt(0));
			int y = Integer.parseInt("" + xy.charAt(1));
			if (xy.length() == 2 && x <= 3 && y <= 3)
				return xy;
			System.out.println("Please enter valid coordinates (xy)");
			xy = readInput();
		}
	}

	public int selectPiece() {
		String p = readInput();
		while (true) {
			if (p.length() == 1) {
				try {
					return Integer.parseInt(p);
				} catch (Exception e) {
				}
				System.out.println("Please enter valid input (an integer)");
				p = readInput();
			}
		}
	}
}

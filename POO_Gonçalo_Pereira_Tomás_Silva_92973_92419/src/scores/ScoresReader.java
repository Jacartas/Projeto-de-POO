package scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoresReader {
	public static ArrayList<Integer> getPlayerTopScores(String playerName) {
		File file = new File("Scores/" + playerName + ".txt");
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				int score = scanner.nextInt();
				list.add(score);
				scanner.nextLine();
			}
			scanner.close();
			return list;
		} catch (FileNotFoundException e) {
			return list;
		}

	}

	public static PlayerScore getPlayerScoreBest(String playerName) {
		PlayerScore player = new PlayerScore(playerName);
		ArrayList<Integer> list = getPlayerTopScores(playerName);
		for (Integer integer : list) {
			player.setScores(integer);
		}
		return player;
	}

	public static ArrayList<PlayerScore> getTopScoreLevel(int l) {
		File file = new File("Scores/topScores" + l + ".txt");
		ArrayList<PlayerScore> list = new ArrayList<>();
		try {
			Scanner s = new Scanner(file);
			s.nextLine();
			while (s.hasNextLine()) {
				String name;
				int moves;
				String temp = s.nextLine();
				name = temp.substring(temp.indexOf('>') + 1, temp.indexOf(':'));
				moves = Integer.parseInt(temp.substring(temp.indexOf(':') + 2, temp.lastIndexOf(' ')));
				PlayerScore player = new PlayerScore(name);
				for (int i = 1; i < l; i++) {
					player.setScores(0);
				}
				player.setScores(moves);
				list.add(player);
			}
			s.close();
			return list;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static String displayScoresLevel(int level) {
		ArrayList<PlayerScore> list = getTopScoreLevel(level);
		String str = "👑  Melhores Scores do nivel " + level + ": 👑 \n";
		int position = 1;
		for (PlayerScore player : list) {
			str += position + " -> " + player.getPlayerName() + " (" + player.getLevelScore(level) + " moves)\n";
			position++;
		}
		return str;
	}
}

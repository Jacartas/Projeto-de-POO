package scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ScoresWriter {
	public static void TopPersonalScoresWriter(PlayerScore player) {
		try {
			PrintWriter writer = new PrintWriter(new File("Scores/" + player.getPlayerName() + ".txt"));
			ArrayList<Integer> list = ScoresReader.getPlayerTopScores(player.getPlayerName());
			for (int i = 1; i <= player.getTotalLevels(); i++) {
				if (i > list.size())
					list.add(player.getLevelScore(i));
				else
					list.set(i - 1, Integer.min(player.getLevelScore(i), list.indexOf(i - 1)));
			}
			for (Integer integer : list) {
				writer.println(integer);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void topScoresWriter(PlayerScore player, int i) {
		File file = new File("Scores/topScores" + i + ".txt");
		ArrayList<PlayerScore> list = new ArrayList<>();
		if (ScoresReader.getTopScoreLevel(i) != null)
			list = ScoresReader.getTopScoreLevel(i);
		list.add(player);
		list.sort((p1, p2) -> p1.getLevelScore(i) - p2.getLevelScore(i));
		if (list.size() > 3)
			list.remove(list.size() - 1);
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.println("Top Scores on level " + i + ":");
			int position = 1;
			for (PlayerScore playerScore : list) {
				writer.println(position + "->" + playerScore.getPlayerName() + ": " + playerScore.getLevelScore(i)
						+ " movements");
				position++;
			}
			writer.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

}

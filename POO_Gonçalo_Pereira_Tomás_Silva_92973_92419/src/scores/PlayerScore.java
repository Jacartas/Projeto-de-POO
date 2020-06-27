package scores;

import java.util.ArrayList;

public class PlayerScore {
	
	private String playerName;
	
	private ArrayList<Integer> scores = new ArrayList<Integer>();

	
	public PlayerScore(String playerName) {
		super();
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName() {
		
	}

	public void setScores(int scores) {
		this.scores.add(scores);
		
	}
	public ArrayList<Integer> sendScores() {
		return scores;
	}
	
	public int getTotalScore() {
		int total=0;
		for (Integer integer : scores) {
			total+=integer;
		}
		return total;
	}
	
	public int getLevelScore(int l) {
		return scores.get(l-1);
	}
	
	public int getTotalLevels() {
		return scores.size();
	}

	@Override
	public String toString() {
		String str = playerName +":"+"\n";
		int level=1;
		for (Integer integer : scores) {
			str+="level "+ level+": "+integer+" movements"+"\n";
			level++;
		}
		return str;
	}
	
	
	

	
	
}

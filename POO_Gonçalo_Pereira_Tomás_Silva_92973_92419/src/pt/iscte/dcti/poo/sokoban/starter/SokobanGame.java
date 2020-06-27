package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import easterEggs.Music;
import objects.*;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import scores.PlayerScore;
import scores.ScoresReader;
import scores.ScoresWriter;

public class SokobanGame implements Observer {

	private static SokobanGame INSTANCE = null;
	private int level = 1;
	private String level0 = "levels/level0.txt";
	private Empilhadora empilhadora;
	private Set<AbstractObject> tiles;
	private List<Alvo> alvos;
	private PlayerScore player;
	private ImageMatrixGUI matrixGui=ImageMatrixGUI.getInstance();

	private SokobanGame() {
		showMessage("Bem-vindo ao Sokoban do ISCTE\nComandos:\nSetas-->Mover\nR-->Recomeçar nível\nBackSpace-->Sair e guardar pontos");
		player = new PlayerScore(JOptionPane.showInputDialog(null, "Nome do Jogador:"));
	}

	public void start() {
//		ImageMatrixGUI.getInstance().addImages(buildSampleLevel());
		String level = level0;
		tiles = LevelReader.levelReaderFromFile(new File(level));
		tiles.add(empilhadora);
		getListOfAlvos();
		matrixGui.setStatusMessage("Level:" + level0.substring(12, 13)+" " + empilhadora.moves()+" " + empilhadora.energy());
	}

	private void setLevel(int level) {
		level0 = level0.replace(level0.charAt(12), String.valueOf(level).charAt(0));
	}

//	private ArrayList<ImageTile> buildSampleLevel() {
//
//		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
//		for (int y = 0; y != 10; y++)
//			for (int x = 0; x != 10; x++)
//				sampleLevelTiles.add(new Chao(new Point2D(x, y)));
//
//		return sampleLevelTiles;
//	}

	public static SokobanGame getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SokobanGame();
		return INSTANCE;
	}

	public void setEmpilhadora(Empilhadora empilhadora) {
		this.empilhadora = empilhadora;
	}

	public Empilhadora getEmpilhadora() {
		return empilhadora;
	}

	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();
		if (lastKeyPressed == KeyEvent.VK_R) {
			restart();
			matrixGui.update();
			return;
		}
		if (lastKeyPressed == KeyEvent.VK_BACK_SPACE) {
			showMessage(player.toString());
			matrixGui.dispose();
		}

		if (empilhadora != null)
			empilhadora.move( Direction.directionFor(lastKeyPressed) );
		matrixGui.setStatusMessage("Level:" + level0.substring(12, 13)+" " + empilhadora.moves()+" " + empilhadora.energy());
		if (isComplete()) {
			player.setScores(empilhadora.getMoves());
			matrixGui.update();
			nextLevel();
		}
		matrixGui.update();
	}

	public MovableObject getMovableObject1(Point2D position) {
		for (AbstractObject obj : tiles)
			if (obj instanceof MovableObject && obj.getPosition().equals(position))
				return (MovableObject) obj;

		return null;
	}

	public ActiveObject getActiveObject(Point2D position) {
		for (AbstractObject obj : tiles)
			if (obj instanceof ActiveObject && obj.getPosition().equals(position))
				return (ActiveObject) obj;
		return null;
	}

	private boolean isComplete() {
		for (Alvo alvo : alvos) {
			if (!alvo.hasBoxIn())
				return false;
		}
		return true;
	}

	public void restart() {
		matrixGui.clearImages();
		start();
	}

	private void nextLevel() {
		if (JOptionPane.showConfirmDialog(null, "continuar?") == 0) {
			ScoresWriter.topScoresWriter(player, level);
			showMessage( ScoresReader.displayScoresLevel(level));
			setLevel(level++);
			matrixGui.clearImages();
			start();
		} else {
			matrixGui.dispose();
			showMessage( "Score atual de " + player + "\nMelhor score anterior de "
					+ ScoresReader.getPlayerScoreBest(player.getPlayerName()));
			ScoresWriter.TopPersonalScoresWriter(player);
			ScoresWriter.topScoresWriter(player, level);
		}
	}

	public void removeObject(AbstractObject object) {
		matrixGui.removeImage(object);
		tiles.remove(object);
	}

	private void getListOfAlvos() {
		alvos = new ArrayList<Alvo>();
		for (AbstractObject obj : tiles) {
			if (obj instanceof Alvo)
				alvos.add((Alvo) obj);
			matrixGui.addImage(obj);
		}
	} 
	public List<Alvo> getAlvos() {
		return alvos;
	}

	public int numberOfBoxes(){
		int i=0;
		for (AbstractObject obj : tiles)
			if(obj instanceof Caixote) i++;
		return i;
	}
	public void showMessage(String str){
		JOptionPane.showMessageDialog(null, str);
	}

	public void finishGame() {
		Music.playMusic("Musica/Queen - We Are The Champions (Official Video).wav");
		showMessage("Acabaste! Parabéns! \n És um CAMPEÃO ");
		matrixGui.dispose();
	}

}

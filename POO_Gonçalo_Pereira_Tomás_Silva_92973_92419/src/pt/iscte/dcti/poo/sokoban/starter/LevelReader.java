package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import objects.*;

public class LevelReader {
	public static Set<AbstractObject> levelReaderFromFile(File file) {
		Set<AbstractObject> set = new HashSet<>();
		try {
			Scanner scanner = new Scanner(file);
			boolean first = true;
			PortalAzul p1 = null;
			while (scanner.hasNextLine()) {
				for (int y = 0; y != 10; y++) {
					String str = scanner.nextLine();
					for (int x = 0; x != 10; x++) {
						switch (str.charAt(x)) {
						case '#':
							set.add(new Parede(new Point2D(x, y)));
							break;
						case 'b':
							set.add(new Bateria(new Point2D(x, y)));
							break;
						case 'X':
							set.add(new Alvo(new Point2D(x, y)));
							break;
						case 'C':
							set.add(new Caixote(new Point2D(x, y)));
							break;
						case 'O':
							set.add(new Buraco(new Point2D(x, y)));
							break;
						case 'E':
							SokobanGame.getInstance().setEmpilhadora(new Empilhadora(new Point2D(x, y)));
							break;
						case ' ':
							break;
						case 'P':
							set.add(new PedraGrande(new Point2D(x, y)));
							break;
						case 'p':
							set.add(new PedraPequena(new Point2D(x, y)));
							break;
						case 'g':
							set.add(new Gelo(new Point2D(x, y)));
							break;
						case '%':
							set.add(new ParedePartida(new Point2D(x, y)));
							break;
						case 'm':
							set.add(new Martelo(new Point2D(x, y)));
							break;
						case 't':
							if (first) {
								p1 = new PortalAzul(new Point2D(x, y), null);
								set.add(p1);
								first = false;
							} else {
								PortalAzul p2 = new PortalAzul(new Point2D(x, y), p1.getPosition());
								set.add(p2);
								p1.setOtherPosition(p2.getPosition());
							}

						}
						ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(x, y)));
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			SokobanGame.getInstance().finishGame();
		}
		return set;
	}
}

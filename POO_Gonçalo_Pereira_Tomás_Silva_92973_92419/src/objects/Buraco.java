package objects;

import javax.swing.JOptionPane;

import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractObject implements ActiveObject {

	public Buraco(Point2D position) {
		super(position, "Buraco", 0);
	}

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		SokobanGame game= SokobanGame.getInstance();
		if (object instanceof Empilhadora ) {
			game.showMessage("Seu Noob,agora tens de repetir!!");
			game.restart();
		}
		 if(object instanceof Caixote){
			 if(game.numberOfBoxes()==SokobanGame.getInstance().getAlvos().size()){
				 game.showMessage("Seu Noob,agora tens de repetir!!");
					game.restart();
			 }else
				 game.removeObject(object);
		 }
		if (object instanceof PedraGrande) {
			((PedraGrande) object).setCanMove();
		}
		if (object instanceof PedraPequena)
			game.removeObject(object);

	}

}

package objects;

import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractObject implements ActiveObject{

	public Alvo(Point2D position){
        super(position,"Alvo",1);
    }

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}
	public boolean hasBoxIn(){
		return SokobanGame.getInstance().getMovableObject1(super.getPosition()) instanceof Caixote;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		
	}

}

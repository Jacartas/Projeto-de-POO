package objects;
import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractObject implements ActiveObject {

    public Bateria(Point2D position){
        super(position,"Bateria",1);
    }

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		if(object instanceof Empilhadora)
            ((Empilhadora)object).recharge();
            SokobanGame.getInstance().removeObject(this);
		
	}


}

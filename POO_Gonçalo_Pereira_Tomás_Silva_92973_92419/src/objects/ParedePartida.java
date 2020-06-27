package objects;
import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ParedePartida extends AbstractObject implements ActiveObject{
    
    public ParedePartida(Point2D position){
        super(position,"Parede_Partida",1);
    }

	@Override
	public boolean canMove(Direction direction) {
		return false;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		if(object instanceof Empilhadora && ((Empilhadora)object).isMartelo()) {
			SokobanGame.getInstance().removeObject(this);
		}			
	}
}

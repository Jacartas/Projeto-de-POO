package objects;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractObject implements ActiveObject{
	
    public Parede(Point2D position){
        super(position,"Parede",1);
    }

	@Override
	public boolean canMove(Direction direction) {
		return false;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		
	}

    

}

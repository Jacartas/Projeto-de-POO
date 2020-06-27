package objects;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public interface ActiveObject {
	//public boolean isOccupied();
	public  boolean canMove(Direction direction);
	public void doAction(Direction direction  ,AbstractObject object  );
}

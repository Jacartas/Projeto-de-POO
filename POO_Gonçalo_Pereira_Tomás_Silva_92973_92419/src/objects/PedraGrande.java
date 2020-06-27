package objects;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class PedraGrande extends MovableObject {

	private boolean canMove = true;

	public PedraGrande(Point2D position) {
		super(position, "BigStone", 1);
	}

	public void setCanMove() {
		this.canMove = false;
	}

	@Override
	public boolean canMove(Direction direction) {
		return canMove ? super.canMove(direction) : false;
	}

}

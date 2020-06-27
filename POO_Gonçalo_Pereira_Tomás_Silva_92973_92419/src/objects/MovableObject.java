package objects;

import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class MovableObject extends AbstractObject {
	SokobanGame game= SokobanGame.getInstance();

	public MovableObject(Point2D position, String imageName, int layer) {
		super(position, imageName, layer);
	}

	public void move(Direction direction) {
		Point2D newPosition = super.getPosition().plus(direction.asVector());
		if (canMove(direction)) {
			super.setPosition(newPosition);
			ActiveObject obj = game.getActiveObject(newPosition);
			if (obj != null)
				obj.doAction(direction, this);
		}

	}

	public boolean canMove(Direction direction) {
		Point2D newPosition = super.getPosition().plus(direction.asVector());
		MovableObject obj = game.getMovableObject1(newPosition);
		if (obj != null) {
			return false;
		}
		ActiveObject obj2 = game.getActiveObject(newPosition);
		return (obj2 != null && !obj2.canMove(direction)) ? false : true;

	}

}

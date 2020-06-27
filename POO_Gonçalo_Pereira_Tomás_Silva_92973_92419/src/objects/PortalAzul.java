package objects;

import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class PortalAzul extends AbstractObject implements ActiveObject {

	private Point2D otherPosition;

	public PortalAzul(Point2D position, Point2D otherPosition) {
		super(position, "Portal_Azul", 1);
		this.otherPosition = otherPosition;
	}

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}

	public void setOtherPosition(Point2D otherPosition) {
		this.otherPosition = otherPosition;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		if (SokobanGame.getInstance().getMovableObject1(otherPosition) == null)
			object.setPosition(otherPosition);
	}

}

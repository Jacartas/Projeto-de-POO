package objects;

import pt.iscte.dcti.poo.sokoban.starter.SokobanGame;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Empilhadora extends MovableObject {

	private int movements = 0;
	private int energy = 100;
	private boolean martelo = false;
	private SokobanGame game= SokobanGame.getInstance();

	public Empilhadora(Point2D position) {
		super(position, "Empilhadora_U", 3);
	}

	public void setImageName(Direction d) {
		super.setImageName("Empilhadora_" + d.name().charAt(0));
	}

	public String moves() {
		return "Moves:" + movements;
	}

	public String energy() {
		return "Energy:" + energy;
	}

	public int getMoves() {
		return movements;
	}

	public void setMartelo(boolean martelo) {
		this.martelo = martelo;
	}

	public boolean isMartelo() {
		return martelo;
	}

	private void moved() {
		movements++;
		energy--;
		if (energy == 0)
			game.restart();
	}

	public void recharge() {
		energy = 100;
	}
	@Override
	public void move(Direction direction) {
		//Direction direction = Direction.directionFor(keycode);
		setImageName(direction);
		Point2D newPosition = super.getPosition().plus(direction.asVector());
		MovableObject object = game.getMovableObject1(newPosition);
		if (object != null) {
			if (!object.canMove(direction)) {
				return;
			} else {
				object.move(direction);
				setPosition(newPosition);
				moved();
				return;
			}
		}
		ActiveObject obj2 = game.getActiveObject(newPosition);
		if (obj2 != null) {
			if (obj2.canMove(direction)) {
				setPosition(newPosition);
				obj2.doAction(direction, this);
				moved();
				return;
			} else {
				obj2.doAction(direction, this);
				return;
			}
		}
		setPosition(newPosition);
		moved();
	}

}

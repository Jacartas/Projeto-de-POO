package objects;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractObject implements ActiveObject{
 public Gelo(Point2D position){
        super(position,"Gelo",1);
    }

	@Override
	public boolean canMove(Direction direction) {
		return true;
	}

	@Override
	public void doAction(Direction direction, AbstractObject object) {
		if (object instanceof MovableObject) {
			if (!((MovableObject)object).canMove(direction)) {
				return;
			} else {
				try {
					Thread.sleep(200);
					ImageMatrixGUI.getInstance().update();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				((MovableObject)object).move(direction);
				return;
			}
		}
		
		
	}
}

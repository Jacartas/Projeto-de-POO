package objects;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractObject implements ImageTile {
	
	private Point2D position;
	private String imageName;
	private int layer;
	
	
    public AbstractObject(Point2D position,String imageName,int layer) {
        this.position = position;
        this.imageName=imageName;
        this.layer=layer;
    }
    
    public Point2D getPosition() {
        return position;
    }
    
    public void setPosition(Point2D position) {
        this.position = position;
    }
    
    public  String getName(){
    	return imageName;
    }
    
    public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getLayer(){
    	return layer;
    } 
	public boolean samePosition(AbstractObject obj){
		return this.getPosition().equals(obj.getPosition());
	}

}

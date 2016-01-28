import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


/** Class Vehicle is the superclass or RailCar and TrainEngine
 * @author Lionel Pereira (500510610)
 *
 */
public abstract class Vehicle extends JComponent {
	private int x, y;
	private Rectangle bbox;
	protected Vehicle trailer;
	private boolean selected;
	private StorageContainer top;
	
	
	
	/**Constructor of a Vehicle.
	 * @param x: X coordinate of the Vehicle.
	 * @param y: Y coordinate of the Vehicle.
	 * @param width: The width of the Vehicle.
	 * @param height: The height of the Vehicle.
	 */
	public Vehicle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.bbox = new Rectangle(x, y, width, height);
		this.trailer = null;
		this.selected = false;
		this.top = null;
	}
	
	/* Method getX return the X coordinate of the Vehicle.
	 * @return: X coordinate of this Vehicle.
	 */
	public int getX() {
		return x;
	}
	
	/**Method setX sets the X coordinate of the Vehicle
	 * @param value: The new X coordinate.
	 */
	public void setX(int value){
		this.x = value;
	}
	
	/* Method getY return the Y coordinate of the Vehicle.
	 * @return: Y coordinate of this Vehicle.
	 */
	public int getY() {
		return y;
	}
	
	/**Method setY sets the Y coordinate of the Vehicle
	 * @param value: The new Y coordinate.
	 */
	public void setY(int value){
		this.y = value;
	}

	/**Method getTrailer returns the trailer of this Vehicle.
	 * @return: The trailer of this Vehicle.
	 */
	public Vehicle getTrailer() {
		return this.trailer;
	}

	/**Method setTrailer sets the trailer of this Vehicle to the value hitch.
	 * @param hitch: The new hitch of this Vehicle.
	 */
	public void setTrailer(Vehicle hitch) {
		this.trailer = hitch;
	}
	
	/**Method hasTrailer returns whether this Vehicle has a trailer.
	 * @return: Boolean result of whether this Vehicle has a trailer.
	 */
	public boolean hasTrailer(){
		return this.trailer != null;
	}
	
	/**Method getBBox returns the Bounding Rectangle of this Vehicle.
	 * @return: THe bounding rectanlgle of this Vehicle.
	 */
	public Rectangle getBBox(){
		return this.bbox;
	}
	
	/**Method setSelected sets the selected value of this Vehicle to the
	 * given value.
	 * @param value: The new selection value of this Vehicle.
	 */
	public void setSelected(boolean value){
		this.selected = value;
		if (this.hasTrailer()){
			this.trailer.setSelected(value);
		}
	}
	
	/**Method getSelected returns the selected value of this Vehicle.
	 * @return: The selected value of this Vehicle.
	 */
	public boolean getSelected(){
		return this.selected;
	}
	
	/**Method intersects returns whether this Vehicle intersects another.
	 * @param other: The other Vehicle to check.
	 * @return: The result of the comparison between the two Vehicles.
	 */
	public boolean intersects(Vehicle other){
		return this.bbox.intersects(other.getBBox());
	}
	
	/**Method hitch attaches the given Vehicle to the end other Vehicles 
	 * connected to this Vehicle.
	 * @param car: The Vehicle to connect.
	 */
	public void hitch(Vehicle car){
		if (car != null){
			if (this.hasTrailer()){
				this.trailer.hitch(car);
			}
			else{
				this.trailer = car;
			}
		}
		else{
			this.trailer = null;
		}
	}
	
	
	/* Method move moves this vehicle and connected vehicles to
	 *  the specifeed X and Y coordinates
	 * @param newX: The new X coordinate of this Vehicle.
	 * @param newY: The new Y coordinate of this Vehicle.
	 */
	public void move(int newX, int newY){
		this.x = newX;
		this.y = newY;
		this.bbox.setLocation(newX, newY);
		if (this.hasTop()){
			this.top.setLocation(this.x + 17, this.y - 15);
		}
		if (this.hasTrailer()){
				this.trailer.move(x + bbox.width, y);
		}
	}
	
	/**Method within checks if the specified X and  values are inside this
	 * Vehicle.
	 * @param xVal: The X value to check.
	 * @param yVal: The Y value to check.
	 * @return: Whether the X and Y values are within this Vehicle.
	 */
	public boolean within(int xVal, int yVal){
		return this.bbox.contains(xVal, yVal);
	}
	
	/**Method setTop sets the top of this Vehicle to a StorageContainer.
	 * @param stor: New StorageContainer to be set.
	 */
	public void setTop(StorageContainer stor){
    	this.top = stor;
    	if (stor != null){
    		stor.setLocation(super.getX(), super.getY());
    	}
    }
    
    /**Method hasTop returns whether this Vehicle has a top.
     * @return
     */
    public boolean hasTop(){
    	return this.top != null;
    }
    
    /**Method getTop returns the top of this Vehicle.
     * @return: The top value of this vehicle.
     */
    public StorageContainer getTop() {
		return top;
	}
	
	
	/**Method draw is a method to be overridden in the subclasses.
	 * @param g2: The graphic object which the Vehicle is draw to.
	 */
	public void draw(Graphics2D g2){
	}
	
	public String toString(){
		String result = "";
		if (!this.hasTrailer()){
			result += x + " : " + y;
		}
		else {
			result +=  x + " : " + y + this.trailer.toString();
		}
		return "[" + result + "]"; 
	}
}

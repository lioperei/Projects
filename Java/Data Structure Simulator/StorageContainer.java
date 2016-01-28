import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


/**Class StorageContainer are the objects used for StorageStack and attached to 
 * RailCars.
 * @author Lionel Pereira (500510610)
 *
 */
public class StorageContainer {
	private StorageContainer top;
	private String name;
	private Rectangle bbox;
	protected final static int width = 25;

	
	/**Constructor of StorageContainer
	 * @param name: Name of StorageContainer.
	 */
	public StorageContainer(String name){
		this.name = name;
		this.bbox = new Rectangle(width, width);
		this.top = null;
	}


	/**Method getTop returns the tops of this StorageContainer.
	 * @return: The top of this StroageContainer.
	 */
	public StorageContainer getTop() {
		return top;
	}


	/**Method setTop sets the given StorageContainer on top of this StorageContainer.
	 * @param top: The StorageContainer to be set.
	 */
	public void setTop(StorageContainer top) {
		this.top = top;
	}


	/**Method getName returns the name of this StorageContainer
	 * @return The name of this StorageContainer.
	 */
	public String getName() {
		return name;
	}


	/**Method getBbox returns the bounding box of this StorageContainer.
	 * @return The bounding box of this StorageContainer.
	 */
	public Rectangle getBbox() {
		return bbox;
	}
	
	/**Method setLocation sets the location of this StorageContainer and any
	 * attached StorageContainers relevant to the given X and Y values.
	 * @param newX: X coordinate to set the location of the StorageContainer. 
	 * @param newY: Y coordinate to set the location of the StorageContainer.
	 */
	public void setLocation(int newX, int newY){
		this.bbox.setLocation(newX, newY);
		if (this.top != null){
			this.top.setLocation(newX, newY - width);
		}
	}
	
	/**Method draw draws the StorageContainer and any attached StorageContainers to the
	 * given graphics object.
	 * @param g2: The graphics object to draw on.
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.GREEN);
		g2.draw(bbox);
		g2.setColor(Color.BLACK);
		g2.drawString(name, (int) bbox.getX() + width / 3,
				(int) bbox.getY() + 3 * (width / 4));
		if (this.top != null){
			this.top.draw(g2);
		}
	}
}

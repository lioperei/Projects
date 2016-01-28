import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


/**Class StorageStack holds the StorageContainer object of the simulator.
 * @author Lionel Pereira (500510610)
 *
 */
public class StorageStack {
	
	private Rectangle base;
	private StorageContainer top;
	private static final String[] names = {"A", "B", "C", "D", "E"};
	
	
	/**Constructor the the StorageStack
	 * @param x: X coordinate of the Stack
	 * @param y: X coordinate of the Stack
	 */
	public StorageStack(int x, int y){
		this.base = new Rectangle(x, y, 40, 10);
		StorageContainer first = new StorageContainer(names[0]);
		StorageContainer next = first;;
		for (int i = 1; i < 5; i++){
			next.setTop(new StorageContainer(names[i]));
			next = next.getTop();
		}
		first.setLocation(x + 7, y - 25);
		this.top = first;
	}

	/**Method isEmpty return whether this stack is empty.
	 * @return: Whether this stack is empty.
	 */
	public boolean isEmpty(){
		return this.top == null;
	}
	
	/**Method pop returns the top most StorageContainer attached to this Stack if one 
	 * exists.
	 * @return: The top most StorageContainer.
	 */
	public StorageContainer pop(){
		StorageContainer previous = null;
		StorageContainer current = this.top;
		if (current == null) return null;
		if (current.getTop() == null){
			this.top = previous;
			return current;
		}
		while (current.getTop() != null){
			previous = current;
			current = current.getTop();
		}
		previous.setTop(null);
		return current;
	}
	
	/**Method push sets the given StorageContainer at the top of this Stack.
	 * @param stor: StorageContainer to be set.
	 */
	public void push(StorageContainer stor){
		StorageContainer current = this.top;
		if (current == null){
			this.top = stor;
		}
		if (current.getTop() == null){
			current.setTop(stor);
		}
		else{
			while (current.getTop() != null){
				current = current.getTop();
			}
			current.setTop(stor);
			if (current.getTop() != null){
				current.getTop().setLocation(current.getBbox().x,
						current.getBbox().y - StorageContainer.width);
			}
		}
	}
	
	/**Method draw draws the StorageStack on the given Graphic object.
	 * @param g2: The graphics object to draw on.
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.BLACK);
		g2.fill(base);
		if (top != null){
			top.draw(g2);
		}
	}

}

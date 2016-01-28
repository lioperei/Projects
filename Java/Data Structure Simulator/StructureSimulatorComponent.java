import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import java.util.Random;

import javax.swing.JComponent;


/**Class StructureSimulatorComponent contains the logic of the simulator.
 * The Vehicles and stack are craeted with stack and list methods.
 * @author Lionel Pereira (500510610)
 *
 */
public class StructureSimulatorComponent extends JComponent{
	private static ArrayList<Vehicle> list;
	private static Vehicle selected;
	private static TrainEngine engine;
	private static int clicks;
	private static StorageStack stack;
	private static StructureMouseListener listener;
	private static Random random = new Random();
	
	
	/**The Constructor method for the Component.
	 * 
	 */
	public StructureSimulatorComponent(){
		
		list = new ArrayList<Vehicle>();
		listener = new StructureMouseListener();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		clicks = 0;
		selected = null;
		engine = null;
		stack = null;
		}
		
		/**Method addFirst that adds the selected RailCar directly behind the 
		 * TrainEngine.
		 */
		public void addFirst(){
			if (selected != null && selected != engine){
				selected.hitch(engine.getTrailer());
				engine.setTrailer(selected);
				engine.move(engine.getX() + 0, engine.getY());
				list.remove(selected);
				selected.setSelected(false);
				selected = null;
			}
			repaint();
		}
		
		/**Method addLast that adds the selected RailCar to the end of the
		 * TrainEngine.
		 */
		public void addLast(){
			if (selected != null){
				engine.hitch(selected);
				engine.move(engine.getX(), engine.getY());
				list.remove(selected);
				selected.setSelected(false);
				selected = null;
				repaint();
			}
		}
		
		/**Method removeFirst removes the first RailCar directly behind
		 *  the TrainEngine if there is one, and move it to a random location
		 *  within the frame.
		 */
		public void removeFirst(){
			if (selected != null) selected.setSelected(false);
			selected = null;
			Vehicle rm = engine.getTrailer();
			if (rm != null){
				engine.setTrailer(rm.getTrailer());
				engine.move(engine.getX(), engine.getY());
				rm.setTrailer(null);
				rm.move(random.nextInt(StructureSimulatorFrame.FRAME_WIDTH - 65),
						random.nextInt(StructureSimulatorFrame.FRAME_HEIGHT - 20));
				list.add(rm);
				repaint();
			}
		}
		
		/**Method removeLast removes the last RailCar behind
		 *  the TrainEngine if there is one, and move it to a random location
		 *  within the frame.
		 */
		public void removeLast(){
			if (selected != null) selected.setSelected(false);
			selected = null;
			Vehicle previous = null;
			Vehicle current = engine.getTrailer();
			if (current == null) return ;
			if (current.getTrailer() == null) {
			    removeFirst() ;
			    return ;
			}
			while (current.getTrailer() != null) {
			    previous = current ;
			    current = current.getTrailer();
			}
			previous.setTrailer(null);
			current.move(random.nextInt(StructureSimulatorFrame.FRAME_WIDTH - 65),
					random.nextInt(StructureSimulatorFrame.FRAME_HEIGHT - 20));
			list.add(current);
			repaint() ;
		}
		
		/**Method pop remove the pops the last StorageContainer of the stack
		 * and adds it to the top of the  first available RailCar hitched
		 * to the selected Vehicle if the stack is not empty. 
		 */
		public void pop(){
			StorageContainer pop =  stack.pop();
			Vehicle next = null;
			if (selected == engine){
				next =  engine.getTrailer();
				while (next != null){
					if (!next.hasTop()){
						next.setTop(pop);
						next.move(next.getX(), next.getY());
						repaint();
						return;
					}
					next = next.getTrailer();
				}
				stack.push(pop);
			}
			else if (selected != null) { 
				next = selected;
				while (next != null){
					if (!next.hasTop()){
						next.setTop(pop);
						next.move(next.getX(), next.getY());
						repaint();
						return;
					}
					next = next.getTrailer();
				}
				stack.push(pop);
			}
			else{
				stack.push(pop);
			}
			//repaint();
		}
		
		/**Method push remove the StorageContainer of the first RailCar hitched
		 * to the selected Vehicle that has a StorageContainer attached to it.
		 */
		public void push(){
			Vehicle next = null;
			StorageContainer push =  null;
			if (selected == engine){
				next =  engine.getTrailer();
				while (next != null){
					if (next.hasTop()){
						push = next.getTop();
						next.setTop(null);
						break;
					}
					next = next.getTrailer();
				}
				stack.push(push);
			}
			else if (selected != null) { 
				next = selected;
				while (next != null){
					if (next.hasTop()){
						push = next.getTop();
						next.setTop(null);
						break;
					}
					next = next.getTrailer();
				}
				stack.push(push);
			}
			repaint();
		}
	
	/**Class StructureMouseListener is the listener class that handles mouse 
	 * events of the simulator.
	 * @author Lionel Pereira (500520620)
	 *
	 */
	class StructureMouseListener extends MouseAdapter{

		/* Method mousePressed handles when the mouse is pressed.
		 * - on first 7 clicks Engine, 5 RailCars and a Stack are made in that order
		 * - every other clicks checks if a Vehicle has been selected.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			clicks++;
			if (clicks > 7){
				if (engine.within(e.getX(), e.getY())){
					if (selected != null){
						selected.setSelected(false);
					}
					selected = engine;
					selected.setSelected(true);
				}
				else{
					boolean intersect = false;
					for(Vehicle car: list){
						if (car.within(e.getX(), e.getY())){
							intersect = true;
							if (selected != car){
								if (selected == null){
									selected = car;
									selected.setSelected(true);
									break;
								}
								else{
									selected.setSelected(false);
									selected = car;
									selected.setSelected(true);
									break;
								}
							}
						}
					}					
					if (!intersect && selected != null){
						selected.setSelected(false);
						selected = null;
					}
				}
			}
			
			if (clicks == 1){
				engine = new TrainEngine(e.getX(), e.getY());
			}
			
			if (1 < clicks && clicks < 7){
				RailCar next = new RailCar(e.getX(), e.getY(), clicks - 1);
				boolean intersects = false;
				
				if (next.intersects(engine)){
					clicks--;
				}
				else {
					for(Vehicle car: list){
						if (next.intersects(car)){
							intersects = true;
							clicks--;
							break;
						}
					}
				}
				
				if (!intersects) list.add(next);
			}
			
			if (clicks == 7){
				stack = new StorageStack(e.getX(), e.getY());
			}
			repaint();
			

		}

		/* Method mouseDragged calls method moveStructure to move a selected
		 * Vehicle to the location of the mouse.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			if (clicks > 7){
				moveStructure(e);
				repaint();
			}
			
		}
		
		/* Method mouseReleased checks if :
		 * - selected Vehicle is on top of another vehicle.
		 * 	- hitch selected Vehicle to overlapped Vehicle iff the
		 *     selected Vehicle isn't the TrainEngine.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			if (clicks > 7 && selected != null){
				if (selected.intersects(engine) && selected != engine){
					engine.hitch(selected);
					list.remove(selected);
					selected = engine;
				}
				else{
					for (Vehicle veh: list){
						if (selected.intersects(veh) && selected != veh
								&& selected != engine){
							veh.hitch(selected);
							list.remove(selected);
							veh.setSelected(true);
							selected = veh;
							break;
						}
					}
				}
				moveStructure(e);
				repaint();
			}
		}

		
		/**Method moveStructure moves the selected Vehicle to the 
		 * location of the mouse event.
		 * @param event: The mouse event to move the selected Vehicle to.
		 */
		public void moveStructure(MouseEvent event){
			if (selected != null){
		        selected.move(event.getX(), event.getY());
			}
		}
	}

	
	
	/* Method paintComponent draws the component.
	 * @param g: The Graphics object the component draws to.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (engine != null){
			engine.draw(g2);
		}
		for (Vehicle v : list){
			v.draw(g2);
		}
		if (stack != null){
			stack.draw(g2);
		}
	}
}
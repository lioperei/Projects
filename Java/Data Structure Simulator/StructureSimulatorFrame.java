import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**Class StructureSimulatorFrame holds the Simulator component
 * as well as the menu options of the simulator.
 * @author Lionel Pereira (500510610)
 *
 */
public class StructureSimulatorFrame extends JFrame {

	protected static final int FRAME_WIDTH = 500;
	protected static final int FRAME_HEIGHT = 600;
	private static StructureSimulatorComponent component;
	private final JMenuBar menuBar;
	private final JMenu fileMenu;
	private final JMenu stackMenu;
	private final JMenu listMenu;
	private final JMenuItem newItem;
	private final JMenuItem exitItem;
	private final JMenuItem pushItem;
	private final JMenuItem popItem;
	private final JMenuItem addFirstItem;
	private final JMenuItem addLastItem;
	private final JMenuItem removeFirstItem;
	private final JMenuItem removeLastItem;
		
	
	/**Constructor of the StructureSimulatorFrame.
	 * 
	 */
	public StructureSimulatorFrame(){
		component = new StructureSimulatorComponent();
		this.add(component);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		ActionListener structureListener = new StructureListener();
		
		fileMenu = new JMenu("File");
	    newItem = new JMenuItem("New");
	    exitItem = new JMenuItem("Exit");
	    newItem.addActionListener(structureListener);
	    exitItem.addActionListener(structureListener);
	    fileMenu.add(newItem);
	    fileMenu.add(exitItem);
	    menuBar.add(fileMenu);
	    
	    stackMenu = new JMenu("Stack");
	    popItem = new JMenuItem("Pop");
	    pushItem = new JMenuItem("Push");
	    popItem.addActionListener(structureListener);
	    pushItem.addActionListener(structureListener);
	    stackMenu.add(popItem);
	    stackMenu.add(pushItem);
	    menuBar.add(stackMenu);
	    
	    listMenu = new JMenu("List");
	    addFirstItem = new JMenuItem("Add First");
	    addLastItem = new JMenuItem("Add Last");
	    removeFirstItem = new JMenuItem("Remove First");
	    removeLastItem = new JMenuItem("Remove Last");
	    addFirstItem.addActionListener(structureListener);
	    addLastItem.addActionListener(structureListener);
	    removeFirstItem.addActionListener(structureListener);
	    removeLastItem.addActionListener(structureListener);
	    listMenu.add(addFirstItem);
	    listMenu.add(addLastItem);
	    listMenu.add(removeFirstItem);
	    listMenu.add(removeLastItem);
	    menuBar.add(listMenu);
	}
	
	/** Method New creates a resest the simulator.
	 * 
	 */
	public void New(){
		component = new StructureSimulatorComponent();
		repaint();
	}
	
	
	/**Class StructurListener calls the corresponding component function
	 * for the various menu items.
	 * @author Lionel Pereira (500510610)
	 *
	 */
	class StructureListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (event.getSource().equals(newItem)){
				New();
			}
			else if (event.getSource().equals(exitItem)){
				System.exit(0);
			}
			else if (event.getSource().equals(addFirstItem)){
				component.addFirst();
			}
			else if (event.getSource().equals(addLastItem)){
				component.addLast();
			}
			else if (event.getSource().equals(removeFirstItem)){
				component.removeFirst();
			}
			else if (event.getSource().equals(removeLastItem)){
				component.removeLast();
			}
			else if (event.getSource().equals(popItem)){
				component.pop();
			}
			else if (event.getSource().equals(pushItem)){
				component.push();
			}
	    }
	}
}

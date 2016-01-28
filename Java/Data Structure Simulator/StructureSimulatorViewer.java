import javax.swing.JFrame;


/** Class StructureSimulatorViewer creates and displays the Simulator.
 * @author Lionel Pereira (500510610)
 *
 */
public class StructureSimulatorViewer {
	
	public static void main(String[] args){
		StructureSimulatorFrame frame = new StructureSimulatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Structure Simulator");
	    frame.setVisible(true);    
	}

}

import javax.swing.JFrame;

/**
 * Class GameViewer creates and displays the game frame.
 * @author Lionel Pereira (500510610)
 *
 */
public class GameViewer{
			
	public static void main(String[] args){
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		;
	}
}

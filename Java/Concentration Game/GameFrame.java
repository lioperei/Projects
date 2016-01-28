import javax.swing.JFrame;

/**
 * Class GameFrame creates the frame for the game.
 * @author Lionel Pereira (500510610)
 *
 */
public class GameFrame extends JFrame{
	protected static final int FRAME_WIDTH = 500;
	protected static final int FRAME_HEIGHT = 600;
	
	/**
	 * Constructs the frame
	 */
	public GameFrame(){
		GameComponent component = new GameComponent();
		this.add(component);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
	}
}

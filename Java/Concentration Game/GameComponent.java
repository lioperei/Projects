import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Class GameComponent contains the logic for the game
 * @author Lionel Pereira (500510610)
 *
 */
public class GameComponent extends JComponent{
	static ArrayList<GameToken> tokens ;
	
	final int NUMBER_TOKENS = 12;
	final int TOKEN_TYPES = 2;
	private static int correct = 0;
	protected static final int TOKEN_WIDTH = 40;
	private static int BOUND_WIDTH = GameFrame.FRAME_WIDTH - 45;
	private static int BOUND_HIEGHT = GameFrame.FRAME_HEIGHT- 125;
	static Random random = new Random();
	private static int score = 0;
	private static Timer timer;
	protected static int DELAY = 3000;
	protected static int time, gameTime = 0;
		
	UserToken user;
	
	/**
	 * Constructor of the GameComponent.
	 */
	public GameComponent(){
		tokens = new ArrayList<GameToken>();
		user = new UserToken(random.nextInt(Pattern.TOTAL_PATTERNS), 5, 5, TOKEN_WIDTH, TOKEN_WIDTH);
		
		// Creates GameTokens specific by NUMBER_TOKENS
		for (int i =0; i < NUMBER_TOKENS; i++){
			GameToken token = generateToken(random.nextInt(TOKEN_TYPES));
			int count = 0;
			while ( count < tokens.size()){
				for (GameToken approvedToken : tokens){
					//Check if token overlaps a previously made token.
					if (token.overlaps(approvedToken)){
						// Make another token
						token = generateToken(random.nextInt(TOKEN_TYPES));
						// Repeat check for overlap with new token.
						count = 0;
						}
					}
					count++;
				}
			tokens.add(token);	
		}
		
		GameListener listener = new GameListener();
		this.addMouseListener(listener);
		TimerListener timeListener = new TimerListener();
		timer = new Timer(10, timeListener);
		timer.start();
	}
		
		class GameListener implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent event) {
				// If timer has surpassed delay.
				if (gameTime > 0 && timer.isRunning()){
					// Left Mouse click, check for score
					if (SwingUtilities.isLeftMouseButton(event)){
						// Move user token.
						user.move(event.getX(), event.getY());
						for (GameToken token: tokens){
							if (token.overlaps(user) && !token.getCorrect()){
									if (token.equals(user)){ 
											token.setColor(Color.GREEN);
											token.setCorrect();
											score += token.getScore();
											correct++;
											break;
									}
									else {
										token.attempt();
										if (token.getScore() == -1){
										score += token.getScore();}
									}
							}
						}	
					}
				// Right mouse click, shift user token pattern type
				if (SwingUtilities.isRightMouseButton(event)){
					user.move(event.getX(), event.getY());
					user.update();
					repaint();
					}
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
			
		}
		
		/**
		 * Class TimerListener is controls time related events of the game.
		 *
		 */
		class TimerListener implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent event) {
				time += 10;
				if (time >= DELAY){gameTime += 10;}
				//Set visibility after each interval.
				for (GameToken token: tokens){
					token.setVisibilityPolicy();
				}
				repaint();
				// Stop timer when all tokens are solved.
				if (correct == NUMBER_TOKENS){
					timer.stop();
					user.setVisible(false);}
				}
			}
		
		/**
		 * Generate tokens EasyToken(0) or HardToken(1) 
		 * @param type: Type of token to generate.
		 * @return: GameToken object specified by type with random x and y 
		 * 			determined by frame width.
		 */
		public GameToken generateToken(int type){
			GameToken token = null;
			if (type == 0){
				token = new EasyToken(random.nextInt(BOUND_WIDTH), random.nextInt(BOUND_HIEGHT)+ TOKEN_WIDTH + 10,
					TOKEN_WIDTH, TOKEN_WIDTH);
			}
			if (type == 1){
				token = new HardToken(random.nextInt(BOUND_WIDTH), random.nextInt(BOUND_HIEGHT)+ TOKEN_WIDTH + 10,
					TOKEN_WIDTH, TOKEN_WIDTH);
			}
			return token;
		}
		
		/**
		 * Paints all tokens as well as score and time to the provided graphic object
		 * @param g: Graphics object to paint to.
		 */
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for (GameToken token : tokens){
					token.draw(g2);}
			user.draw(g2);
			g2.drawString("Score : " + String.valueOf(score), GameFrame.FRAME_WIDTH / 4, 10);
			g2.drawString("Time : " + String.valueOf(gameTime / 1000), 3 * (GameFrame.FRAME_WIDTH / 4), 10);
		}
	}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Class GameToken which contains the 
 * @author Lionel Pereira (500510610)
 *
 */
public class GameToken implements VisibleShape {
	private boolean visible, correct;
	public Rectangle bbox;
	private Pattern pattern;
	private Color color;
	private int attempts;
	
	
	/**GameToken class with random pattern type
	 * @param x: X corrdinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public GameToken(int x, int y, int width, int height) {
		bbox = new Rectangle(x, y, width, height);
		pattern = new Pattern(GameComponent.random.nextInt(3), bbox);
		visible = true;
		color = Color.RED;
		attempts = 2;
		correct = false;
	}

	/** GameToken with a specified pattern type
	 * @param patternType: The type of pattern of this specified GameToken.
	 * @param x: X corrdinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public GameToken(int patternType, int x, int y, int width, int height) {
		bbox = new Rectangle(x, y, width, height);
		pattern = new Pattern(patternType, bbox);
		visible = true;
		color = Color.RED;
		attempts = 0;
		correct = false;
	}

	// Add accessor and mutator methods for instance variables
	/**Returns the visibility of this token.
	 * @return: the visibile value of this token.
	 */
	public boolean isVisible() {return this.visible;}

	/**Sets the visibiliity of this token.
	 * @param visible: the visibile value of this token
	 */
	public void setVisible(boolean visible) {this.visible = visible;}

	/**Returns the bounding box(bbox) of this token.
	 * @return: The rectangle that bounds this token.
	 */
	public Rectangle getBbox() {return bbox;}

	/**Sets the bounding box(bbox) of this token.
	 * @param bbox: The rectangle that will bounds.
	 */
	public void setBbox(Rectangle bbox) {this.bbox = bbox;}

	/**Returns the Pattern of this token.
	 * @return: The Pattern object of this token.
	 */
	public Pattern getPattern() {return pattern;}

	/**Setss the Pattern of this token.
	 * @param pattern: The Pattern object of this token.
	 */
	public void setPattern(Pattern pattern) {this.pattern = pattern;}

	/**Returns the color of this token.
	 * @return: The color type of this token.
	 */
	public Color getColor() {return color;}

	/**Sets the color type of this token
	 * @param color: The color to be set.
	 */
	public void setColor(Color color) {this.color = color;}

	/**Decreases the ammounts of attempts of this token.
	 * 
	 */
	public void attempt() {this.attempts -= 1;}
	

	/**Returns the correct value of this token
	 * @return: The correct value of this token.
	 */
	public boolean getCorrect() {return this.correct;}

	/**Sets the correct value of this token to true.
	 */
	public void setCorrect() {this.correct = true;}

	/**Uses the attempts to calculate the score of this token.
	 * If attempts is greater than 0 return the attempts value
	 * If attempts is negative return -1
	 * @return: The calculated score of this token.
	 */
	public int getScore() {
		if (!this.getCorrect()){
			if (this.attempts >= 0){return this.attempts;}
			else{return -1;}
		}
		else{
			if (this.attempts < 0){ return -1;}
			else {return this.attempts;}
		}
	}

	// end accessor and mutator methods

	/* Checks whether if this GameToken object is equal to another object.
	 * @param: Other object to be compared.
	 * @return: The boolean result of the comparison.
	 */
	public boolean equals(Object other){
		return this.pattern.equals(((GameToken) other).getPattern());
	}

	// Add methods to implement VisibleShape interface
	@Override
	
	/*
	 * Draws the GameToken object)
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(this.getColor());
		if (this.isVisible()) {
			this.pattern.draw(g2);
		}
		g2.draw(bbox);
	}

	@Override
	/*
	 * Sets the Visibility Policy for the game token.
	 */
	public void setVisibilityPolicy() {
	}

	@Override
	/*
	 * Determines if two VisibleShape objects ovarlap one another.
	 * @return: The result of the intersection between the bboxs of both objects.
	 */
	public boolean overlaps(VisibleShape other) {
		return bbox.intersects(((GameToken) other).getBbox());
	}
}

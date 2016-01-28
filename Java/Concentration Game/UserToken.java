import java.awt.Color;


/**
 * Class UsertToken that represents token that a player will use for scoring
 * @author Lionel Pereira (500510610)
 *
 */
public class UserToken extends GameToken {
	
	
	/**
	 * UserToken class with random pattern type
	 * @param x: X corrdinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public UserToken(int x, int y, int width, int height) {
		super(x, y, width, height);
		super.setColor(Color.BLUE);
	}
	
	/**
	 * UserToken with a specified pattern type
	 * @param patternType: The type of pattern of this specified UserToken.
	 * @param x: X corrdinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public UserToken(int patternType, int x, int y, int width, int height) {
		super(patternType, x, y, width, height);
		super.setColor(Color.BLUE);
	}
	
	/**
	 * Creates and sets a new Pattern object for this UseToken.
	 * @param type: The new type of this UserToken.
	 */
	public void setType(int type){
		super.setPattern(new Pattern(type, super.getBbox()));
	}
	
	/**
	 * Updates the Pattern of this UserToken so player can
	 * shuffle through available patterns.
	 */
	public void update(){super.getPattern().update();}
	
	/**
	 * Moves this UserToken to new coordinates.
	 * @param x: The new X coordinate of this UserToken
	 * @param y: The new Y coordinate of this UserToken
	 */
	public void move(int x, int y){super.getBbox().setLocation(x, y);}
	
}

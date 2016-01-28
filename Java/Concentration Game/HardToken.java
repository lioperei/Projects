
/**
 * Class HardToken that is less visible than EasyToken
 * @author Lionel Pereira (500510610)
 *
 */
public class HardToken extends GameToken{
	
	/**
	 * HardToken class with random pattern type
	 * @param x: X coordinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public HardToken(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	/**
	 * HardToken with a specified pattern type
	 * @param patternType: The type of pattern of this specified HardToken.
	 * @param x: X coordinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public HardToken(int patternType, int x, int y, int width, int height) {
		super(patternType, x, y, width, height);
	}
	
	/*
	 * Sets the visibility of an HardToken
	 * The token is switches visibility for 0.5 seconds per 2 seconds.
	 */
	public void setVisibilityPolicy(){
		if (!super.getCorrect()	&& GameComponent.gameTime > 0
				&&  GameComponent.gameTime % 2000 >= 200 
				&&  GameComponent.gameTime % 2000 <=  1700){
			super.setVisible(false);		
		}
		else{super.setVisible(true);}
	}
}
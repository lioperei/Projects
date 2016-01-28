
/**
 * Class EasyToken that is visible for more time then HardToken
 * @author Lionel Pereira (500510610)
 *
 */
public class EasyToken extends GameToken{
	
	/**
	 * EasyToken class with random pattern type
	 * @param x: X coordinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public EasyToken(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	/**
	 * EasyToken with a specified pattern type
	 * @param patternType: The type of pattern of this specified EasyToken.
	 * @param x: X coordinate of the token
	 * @param y: Y coordinate of the token
	 * @param width: Width of the bounding rectangle
	 * @param height: Height of the bounding rectangle
	 */
	public EasyToken(int patternType, int x, int y, int width, int height) {
		super(patternType, x, y, width, height);
	}
	
	/*
	 * Sets the visibility of an EasyToken
	 * The token is switches visibility after every second.
	 */
	public void setVisibilityPolicy(){
		if (!super.getCorrect() && GameComponent.gameTime > 0 &&
				GameComponent.gameTime % 2000 >= 500 && 
				GameComponent.gameTime % 2000 <= 1500){
			super.setVisible(false);		
		}
		else {
			super.setVisible(true);}
	}
}

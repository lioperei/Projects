import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.awt.geom.Line2D;

import java.awt.Color;
import java.awt.geom.* ;
import java.awt.* ;

/**
 * Class pattern that is responsible for drawing and assigning a 
 * pattern for each GameToken
 * @author Lionel Pereira (500510610)
 */
public class Pattern 
{
  // A2 bullshit
      private static final double WIDTH = 35 ;
    private static final double UNIT = WIDTH / 5 ;
    private static final double LENGTH_FACTOR = 14 ; // length is 14U
    private static final double HEIGHT_FACTOR = 5 ; // height is 5U
    private static final double U_3 = 0.3 * UNIT ; 
    private static final double U2_5 = 2.5 * UNIT ; 
    private static final double U3 = 3 * UNIT ; 
    private static final double U4 = 4 * UNIT ; 
    private static final double U5 = 5 * UNIT ; 
    private static final double U10 = 10 * UNIT ; 
    private static final double U10_7 = 10.7 * UNIT ; 
    private static final double U12 = 12 * UNIT ; 
    private static final double U13 = 13 * UNIT ; 
    private static final double U14 = 14 * UNIT ; 
	
	
  // Some example patterns 
  public static final int CROSS      = 0;
  public static final int CIRCLEPLUS = 1;
  public static final int SQUARE_X   = 2;
  public static final int TOTAL_PATTERNS = 3;

  int type;
  Rectangle bbox;
	
  // Create random pattern type	
  /**
   * Default constructor of Pattern that assigns a random pattern.
 * @param bbox: The bounding rectangle of the pattern.
 */
public Pattern(Rectangle bbox)
  {
    Random random = new Random();
	this.type = random.nextInt(TOTAL_PATTERNS);
    this.bbox = bbox;
  }
  
  /*
   * Constructor with a specified pattern.
   * @param type: The type this Pattern will have.
   * @param bbox: The bounding rectangle of the pattern.
   */
  public Pattern(int type, Rectangle bbox)
  {
    this.type = type;
    this.bbox = bbox;
  }

  /*
   * Gets the type of this Pattern object
   * @return The type of this pattern
   */
  public int getType(){return this.type;}
  
  /*
   * Sets the type of this Pattern.
   * @param type: The new type of this Pattern.
   */
  public void setType(int type){this.type = type;}
  
  /*
  // Updates the pattern objects to the next type.
  */
  public void update(){
    switch(this.type){
    	case CROSS: setType(CIRCLEPLUS);
    			break;
    	case CIRCLEPLUS: setType(SQUARE_X);
    			break;
    	case SQUARE_X: setType(CROSS);
    			break;
    }		
  }
  
  /*
   * Check if another object is equal to this Pattern.
   * @param other: The other object to be compared.
   */
  public boolean equals(Object other){
    return this.type == ((Pattern) other).getType();
  }
  
  /*
   * Draws the pattern depending on type.
   * @param g2: The graphics component which the pattern is drawn on.
   */
  /*
  public void draw(Graphics2D g2)
  {
	if (type == CROSS){
		int width = GameComponent.TOKEN_WIDTH / 4;
		int center = 3 * (GameComponent.TOKEN_WIDTH / 8);
		Rectangle cross1 = new Rectangle((int) bbox.getX() + center,
				(int) bbox.getY(), width, (int) bbox.getHeight());
		Rectangle cross2 = new Rectangle((int) bbox.getX(), (int) bbox.getY() + center,
				(int) bbox.getWidth(), width);
		g2.fill(cross1);
		g2.fill(cross2);
		g2.draw(cross1);
		g2.draw(cross2);
	}
	
	if (type == CIRCLEPLUS){ 
		Ellipse2D.Double circle = new Ellipse2D.Double(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		Line2D.Double line1 = new Line2D.Double(bbox.getX(), bbox.getCenterY(),
				bbox.getX() + bbox.getWidth(), bbox.getCenterY());
		Line2D.Double line2 = new Line2D.Double(bbox.getCenterX(), bbox.getY(),
				bbox.getCenterX(), bbox.getY() + bbox.getHeight());
		g2.draw(circle);
		g2.draw(line1);
		g2.draw(line2);
		
	}
	
	if (type == SQUARE_X){
		Line2D.Double line1 = new Line2D.Double(bbox.getX(), bbox.getY(),
				bbox.getX() + bbox.getWidth(), bbox.getY() + bbox.getHeight());
		Line2D.Double line2 = new Line2D.Double(bbox.getX(), bbox.getY() + bbox.getHeight(),
				bbox.getX() + bbox.getWidth(), bbox.getY());
		g2.draw(line1);
		g2.draw(line2);
	}
  }
  */
  public void draw(Graphics2D g2)
    {
	// decide whether to implement getX() and getY() in this
     // class or in superclass
	int x1 = 50 ;
	int y1 = 50 ;
	Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
							 U3, U3 ) ;
	g2.setColor(Color.blue) ;
	g2.fill(hood) ;

	Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
							 y1,
							 U10, U4) ;
	g2.setColor(Color.blue) ;
	g2.fill(body) ;

	Line2D.Double hitch = new Line2D.Double(x1 + U13,
						y1 + U2_5,
						x1 + U14, 
						y1 + U2_5) ;
	g2.setColor(Color.black) ;
	g2.draw(hitch) ;

	Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel1) ;

	Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel2) ;

	Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT, 
						       y1 + 4 * UNIT, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel3) ;

	Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel4) ;

	Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel5) ;
	
	Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT, 
		       y1 + U4, 
			 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
	g2.fill(wheel6) ;
	
	
    }
}

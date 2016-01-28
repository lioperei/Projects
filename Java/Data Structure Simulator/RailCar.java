import java.awt.* ;
import java.awt.geom.* ;



/**Class RailCar is a subclass of Vehicle.
 * @author Lionel Pereira (500510610)
 *
 */
public class RailCar extends Vehicle
{
 
	public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;
    
    private int number;
    
    /**Constructor of RailCar
     * @param x: The X coordinate of this RailCar.
     * @param y: The Y coordinate of this RailCar.
     * @param number: THe number of this RailCar.
     */
    public RailCar(int x, int y, int number) {
		super(x, y, 65, 20);
		this.number = number;
		
	}
    
    /**Method getNumber returns the number of this Railcar.
     * @return: THe number of this RailCar.
     */
    public int getNumber(){
    	return this.number;
    }
    
    /**
    Draw the rail car
    @param g2 the graphics context
  */
 public void draw(Graphics2D g2)
 {
	// think about whether getX() and getY() should be inherited
  // or defined in this class
	
	int yTop = getY() ;
	
	Rectangle2D.Double body 
	    = new Rectangle2D.Double(getX(), yTop + UNIT, U6, UNIT);      
	Ellipse2D.Double frontTire 
	    = new Ellipse2D.Double(getX() + UNIT, yTop + U2, UNIT, UNIT);
	Ellipse2D.Double rearTire
	    = new Ellipse2D.Double(getX() + U4, yTop + U2, UNIT, UNIT);
	
	// the bottom of the front windshield
	Point2D.Double r1 
	    = new Point2D.Double(getX() + UNIT, yTop + UNIT);
	// the front of the roof
	Point2D.Double r2 
	    = new Point2D.Double(getX() + U2, yTop);
	// the rear of the roof
	Point2D.Double r3 
	    = new Point2D.Double(getX() + U4, yTop);
	// the bottom of the rear windshield
	Point2D.Double r4 
	    = new Point2D.Double(getX() + U5, yTop + UNIT);

	// the right end of the hitch
	Point2D.Double r5 
	    = new Point2D.Double(getX() + U6, yTop + U15);
	// the left end of the hitch
	Point2D.Double r6 
	    = new Point2D.Double(getX() + U6 + U05, yTop + U15);
	
	Line2D.Double frontWindshield 
	    = new Line2D.Double(r1, r2);
	Line2D.Double roofTop 
	    = new Line2D.Double(r2, r3);
	Line2D.Double rearWindshield
	    = new Line2D.Double(r3, r4);
	Line2D.Double hitch
	    = new Line2D.Double(r5, r6);
	
	if (super.getSelected() == true){
		g2.setColor(Color.RED);
	}
	else{
		g2.setColor(Color.BLACK);
	}

	g2.draw(body);
	g2.draw(hitch);
	g2.draw(frontTire);
	g2.draw(rearTire);
	g2.draw(body) ;
	
	// think about whether getNumber() should be inherited or
	// defined in this class
	g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;
	
	if (super.hasTrailer()){
		this.trailer.draw(g2);
	}
	if (super.hasTop()){
		super.getTop().draw(g2);
	}
		
 }

    
}
    
    
    
    
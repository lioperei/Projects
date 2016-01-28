import java.awt.Point;
/**
 * Matcher class that takes two images, a template image and a search image
 * and finds the location and outline the template image onto the search image.
 * 
 * @author: Lionel Pereira 
 * @student number: 500510610
 */
public class Matcher
{
    /** Method sadMatch that computes the SAD matches by translating 
	 *  the template image along the search image between two images and outputs
	 *  a 2d int array of the SAD matches.
     *  
	 *  @param: template: The template image of type Picture
	 *  @param: search: The search image of type Picture
	 *
	 *  @return: sadMatches: A 2d int array of the SAD matches 
	 *           between both images 
     */
    public static int[][] sadMatch(Picture template, Picture search){
         // Set 2d array for the template and search images
         int[][] templateArray = new int[template.getHeight()][template.getWidth()];
         int[][] searchArray = new int[search.getHeight()][search.getWidth()];
         // Get pixel array for template and search images
         Pixel[] templatePixels = template.getPixels();
         Pixel[] searchPixels = search.getPixels();
         
         int widthSearch = search.getWidth();
         int heightSearch = search.getHeight();
         
         int widthTemplate = template.getWidth();
         int heightTemplate = template.getHeight();
         
         // Get the red values of each pixel and move that into the correct position
         for (Pixel pT : templatePixels){templateArray[pT.getY()][pT.getX()] = pT.getRed();}
         for (Pixel pS : searchPixels){ searchArray[pS.getY()][pS.getX()] = pS.getRed();}
         
         int[][] sadMatches = new int[heightSearch - heightTemplate][widthSearch - widthTemplate];
                  
         for (int i = 0; i < sadMatches.length; i++){//go from top to bottom
             for (int j = 0; j < sadMatches[0].length; j++){// go left to right at each height
                 int [][] sadMatchLocal = new int[heightTemplate][widthTemplate];// hold the sads in this area
                 for (int k = 0; k < heightTemplate; k++){ // find the differences in this area
                     for (int l = 0; l < widthTemplate; l++){
                         sadMatchLocal[k][l] = Math.abs(templateArray[k][l] - searchArray[i+k][j+l]);}} //compute and set the differences
                 int sad = 0;
                 for (int y = 0; y < sadMatchLocal.length; y++){
                     for (int z = 0; z < sadMatchLocal[0].length; z++){
                         sad += sadMatchLocal[y][z];} // find the sad
                        }
                 sadMatches[i][j] = sad; // set sad
                    }
                }
         return sadMatches;
    }
    
	/** Method find2DMin that takes a 2d int array of SAD matches and
	  * returns a Point were the lowest SAD match occurs.
	  *
	  * @param: sadMatches: A 2d array of SAD matches.
	  * 
	  *@ return: minimum: The minimum point of the lowest SAD match.
	**/
    public static Point find2DMin(int[][] sadMatches){
        int minX = 0;
        int minY = 0;
        int min = 1000000000;
        
        for (int i = 0; i < sadMatches.length; i++){
            for (int j = 0; j < sadMatches[0].length; j++){
                if (sadMatches[i][j] < min){
                    min = sadMatches[i][j];
                    minX = j;
                    minY = i;
                }
            }
        }
        Point minimum = new Point(minX, minY);
        return minimum;
    }
    /** Method displayMatch that take a search image a template image
    	and a point where the template matches the search image and writes 
		and displays the search image with a box drawn the size of the template
		image at the point given.
		
		@param: search: The search image of type Picture
	  * @param: template: The template image of type Picture
	    @param: minMatch: The point where the template appears in the search
		        image, of type Point.
	**/
    public static void displayMatch(Picture search,Picture template, 
                                    Point minMatch){
        Turtle turtle = new Turtle((int)minMatch.getX(), (int)minMatch.getY(),
                                    search);
        turtle.setPenWidth(3);
        turtle.turnRight();
        turtle.forward(template.getWidth());
        turtle.turnRight();
        turtle.forward(template.getHeight());
        turtle.turnRight();
        turtle.forward(template.getWidth());
        turtle.turnRight();
        turtle.forward(template.getHeight());
        turtle.turnRight();
        search.show();
        search.write("Waldoboxed.png");
		}
}

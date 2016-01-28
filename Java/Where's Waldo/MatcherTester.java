import java.awt.Point;
/**
 * MatcherTester is a class that tests the methods of the class Matcher
 * by finding waldo using "waldo.png" and finding that image within
 * "scene.png".
 * author: Lionel Pereira 
 * Student number: 500510610
 */
public class MatcherTester
{
    

    /**
     * Main class that tests the methods of the Matcher class.
     */
    public static void main(String[] args){
        Picture scene = new Picture("scene.png");
        Picture template = new Picture("waldo.png");
        
        int[][] sads = Matcher.sadMatch(template, scene);
        Point min = Matcher.find2DMin(sads);
        Matcher.displayMatch(scene,template, min);
    }
}

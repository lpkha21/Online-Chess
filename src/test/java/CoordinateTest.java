import junit.framework.TestCase;
import javaClasses.*;
public class CoordinateTest extends TestCase {

    public void testEquals(){
        Coordinate c1 = new Coordinate(0,5);
        Coordinate c2 = new Coordinate(5,5);
        Coordinate c3 = new Coordinate(0,5);

        assertTrue(c1.equals(0,5));
        assertFalse(c1.equals(c2));
        assertTrue(c2.equals(5,5));
        assertFalse(c2.equals(c3));
    }
}

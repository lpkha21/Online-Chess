import junit.framework.TestCase;
import javaClasses.*;

import java.util.ArrayList;

public class QueenTest extends TestCase {

    protected void setUp(){

    }

    public void test1(){

    }

    public void testCheckPath1(){
        Board b = new Board(false);
        b.setPiece(new Queen(0,0,b,pieceEnum.WHITE));
        b.setPiece(new King(1,0,b,pieceEnum.WHITE));
        b.setPiece(new King(0,7,b,pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(0,0), new Coordinate(0,1), pieceEnum.WHITE));
        assertTrue(b.isCheck(pieceEnum.BLACK));

        ArrayList<Coordinate> a = b.getPiece(0,1).getCheckPath(new Coordinate(0,7));
        assertFalse(a.isEmpty());
        assertEquals(5, a.size());
        assertTrue(a.get(0).equals(new Coordinate(0,2)));
        assertTrue(a.get(1).equals(new Coordinate(0,3)));
        assertTrue(a.get(2).equals(new Coordinate(0,4)));
        assertTrue(a.get(3).equals(new Coordinate(0,5)));
        assertTrue(a.get(4).equals(new Coordinate(0,6)));
    }

    public void testCheckPath2(){
        Board b = new Board(false);
        b.setPiece(new Queen(0,0,b,pieceEnum.WHITE));
        b.setPiece(new King(0,1,b,pieceEnum.WHITE));
        b.setPiece(new King(7,7,b,pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(0,0), new Coordinate(1,1), pieceEnum.WHITE));
        assertTrue(b.isCheck(pieceEnum.BLACK));

        ArrayList<Coordinate> a = b.getPiece(1,1).getCheckPath(new Coordinate(7,7));
        assertFalse(a.isEmpty());
        assertEquals(5, a.size());
        assertTrue(a.get(0).equals(new Coordinate(2,2)));
        assertTrue(a.get(1).equals(new Coordinate(3,3)));
        assertTrue(a.get(2).equals(new Coordinate(4,4)));
        assertTrue(a.get(3).equals(new Coordinate(5,5)));
        assertTrue(a.get(4).equals(new Coordinate(6,6)));
    }
}

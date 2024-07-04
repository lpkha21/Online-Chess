import junit.framework.TestCase;
import javaClasses.*;

public class KingTest extends TestCase {

    private Board emptyBoard;
    private Board board;
    private King loneKing;
    private int lonei, lonej;


    private Board castleBoard;
    private King castleKing;
    private Rook castleRook1, castleRook2;


    protected void setUp(){
        setupEmpty();
        setupCastle();

        board = new Board(false);
        placePieces();
    }

    private void setupEmpty(){
        emptyBoard = new Board(false);
        loneKing = new King(4,4,emptyBoard,pieceEnum.WHITE);
        lonei = loneKing.getCoordinate().i;
        lonej = loneKing.getCoordinate().j;
    }

    private void setupCastle() {
        castleBoard = new Board(false);
        castleKing = new King(0, 4, castleBoard, pieceEnum.BLACK);
        castleRook1 = new Rook(0, 0, castleBoard, pieceEnum.BLACK);
        castleRook2 = new Rook(0, 7, castleBoard, pieceEnum.BLACK);
    }
    private void placePieces() {
    }

    public void testAtributes(){
        assertEquals(loneKing.color(), pieceEnum.WHITE);
        assertEquals(loneKing.getType(), pieceEnum.KING);
        assertTrue(loneKing.getCoordinate().equals(new Coordinate(4,4)));
    }

    public void testCanMove1(){ // simple moves
        assertFalse(loneKing.canMove(lonei, lonej));

        // around circle
        assertTrue(loneKing.canMove(lonei,lonej+1));
        assertTrue(loneKing.canMove(lonei,lonej-1));
        assertTrue(loneKing.canMove(lonei+1,lonej));
        assertTrue(loneKing.canMove(lonei-1,lonej));
        assertTrue(loneKing.canMove(lonei+1,lonej+1));
        assertTrue(loneKing.canMove(lonei+1,lonej-1));
        assertTrue(loneKing.canMove(lonei-1,lonej+1));
        assertTrue(loneKing.canMove(lonei-1,lonej+1));

    }

    public void testCastle1(){
        assertTrue(castleKing.canMove(0,6));

        //assertTrue(castleKing.canMove(6,0));
        //assertTrue(castleKing.canMove(2,0));
    }

}

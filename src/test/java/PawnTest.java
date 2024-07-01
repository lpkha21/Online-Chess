import javaClasses.*;
import junit.framework.TestCase;

public class PawnTest extends TestCase {

    private Board emptyBoard;
    private Board board;
    private Pawn lonePawn;

    int lonei,lonej;

    protected void setUp(){
        emptyBoard = new Board(false);
        lonePawn = new Pawn(6,1,emptyBoard, pieceEnum.WHITE);
        lonei = lonePawn.getCoordinate().i;
        lonej = lonePawn.getCoordinate().j;
        emptyBoard.setPiece(lonePawn);
    }

    public void test1(){

    }

    public void testCanMove1(){
        assertTrue(lonePawn.canMove(lonei-1,lonej));
        assertTrue(lonePawn.canMove(lonei-1,lonej-1));

        assertFalse(lonePawn.canMove(lonei+1,lonej));
        assertFalse(lonePawn.canMove(lonei-1,lonej));

        assertFalse(lonePawn.canMove(lonei,lonej));
    }
}

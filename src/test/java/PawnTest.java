import javaClasses.*;
import junit.framework.TestCase;

public class PawnTest extends TestCase {

    private Board emptyBoard;
    private Board board;
    private Pawn lonePawnB;
    private Pawn lonePawnW;

    int loneiB,lonejB;
    int loneiW,lonejW;


    protected void setUp(){
        setLonePawns();
        setBoard();
    }

    private void setBoard() {
        board = new Board(false);

        board.setPiece(1,1, new Pawn(1,1, board, pieceEnum.BLACK));
        board.setPiece(4,4, new Pawn(5,5, board, pieceEnum.BLACK));
        ((Pawn)board.getPiece(4,4)).updateCoordinate(5,5);


    }

    private void setLonePawns() {
        emptyBoard = new Board(false);

        lonePawnW = new Pawn(6,1,emptyBoard, pieceEnum.WHITE);
        loneiW = lonePawnW.getCoordinate().i;
        lonejW = lonePawnW.getCoordinate().j;
        emptyBoard.setPiece(lonePawnW);

        lonePawnB = new Pawn(1,1,emptyBoard, pieceEnum.BLACK);
        loneiB = lonePawnB.getCoordinate().i;
        lonejB = lonePawnB.getCoordinate().j;
        emptyBoard.setPiece(lonePawnB);
    }


    public void testCanMove1(){ // empty board
        // Move to same place
//        assertFalse(lonePawnB.canMove(loneiB,lonejB));
//        assertFalse(lonePawnW.canMove(loneiW,lonejW));

        // make first move forward with 1 or 2 moves
        assertTrue(lonePawnB.canMove(loneiB+1,lonejB));
        assertTrue(lonePawnB.canMove(loneiB+2,lonejB));
        assertFalse(lonePawnB.canMove(loneiB-1,lonejB));
        assertFalse(lonePawnB.canMove(loneiB-2,lonejB));

        assertTrue(lonePawnW.canMove(loneiW-1,lonejW));
        assertTrue(lonePawnW.canMove(loneiW-2,lonejW));
        assertFalse(lonePawnW.canMove(loneiW+1,lonejW));
        assertFalse(lonePawnW.canMove(loneiW+2,lonejW));
    }

    public void testCanMove2(){ // empty board
        // move horizontally without killing
        assertFalse(lonePawnB.canMove(loneiB+1,lonejB+1));
        assertFalse(lonePawnB.canMove(loneiB+1,lonejB-1));
        assertFalse(lonePawnW.canMove(loneiW-1,lonejW+1));
        assertFalse(lonePawnW.canMove(loneiW-1,lonejW-1));

        assertFalse(lonePawnB.canMove(loneiB-1,lonejB+1));
        assertFalse(lonePawnB.canMove(loneiB-1,lonejB-1));
        assertFalse(lonePawnW.canMove(loneiW+1,lonejW+1));
        assertFalse(lonePawnW.canMove(loneiW+1,lonejW-1));
    }

    public void testCanMove3(){ // random movement
        assertFalse(lonePawnB.canMove(3, 7));
        assertFalse(lonePawnB.canMove(1,4));
        assertFalse(lonePawnB.canMove(0, 7));
        assertFalse(lonePawnB.canMove(2, 3));


        assertFalse(lonePawnW.canMove(1, 7));
        assertFalse(lonePawnW.canMove(0,7));
        assertFalse(lonePawnW.canMove(6,2));
        assertFalse(lonePawnW.canMove(6,6));
    }

}

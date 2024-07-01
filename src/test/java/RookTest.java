import junit.framework.TestCase;
import javaClasses.*;
public class RookTest extends TestCase {

    private Board emptyBoard;
    private Board board;
    private Rook loneRook;
    int lonei,lonej;

    private Rook rookW, rookB1, rookB2;
    protected void setUp(){
        emptyBoard = new Board(false);
        loneRook = new Rook(6,3,emptyBoard, pieceEnum.BLACK);
        lonei = loneRook.getCoordinate().i;
        lonej = loneRook.getCoordinate().j;
        emptyBoard.setPiece(loneRook);

        board = new Board(false);
        placePieces(board);
    }

    private void placePieces(Board board) {

        board.setPiece(1, 0,new Pawn(new Coordinate(1,0), board, pieceEnum.WHITE));
        board.setPiece(1, 1,new Pawn(new Coordinate(1,1), board, pieceEnum.WHITE));
        board.setPiece(1, 2,new Pawn(new Coordinate(1,2), board, pieceEnum.WHITE));

        board.setPiece(3, 1,new Knight(new Coordinate(3,1), board, pieceEnum.BLACK));
        board.setPiece(4, 5,new Bishop(new Coordinate(4,5), board, pieceEnum.BLACK));

        board.setPiece(4, 4,new Queen(new Coordinate(4,4), board, pieceEnum.BLACK));
        board.setPiece(0, 4,new Queen(new Coordinate(0,4), board, pieceEnum.WHITE));

        board.setPiece(2, 5,new Bishop(2,5, board, pieceEnum.WHITE));

        board.setPiece(3, 6,new Pawn(new Coordinate(3,6), board, pieceEnum.WHITE));
        board.setPiece(4, 7,new Rook(new Coordinate(4,7), board, pieceEnum.WHITE));
        rookW = (Rook) board.getPiece(4,7);

        board.setPiece(6,3,new Rook(6,3,board,pieceEnum.BLACK));
        rookB1 = (Rook) board.getPiece(6,3);

        board.setPiece(6,1, new Pawn(6,1,board,pieceEnum.WHITE));
        board.setPiece(6,1, new Pawn(6,7,board,pieceEnum.WHITE));

        board.setPiece(6,6, new Rook(6,6,board,pieceEnum.BLACK));
        rookB2 = (Rook) board.getPiece(6,6);

    }



    public void testAttributes(){
        assertEquals(loneRook.color(), pieceEnum.BLACK);
        assertEquals(loneRook.getType(), pieceEnum.ROOK);
        assertTrue(loneRook.getCoordinate().equals(new Coordinate(6,3)));
    }

    // Rook on empty board
    public void testCanMove1(){
        assertTrue(loneRook.canMove(lonei+1,lonej));
        assertTrue(loneRook.canMove(lonei-1,lonej));
        assertTrue(loneRook.canMove(lonei,lonej+1));
        assertTrue(loneRook.canMove(lonei,lonej-1));

        assertFalse(loneRook.canMove(lonei+1,lonej+1));
        assertFalse(loneRook.canMove(lonei-1,lonej-1));
        assertFalse(loneRook.canMove(lonei-1,lonej+1));
        assertFalse(loneRook.canMove(lonei+1,lonej-1));

        assertFalse(loneRook.canMove(lonei,lonej));
    }

    public void testCanMove2(){
        assertTrue(rookB1.canMove(6,1));
    }

    public void testBoardSetup(){

    }
}

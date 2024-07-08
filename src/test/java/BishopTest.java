import javaClasses.*;
import junit.framework.TestCase;

import java.util.ArrayList;

public class BishopTest extends TestCase {

    private Board emptyBoard;
    private Board board;
    private Bishop loneBishop;
    private Bishop bishopW, bishopB;

    private int x,y;

    protected void setUp(){
        emptyBoard = new Board(false);
        loneBishop = new Bishop(new Coordinate(4,5), emptyBoard, pieceEnum.WHITE);
        x = loneBishop.getCoordinate().i;
        y = loneBishop.getCoordinate().j;
        emptyBoard.setPiece(loneBishop);

        board = new Board(false);
        placePieces(board);
    }

    private void placePieces(Board board) {
        board.setPiece(new King(7,3, board, pieceEnum.BLACK));
        board.setPiece(new King(0,0, board, pieceEnum.WHITE));

        board.setPiece(1, 0,new Pawn(new Coordinate(1,0), board, pieceEnum.WHITE));
        board.setPiece(1, 1,new Pawn(new Coordinate(1,1), board, pieceEnum.WHITE));
        board.setPiece(1, 2,new Pawn(new Coordinate(1,2), board, pieceEnum.WHITE));

        board.setPiece(3, 1,new Knight(new Coordinate(3,1), board, pieceEnum.BLACK));
        board.setPiece(4, 5,new Bishop(new Coordinate(4,5), board, pieceEnum.BLACK));
        bishopB = (Bishop) board.getPiece(4,5);

        board.setPiece(4, 4,new Queen(new Coordinate(4,4), board, pieceEnum.BLACK));
        board.setPiece(0, 4,new Queen(new Coordinate(0,4), board, pieceEnum.WHITE));

        board.setPiece(2, 5,new Bishop(2,5, board, pieceEnum.WHITE));
        bishopW = (Bishop) board.getPiece(2,5);

        board.setPiece(3, 6,new Pawn(new Coordinate(3,6), board, pieceEnum.WHITE));
        board.setPiece(4, 7,new Rook(new Coordinate(4,7), board, pieceEnum.WHITE));
        board.setPiece(6,3,new Rook(6,3,board,pieceEnum.BLACK));
    }

    public void testAttributes(){
        assertEquals(loneBishop.color(), pieceEnum.WHITE);
        assertEquals(loneBishop.getType(), pieceEnum.BISHOP);
        assertTrue(loneBishop.getCoordinate().equals(new Coordinate(4,5)));
    }

    public void testCoordinates(){
        Board brd = new Board(false);
        Bishop b = new Bishop(new Coordinate(0,0), brd, pieceEnum.WHITE);

        b.updateCoordinate(1, 2);
        assertTrue(b.getCoordinate().equals(new Coordinate(1,2)));

        b.updateCoordinate(new Coordinate(8, 6));
        assertTrue(b.getCoordinate().equals(new Coordinate(8,6)));
    }

    // Piece on empty Board
    public void testCanMove1(){
        assertTrue(loneBishop.canMove(x+1,y+1));
        assertTrue(loneBishop.canMove(x-1,y-1));
        assertTrue(loneBishop.canMove(x-1,y+1));
        assertTrue(loneBishop.canMove(x+1,y-1));

        assertFalse(loneBishop.canMove(x+1,y));
        assertFalse(loneBishop.canMove(x-1,y));
        assertFalse(loneBishop.canMove(x,y+1));
        assertFalse(loneBishop.canMove(x,y-1));

        assertFalse(loneBishop.canMove(x,y));
    }

    // Piece on empty board
    public void testCanMove2(){
        assertTrue(loneBishop.canMove( 5,6));
        assertTrue(loneBishop.canMove(6,7));
        assertTrue(loneBishop.canMove(7,2));
        assertTrue(loneBishop.canMove(2,7));
        assertTrue(loneBishop.canMove(1,2));

        assertFalse(loneBishop.canMove(7,7));
        assertFalse(loneBishop.canMove(4,0));
        assertFalse(loneBishop.canMove(7,0));
        assertFalse(loneBishop.canMove(6,6));
        assertFalse(loneBishop.canMove(3,2));
    }

    // Piece on board during game
    public void testCanMove3(){
        assertFalse(bishopB.canMove(bishopB.getCoordinate().i,bishopB.getCoordinate().j));

        // Kill pawns
        assertTrue(bishopB.canMove(board.getPiece(1,2).getCoordinate().i, board.getPiece(1,2).getCoordinate().j ));
        assertTrue(bishopB.canMove(3,6));

        // Moving to Blocked path
        assertFalse(bishopB.canMove(2,7));
        assertFalse(bishopB.canMove(board.getPiece(1,2).getCoordinate().i-1, board.getPiece(1,2).getCoordinate().j-1));


        // Cannot move to same colored pieces
        assertFalse(bishopB.canMove(6,3));
        assertFalse(bishopW.canMove(board.getPiece(3,6).getCoordinate().i, board.getPiece(3,6).getCoordinate().j));
        assertFalse(bishopW.canMove(4,7));
    }

    public void testCheckPath(){
        Board b = new Board(false);
        b.setPiece(new Bishop(0,0,b,pieceEnum.WHITE));
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

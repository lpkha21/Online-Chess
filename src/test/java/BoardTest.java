import junit.framework.TestCase;
import javaClasses.*;


public class BoardTest extends TestCase {

    private Board startBoard;
    private Board board1;

    protected void setUp() {
        startBoard = new Board(true);
        board1 = new Board(false);
        setupBoard1(board1);
    }

    private void setupBoard1(Board board1) {
        // set pawns
        board1.setPiece(1, 0, new Pawn(new Coordinate(1, 0), board1, pieceEnum.WHITE));
        board1.setPiece(1, 1, new Pawn(new Coordinate(1, 1), board1, pieceEnum.WHITE));
        board1.setPiece(1, 2, new Pawn(new Coordinate(1, 2), board1, pieceEnum.WHITE));

        Knight knight = new Knight(new Coordinate(3, 1), board1, pieceEnum.BLACK);
        Bishop bishop = new Bishop(new Coordinate(4, 5), board1, pieceEnum.BLACK);
        board1.setPiece(3, 1, knight);
        board1.setPiece(4, 5, bishop);
    }

    public void testBoardInitialization() {

        // Test black pawns
        for (int i = 0; i < startBoard.SIZE; i++) {
            assertTrue(startBoard.getPiece(1, i) instanceof Pawn);
            assertEquals(pieceEnum.BLACK, startBoard.getPiece(1, i).color());
        }

        // Test white pawns
        for (int i = 0; i < startBoard.SIZE; i++) {
            assertTrue(startBoard.getPiece(6, i) instanceof Pawn);
            assertEquals(pieceEnum.WHITE, startBoard.getPiece(6, i).color());
        }

        // Test black rooks
        assertTrue(startBoard.getPiece(0, 0) instanceof Rook);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 0).color());
        assertTrue(startBoard.getPiece(0, 7) instanceof Rook);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 7).color());

        // Test white rooks
        assertTrue(startBoard.getPiece(7, 0) instanceof Rook);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 0).color());
        assertTrue(startBoard.getPiece(7, 7) instanceof Rook);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 7).color());

        // Test black knights
        assertTrue(startBoard.getPiece(0, 1) instanceof Knight);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 1).color());
        assertTrue(startBoard.getPiece(0, 6) instanceof Knight);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 6).color());

        // Test white knights
        assertTrue(startBoard.getPiece(7, 1) instanceof Knight);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 1).color());
        assertTrue(startBoard.getPiece(7, 6) instanceof Knight);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 6).color());

        // Test black bishops
        assertTrue(startBoard.getPiece(0, 2) instanceof Bishop);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 2).color());
        assertTrue(startBoard.getPiece(0, 5) instanceof Bishop);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 5).color());

        // Test white bishops
        assertTrue(startBoard.getPiece(7, 2) instanceof Bishop);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 2).color());
        assertTrue(startBoard.getPiece(7, 5) instanceof Bishop);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 5).color());

        // Test black king and queen
        assertTrue(startBoard.getPiece(0, 4) instanceof King);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 4).color());
        assertTrue(startBoard.getPiece(0, 3) instanceof Queen);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 3).color());

        // Test white king and queen
        assertTrue(startBoard.getPiece(7, 4) instanceof King);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 4).color());
        assertTrue(startBoard.getPiece(7, 3) instanceof Queen);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 3).color());


    }

    public void testIsEmpty1() {
        for (int i = 2; i < startBoard.SIZE - 2; i++)
            for (int j = 0; j < startBoard.SIZE; j++)
                assertTrue(startBoard.isEmpty(i, j));
    }

    public void testIsEmpty2() {
        assertFalse(board1.isEmpty(1, 0));
        assertTrue(board1.isEmpty(0, 0));
        assertFalse(board1.isEmpty(1, 1));
        assertTrue(board1.isEmpty(2, 1));
        assertFalse(board1.isEmpty(3, 1));
        assertTrue(board1.isEmpty(4, 4));
        assertFalse(board1.isEmpty(4, 5));

    }

    public void testGetPiece() {
        for (int i = 2; i < startBoard.SIZE - 2; i++)
            for (int j = 0; j < startBoard.SIZE; j++)
                assertNull(startBoard.getPiece(i, j));
    }

    public void testGetPieceAndSetPiece() {
        assertTrue(board1.getPiece(1, 0) instanceof Pawn);
        assertEquals(pieceEnum.WHITE, board1.getPiece(1, 0).color());

        assertTrue(board1.getPiece(1, 1) instanceof Pawn);
        assertEquals(pieceEnum.WHITE, board1.getPiece(1, 1).color());

        assertTrue(board1.getPiece(1, 2) instanceof Pawn);
        assertEquals(pieceEnum.WHITE, board1.getPiece(1, 2).color());

        assertTrue(board1.getPiece(3, 1) instanceof Knight);
        assertEquals(pieceEnum.BLACK, board1.getPiece(3, 1).color());

        assertTrue(board1.getPiece(4, 5) instanceof Bishop);
        assertEquals(pieceEnum.BLACK, board1.getPiece(4, 5).color());
    }


    public void testIsCheck1() {
        Board brd = new Board();

        assertFalse(brd.isCheck(pieceEnum.BLACK));
        assertFalse(brd.isCheck(pieceEnum.WHITE));

        // 1. make some moves
        brd.removePiece(1, 4);
        brd.removePiece(1, 6);
        brd.removePiece(6, 4);
        brd.removePiece(7, 3);

        brd.setPiece(2, 6, new Pawn(2, 6, brd, pieceEnum.BLACK));
        brd.setPiece(3, 4, new Queen(3, 4, brd, pieceEnum.WHITE));
        brd.setPiece(4, 4, new Pawn(4, 4, brd, pieceEnum.WHITE));

        // 1. check isCheck
        assertTrue(brd.isCheck(pieceEnum.BLACK));
        assertFalse(brd.isCheck(pieceEnum.WHITE));
    }

    public void testIsCheck2() {
        Board brd = new Board();

        // make moves
        brd.removePiece(1, 5);
        brd.removePiece(6, 4);
        brd.removePiece(7, 3);

        brd.setPiece(2, 5, new Pawn(2, 5, brd, pieceEnum.BLACK));
        brd.setPiece(3, 7, new Queen(3, 7, brd, pieceEnum.WHITE));
        brd.setPiece(5, 4, new Pawn(5, 4, brd, pieceEnum.WHITE));

        // check isCheck
        assertTrue(brd.isCheck(pieceEnum.BLACK));
        assertFalse(brd.isCheck(pieceEnum.WHITE));

    }

    public void testIsCheck3() {
        Board brd = new Board(false);

        // make moves
        brd.setPiece(2, 4, new King(2, 4, brd, pieceEnum.BLACK));
        brd.setPiece(7, 4, new Queen(7, 4, brd, pieceEnum.WHITE));

        // check isCheck
        assertTrue(brd.isCheck(pieceEnum.BLACK));

        // move
        brd.removePiece(7, 4);
        brd.setPiece(2, 0, new Rook(2, 0, brd, pieceEnum.WHITE));

        assertTrue(brd.isCheck(pieceEnum.BLACK));

        // make moves
        brd.removePiece(2, 0);
        assertFalse(brd.isCheck(pieceEnum.BLACK));
        brd.setPiece(5, 1, new Bishop(5, 1, brd, pieceEnum.WHITE));
        assertTrue(brd.isCheck(pieceEnum.BLACK));
    }

    public void testMakeMove1() {
        Board brd = new Board(true);

        // pawn
        assertFalse(brd.makeMove(new Coordinate(4, 4), new Coordinate(4, 4), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(6, 4), new Coordinate(4, 4), pieceEnum.WHITE));
        assertTrue(brd.getPiece(4, 4) instanceof Pawn);
        assertTrue(brd.isEmpty(6, 4));

        // knight
        assertTrue(brd.makeMove(new Coordinate(7, 6), new Coordinate(5, 7), pieceEnum.WHITE));
        assertTrue(brd.getPiece(5, 7) instanceof Knight);
        assertTrue(brd.isEmpty(7, 6));

        // pawn
        assertTrue(brd.makeMove(new Coordinate(1, 5), new Coordinate(3, 5), pieceEnum.BLACK));
        assertTrue(brd.getPiece(3, 5) instanceof Pawn);
        assertTrue(brd.isEmpty(1, 5));

        // Queen
        assertTrue(brd.makeMove(new Coordinate(7, 3), new Coordinate(3, 7), pieceEnum.WHITE));
        assertTrue(brd.getPiece(3, 7) instanceof Queen);
        assertTrue(brd.isEmpty(7, 3));

    }

    public void testMakeMove2() {
        Board brd = new Board(true);

        // get to check
        assertTrue(brd.makeMove(new Coordinate(6, 4), new Coordinate(4, 4), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(1,5), new Coordinate(3,5), pieceEnum.BLACK));
        assertTrue(brd.makeMove(new Coordinate(7, 6), new Coordinate(5, 7), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(0,6), new Coordinate(2,5), pieceEnum.BLACK));
        assertTrue(brd.makeMove(new Coordinate(7,3), new Coordinate(3,7), pieceEnum.WHITE));

        // move while isCheck(black) = true
        assertFalse(brd.makeMove(new Coordinate(1,1), new Coordinate(2,1), pieceEnum.BLACK));
        assertFalse(brd.makeMove(new Coordinate(1,1), new Coordinate(3,1), pieceEnum.BLACK));
        assertFalse(brd.makeMove(new Coordinate(0,1), new Coordinate(2,0), pieceEnum.BLACK));
        assertFalse(brd.makeMove(new Coordinate(0,1), new Coordinate(2,2), pieceEnum.BLACK));

        // moves that resolve check
        assertTrue(brd.makeMove(new Coordinate(2,5), new Coordinate(3,7), pieceEnum.BLACK));

        // move king
        assertTrue(brd.makeMove(new Coordinate(7,4), new Coordinate(6,4), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(1,1), new Coordinate(2,1), pieceEnum.BLACK));
        assertTrue(brd.makeMove(new Coordinate(6,1), new Coordinate(5,1), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(3,7), new Coordinate(5,6), pieceEnum.BLACK));

        // check White
        assertTrue(brd.isCheck(pieceEnum.WHITE));
        assertFalse(brd.makeMove(new Coordinate(7,2), new Coordinate(5,0), pieceEnum.WHITE));
        assertTrue(brd.makeMove(new Coordinate(6,4), new Coordinate(5,3),pieceEnum.WHITE ));
        assertTrue(brd.makeMove(new Coordinate(1,7), new Coordinate(3,7), pieceEnum.BLACK));
        assertTrue(brd.makeMove(new Coordinate(7,2), new Coordinate(5,0), pieceEnum.WHITE));

        // move black pawn
        assertTrue(brd.makeMove(new Coordinate(1,6), new Coordinate(2,6),pieceEnum.BLACK ));

    }

    public void testCheckMate1(){
        Board b = new Board(false);


        // set board
        b.setPiece(0,6,new King(0,6,b,pieceEnum.BLACK)); b.getPiece(0,6).setMoved(true);
        b.setPiece(1,3,new Rook(1,3,b,pieceEnum.BLACK)); b.getPiece(1,3).setMoved(true);
        b.setPiece(2,7,new Pawn(2,7,b,pieceEnum.WHITE)); b.getPiece(2,7).setMoved(true);
        b.setPiece(2,4,new Rook(2,4,b,pieceEnum.WHITE)); b.getPiece(2,4).setMoved(true);
        b.setPiece(2,5,new King(2,5,b,pieceEnum.WHITE)); b.getPiece(2,5).setMoved(true);
        b.setPiece(2,6,new Pawn(2,6,b,pieceEnum.WHITE)); b.getPiece(2,6).setMoved(true);

        assertFalse(b.isCheckMate(pieceEnum.BLACK));
        assertFalse(b.isCheckMate(pieceEnum.WHITE));

        assertTrue(b.makeMove(new Coordinate(2,4),new Coordinate(0,4),pieceEnum.WHITE));
        assertTrue(b.isCheckMate(pieceEnum.BLACK));
        assertFalse(b.isCheckMate(pieceEnum.WHITE));

        // add pawn
        b.setPiece(1,5,new Pawn(1,5,b,pieceEnum.WHITE)); b.getPiece(1,5).setMoved(true);
        assertTrue(b.isCheckMate(pieceEnum.BLACK));
        assertFalse(b.isCheckMate(pieceEnum.WHITE));

        // remove pieces
        b.removePiece(2,6);
        assertFalse(b.isCheckMate(pieceEnum.BLACK));
        assertFalse(b.isCheckMate(pieceEnum.WHITE));

        // add pawn
        b.setPiece(1,7,new Queen(1,7,b,pieceEnum.WHITE)); b.getPiece(1,7).setMoved(true);
    }

    public void testCheckMate2(){
        Board b = new Board(true);
        assertTrue(b.makeMove(new Coordinate(6,4), new Coordinate(4,4), pieceEnum.WHITE));
        assertTrue(b.makeMove(new Coordinate(1,4), new Coordinate(3,4), pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(7,5), new Coordinate(4,2), pieceEnum.WHITE));
        assertTrue(b.makeMove(new Coordinate(1,0), new Coordinate(2,0), pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(7,3), new Coordinate(5,5), pieceEnum.WHITE));
        assertTrue(b.makeMove(new Coordinate(1,1), new Coordinate(2,1), pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(5,5), new Coordinate(1,5), pieceEnum.WHITE));

        assertTrue(b.isCheck(pieceEnum.BLACK));
        assertTrue(b.isCheckMate(pieceEnum.BLACK));

        b.removePiece(0,6); b.setPiece(new Knight(3,6,b,pieceEnum.BLACK));
        assertTrue(b.isCheck(pieceEnum.BLACK));
        assertFalse(b.isCheckMate(pieceEnum.BLACK));

    }

    public void testDraw1(){
        Board b = new Board(true);
        assertFalse(b.isDraw(pieceEnum.BLACK));
        assertFalse(b.isDraw(pieceEnum.WHITE));

        b = new Board(false);
        b.setPiece(1,1, new King(1, 1, b, pieceEnum.WHITE));
        b.setPiece(4,4, new King(4, 4, b, pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.WHITE));
        assertTrue(b.isDraw(pieceEnum.BLACK));

        // 2 kings + bishop/knight
        b.setPiece(0,5, new Bishop(0, 5, b, pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.WHITE));

        b.removePiece(0,5); b.blackPieces.remove(b.blackPieces.size()-1);
        b.setPiece(0,5, new Knight(0, 5, b, pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.WHITE));

        // 2 kings + 2 knights
        b.setPiece(6,5, new Knight(6, 5, b, pieceEnum.WHITE));
        assertFalse(b.isDraw(pieceEnum.WHITE));
        assertFalse(b.isDraw(pieceEnum.BLACK));

        b.removePiece(6,5); b.whitePieces.remove(b.whitePieces.size()-1);
        b.setPiece(6,5, new Knight(6,5,b,pieceEnum.BLACK));
        assertTrue(b.isDraw(pieceEnum.WHITE));
        assertTrue(b.isDraw(pieceEnum.BLACK));


    }


    public void testDraw2(){
        Board b = new Board(false);

        b.setPiece(new King(0,0,b,pieceEnum.BLACK));
        b.setPiece(new King(1,3,b,pieceEnum.WHITE));
        b.setPiece(new Queen(1,2,b,pieceEnum.WHITE));

        assertFalse(b.isDraw(pieceEnum.WHITE));
        assertTrue(b.isDraw(pieceEnum.BLACK));

        // add pieces
        b.setPiece(new Pawn(2,5,b,pieceEnum.BLACK));
        b.setPiece(new Pawn(3,5,b,pieceEnum.WHITE));

        assertFalse(b.isDraw(pieceEnum.WHITE));
        assertTrue(b.isDraw(pieceEnum.BLACK));

        b.setPiece(new Knight(0,6,b,pieceEnum.BLACK));
        assertFalse(b.isDraw(pieceEnum.WHITE));
        assertFalse(b.isDraw(pieceEnum.BLACK));

    }

    public void testCastle(){
        Board b = new Board(false);

        b.setPiece(new King(0,4,b,pieceEnum.BLACK));
        b.setPiece(new King(7,4,b,pieceEnum.WHITE));
        b.setPiece(new Rook(0,7,b,pieceEnum.BLACK));
        b.setPiece(new Rook(7,0,b,pieceEnum.WHITE));

        assertTrue(b.makeMove(new Coordinate(0,4), new Coordinate(0,6), pieceEnum.BLACK));
        assertTrue(b.makeMove(new Coordinate(7,4), new Coordinate(7,2), pieceEnum.WHITE));

        assertTrue(b.getPiece(0,5) instanceof Rook);
        assertTrue(b.getPiece(7,3) instanceof Rook);

        assertEquals(2, b.whitePieces.size());
        assertEquals(2, b.blackPieces.size());
    }

    public void testPromotion1(){
        Board b = new Board(false);

        b.setPiece(new Pawn(1,4, b, pieceEnum.WHITE));
        b.setPiece(new King(1,5, b, pieceEnum.WHITE));
        b.setPiece(new Pawn(6,4, b, pieceEnum.BLACK));
        b.setPiece(new King(5,2, b, pieceEnum.BLACK));

        assertFalse(((Pawn)b.getPiece(1,4)).isPromoted());
        assertFalse(((Pawn)b.getPiece(6,4)).isPromoted());

        b.makeMove(new Coordinate(1,4), new Coordinate(0,4), pieceEnum.WHITE );
        b.makeMove(new Coordinate(6,4), new Coordinate(7,4), pieceEnum.BLACK );

        assertTrue(((Pawn)b.getPiece(0,4)).isPromoted());
        assertTrue(((Pawn)b.getPiece(7,4)).isPromoted());
    }

    public void testPromotion2(){
        Board b = new Board(false);

        b.setPiece(new Pawn(1,4, b, pieceEnum.WHITE));
        b.setPiece(new King(1,5, b, pieceEnum.WHITE));
        b.setPiece(new Pawn(6,4, b, pieceEnum.BLACK));
        b.setPiece(new King(5,3, b, pieceEnum.BLACK));
        b.setPiece(new Rook(1,2,b,pieceEnum.BLACK));
        b.setPiece(new Bishop(7,5,b,pieceEnum.WHITE));

        assertFalse(b.makeMove(new Coordinate(1,4), new Coordinate(0,4), pieceEnum.WHITE ));
        assertFalse(b.makeMove(new Coordinate(6,4), new Coordinate(7,4), pieceEnum.BLACK ));

        assertFalse(((Pawn)b.getPiece(1,4)).isPromoted());
        assertFalse(((Pawn)b.getPiece(6,4)).isPromoted());
    }

    public void testPromotion3(){
        Board b = new Board(false);

        b.setPiece(new Pawn(1,4, b, pieceEnum.WHITE));
        b.setPiece(new Bishop(0,5, b, pieceEnum.BLACK));
        b.setPiece(new King(5,5, b, pieceEnum.WHITE));
        b.setPiece(new Pawn(6,0, b, pieceEnum.BLACK));
        b.setPiece(new King(2,0, b, pieceEnum.BLACK));

        assertTrue(b.makeMove(new Coordinate(1,4), new Coordinate(0,5), pieceEnum.WHITE ));
        assertTrue(b.makeMove(new Coordinate(6,0), new Coordinate(7,0), pieceEnum.BLACK ));

        assertTrue(((Pawn)b.getPiece(0,5)).isPromoted());
        assertTrue(((Pawn)b.getPiece(7,0)).isPromoted());

        assertEquals(1, b.whitePieces.size());
        assertEquals(1, b.whitePieces.size());
    }


}
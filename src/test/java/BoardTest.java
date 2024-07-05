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

}
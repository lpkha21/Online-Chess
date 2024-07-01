import junit.framework.TestCase;
import javaClasses.*;


public class BoardTest extends TestCase {

    private Board startBoard;
    private Board board1;

    protected void setUp(){
        startBoard = new Board(true);
        board1 = new Board(false);
        setupBoard1(board1);
    }

    private void setupBoard1(Board board1) {
        // set pawns
        board1.setPiece(1, 0,new Pawn(new Coordinate(1,0), board1, pieceEnum.WHITE));
        board1.setPiece(1, 1,new Pawn(new Coordinate(1,1), board1, pieceEnum.WHITE));
        board1.setPiece(1, 2,new Pawn(new Coordinate(1,2), board1, pieceEnum.WHITE));

        Knight knight = new Knight(new Coordinate(3,1), board1, pieceEnum.BLACK);
        Bishop bishop = new Bishop(new Coordinate(4,5), board1, pieceEnum.BLACK);
        board1.setPiece(3, 1, knight);
        board1.setPiece(4, 5, bishop);
    }

    public void testBoardInitialization(){

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
        assertTrue(startBoard.getPiece(0, 3) instanceof King);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 3).color());
        assertTrue(startBoard.getPiece(0, 4) instanceof Queen);
        assertEquals(pieceEnum.BLACK, startBoard.getPiece(0, 4).color());

        // Test white king and queen
        assertTrue(startBoard.getPiece(7, 3) instanceof King);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 3).color());
        assertTrue(startBoard.getPiece(7, 4) instanceof Queen);
        assertEquals(pieceEnum.WHITE, startBoard.getPiece(7, 4).color());


    }

    public void testIsEmpty1(){
        for(int i=2; i< startBoard.SIZE-2; i++)
            for(int j=0; j< startBoard.SIZE; j++)
                assertTrue(startBoard.isEmpty(i,j));
    }

    public void testIsEmpty2(){
        assertFalse(board1.isEmpty(1, 0));
        assertTrue(board1.isEmpty(0,0));
        assertFalse(board1.isEmpty(1, 1));
        assertTrue(board1.isEmpty(2, 1));
        assertFalse(board1.isEmpty(3, 1));
        assertTrue(board1.isEmpty(4, 4));
        assertFalse(board1.isEmpty(4, 5));

    }

    public void testGetPiece(){
        for(int i=2; i< startBoard.SIZE-2; i++)
            for(int j=0; j< startBoard.SIZE; j++)
                assertNull(startBoard.getPiece(i,j));
    }

    public void testGetPieceAndSetPiece(){
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

    public void testMakeMove(){

    }

    public void testIsCheck(){

    }

}

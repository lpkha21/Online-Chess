package javaClasses;

public class Game {

    Board board;
    Player whitePlayer;
    Player blackPlayer;

    public Game(){
        board = new Board();
        whitePlayer = new Player(pieceEnum.WHITE);
        blackPlayer = new Player(pieceEnum.BLACK);
    }
}

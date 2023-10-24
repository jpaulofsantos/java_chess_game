package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;

public class MainProgram {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());

    }
}

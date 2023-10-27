package application;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            UI.printBoard(chessMatch.getPieces());
            System.out.println("");
            System.out.print("Digite a posição de origem: ");
            ChessPosition source = UI.readChessPosition(scanner);

            System.out.print("Digite a posição de destino: ");
            ChessPosition destino = UI.readChessPosition(scanner);

            chessMatch.performeChessMove(source, destino);
        }
    }
}

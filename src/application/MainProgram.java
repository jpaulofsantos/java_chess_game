package application;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println("");
                System.out.print("Digite a posição de origem: ");
                ChessPosition source = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chessMatch.possibleChessMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.print("Digite a posição de destino: ");
                ChessPosition destino = UI.readChessPosition(scanner);

                chessMatch.performeChessMove(source, destino);
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }
}

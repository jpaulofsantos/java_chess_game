package application;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> listCapturedChessPieces = new ArrayList<>();

        //System.out.println(("Classpath: "));
        //System.out.println(System.getProperty("java.class.path"));

        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, listCapturedChessPieces);
                System.out.println("");
                System.out.print("Digite a posição de origem: ");
                ChessPosition source = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chessMatch.possibleChessMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                //UI.printMatch(chessMatch);

                System.out.print("Digite a posição de destino: ");
                ChessPosition destino = UI.readChessPosition(scanner);

                ChessPiece chessPiece = chessMatch.performeChessMove(source, destino);

                if (chessPiece != null) {
                    listCapturedChessPieces.add(chessPiece);
                }

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

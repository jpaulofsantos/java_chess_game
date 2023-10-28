package application;

import boardgame.Piece;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import chess.pieces.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static chess.Color.WHITE;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        //System.out.print("\033[H\033[2J");
        System.out.println("\u001B[2J");
        System.out.flush();
    }
    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String string = scanner.next();
            char column = string.charAt(0);
            int row = Integer.parseInt(string.substring(1));

            return new ChessPosition(column, row);

        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro. Dados devem ser entre a1 e h8");
        }
    }

    public static void printBoard(ChessPiece[][] chessPieces) {
        System.out.println();
        System.out.println("                A B C D E F G H");
        System.out.println("                | | | | | | | |");
        System.out.println("                _______________");

        for (int i=0; i<chessPieces.length; i++) { //loop nas linhas da matriz

            System.out.print("     linha " + (8-i) + "    "); //imprimindo o começo da linha
            for (int j=0; j<chessPieces.length; j++)  { //loop nas colunas (ex: preenche na linha 0, as colunas 0 a 7 com o "-"
                //System.out.print("- ");
                //System.out.print(i+j + " ");
                printPiece(chessPieces[i][j]); //imprimindo a Piece na posição da matriz informada
            }
            System.out.println(); //pulando uma linha para imprimir a proxima linha do for
        }

        System.out.println("                _______________");
        System.out.println("                | | | | | | | |");
        System.out.print("                A B C D E F G H"); //imprimindo as colunas no final do tabuleiro
        System.out.println();
    }

    private static void printPiece(ChessPiece chessPiece) {
        if (chessPiece == null) {
            System.out.print("-");
        }

        /*else {
            System.out.print(" " + PieceSymbolFactory.getSymbol(chessPiece));
        }*/
        else {
            if (chessPiece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + chessPiece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static void print2(ChessPiece[][] chessPieces) {
        for (int i = 0; i<chessPieces.length; i++) {
            System.out.print(8-i + " ");
            for (int j=0; j<chessPieces.length; j++) {
                //System.out.print("- ");
                printPiece(chessPieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    public static class PieceSymbolFactory {
        private static final int whiteKing = 2654;
        private static final int whiteQueen = 2655;
        private static final int whiteRook = 2656;
        private static final int whiteBishop = 2657;
        private static final int whiteKnight = 2658;
        private static final int whitePawn = 2659;

        private static final int blackKing = 0x265A;
        private static final int blackQueen = 0x265B;
        private static final int blackRook = 0x265C;
        private static final int blackBishop = 0x265D;
        private static final int blackKnight = 0x265E;
        private static final int blackPawn = 0x265F;


        static char getSymbol(ChessPiece piece) {
            if (piece instanceof King) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteKing), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackKing)));
                }
            }
            if (piece instanceof Queen) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteQueen), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackQueen)));
                }
            }
            if (piece instanceof Rook) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteRook), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackRook)));
                }
            }
            if (piece instanceof Bishop) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteBishop), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackBishop)));
                }
            }
            if (piece instanceof Knight) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteKnight), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackKnight)));
                }
            }
            if (piece instanceof Pawn) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whitePawn), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackPawn)));
                }
            }
            return 0;
        }
    }
}

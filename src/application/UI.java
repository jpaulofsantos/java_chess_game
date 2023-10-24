package application;

import boardgame.Piece;
import chess.ChessPiece;
import chess.pieces.King;
import chess.pieces.Rook;

import static chess.Color.WHITE;

public class UI {

    public static void printBoard(ChessPiece[][] chessPieces) {
        System.out.println();
        System.out.println("                A B C D E F G H");
        System.out.println("                | | | | | | | |");

        for (int i=0; i<chessPieces.length; i++) { //loop nas linhas da matriz

            System.out.print("     linha " + (8-i) + "    "); //imprimindo o começo da linha
            for (int j=0; j<chessPieces.length; j++)  { //loop nas colunas (ex: preenche na linha 0, as colunas 0 a 7 com o "-"
                //System.out.print("- ");
                //System.out.print(i+j + " ");
                printPiece(chessPieces[i][j]); //imprimindo a Piece na posição da matriz informada
            }
            System.out.println(); //pulando uma linha para imprimir a proxima linha do for
        }

        System.out.println("                | | | | | | | |");
        System.out.print("                A B C D E F G H"); //imprimindo as colunas no final do tabuleiro
        System.out.println();
    }

    private static void printPiece(ChessPiece chessPiece) {
        if (chessPiece == null) {
            System.out.print("-");
        } else {
            //System.out.print(chessPiece);
            System.out.print(PieceSymbolFactory.getSymbol(chessPiece));
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
        private static final int blackKing = 0x265A;
        private static final int whiteRook = 2656;
        private static final int blackRook = 0x265C;

        static char getSymbol(ChessPiece piece) {
            if (piece instanceof King) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteKing), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackKing)));
                }
            }
            if (piece instanceof Rook) {
                if (piece.getColor() == WHITE) {
                    return (char) (Integer.parseInt(String.valueOf(whiteRook), 16));
                } else {
                    return (char) (Integer.parseInt(String.valueOf(blackRook)));
                }
            }
            return 0;
        }
    }
}

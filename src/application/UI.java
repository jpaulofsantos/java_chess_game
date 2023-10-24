package application;

import chess.ChessPiece;

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
            System.out.print(chessPiece);
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
}

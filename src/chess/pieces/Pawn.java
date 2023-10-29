package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    int move = 0;
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow()-1, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            matriz[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValues(position.getRow()+1, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            matriz[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }

        return matriz;
    }
}

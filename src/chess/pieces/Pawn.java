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

    private boolean canMove (Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow()-1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            matriz[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValues(position.getRow()+1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            matriz[p.getRow()][p.getColumn()] = true;
        }

        return matriz;
    }
}

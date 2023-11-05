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

        if (getColor() == Color.BLACK) {
            p.setValues(position.getRow()-1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()-2, position.getColumn()); //pegando a posição 2 linhas acima para as validações
            Position p2 = new Position(position.getRow()-1, position.getColumn()); //pegando a posição 1 linha acima para as validações
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()-1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()-1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }
        } else {
            p.setValues(position.getRow()+1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()+2, position.getColumn()); //pegando a posição 2 linhas acima para as validações
            Position p2 = new Position(position.getRow()+1, position.getColumn()); //pegando a posição 1 linha acima para as validações
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()+1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow()+1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matriz[p.getRow()][p.getColumn()] = true;
            }
        }
        return matriz;
    }
}

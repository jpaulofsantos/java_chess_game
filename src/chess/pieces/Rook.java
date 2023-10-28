package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0 );

        //checking positions above
        p.setValues(position.getRow()-1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //percorre as linhas verificando se a posição existe e se existe uma peça
            matriz[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }

        //checking positions left
        p.setValues(position.getRow(), position.getColumn()-1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //percorre as linhas verificando se a posição existe e se existe uma peça
            matriz[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }

        //checking positions right
        p.setValues(position.getRow(), position.getColumn()+1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //percorre as linhas verificando se a posição existe e se existe uma peça
            matriz[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }

        //checking positions below
        p.setValues(position.getRow()+1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //percorre as linhas verificando se a posição existe e se existe uma peça
            matriz[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se a posição existe e se existe uma peça do adversário
            matriz[p.getRow()][p.getColumn()] = true;
        }



        return matriz;
    }
}

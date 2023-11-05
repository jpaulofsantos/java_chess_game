package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rook;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount; //começa com o valor 0, então não é necessário inicializar no construtor
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

    public Color getColor() {
        return color;
    }

    protected void increaseMoveCount() {
        moveCount++;
        //System.out.println("Count: " + moveCount);
    }

    protected void decreaseMoveCount() {
        moveCount--;
    }

    public int getMoveCount() {
        return moveCount;
    }
}

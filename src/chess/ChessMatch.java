package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;

    private Board board;

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        turn+=1;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) { //método passando as posições do xadrez (a8) ao inves da matriz
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public boolean[][] possibleChessMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition(); //convertendo a posição do xadrez para a posição da matriz.
        validadeSourcePosition(position);
        return board.piece(position).possibleMoves(); //retorna os movimentos possíveis da peça na posição;
    }

    public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition ) {

        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        
        validadeSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("Movimento de destino não é válido para esta peça.");
        }
    }

    private Piece makeMove(Position source, Position target) {

        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        return capturedPiece;
    }

    private void validadeSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não existe peça na posição de origem!");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("Não é possível movimentar a peça do adversário!");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Esta peça não possui movimentos disponíveis!");
        }
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i< board.getRows(); i++) {
            for (int j=0; j<board.getColumns(); j++) {
                matriz[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return matriz;
    }

    private void initialSetup() {

        placeNewPiece('a', 8, new Rook(board, Color.WHITE));
        //board.placePiece(new Rook(board, Color.BLACK), new Position(1,0));
        placeNewPiece('b', 8, new Knight(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 8, new Queen(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.WHITE));
        placeNewPiece('f', 8, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 8, new Knight(board, Color.WHITE));
        placeNewPiece('h', 8, new Rook(board, Color.WHITE));

        placeNewPiece('a', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 7, new Pawn(board, Color.WHITE));
        //placeNewPiece('e', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 7, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 7, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 1, new Rook(board, Color.BLACK));
        //board.placePiece(new Rook(board, Color.BLACK), new Position(1,0));
        placeNewPiece('b', 1, new Knight(board, Color.BLACK));
        placeNewPiece('c', 1, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 1, new Queen(board, Color.BLACK));
        placeNewPiece('e', 1, new King(board, Color.BLACK));
        placeNewPiece('f', 1, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 1, new Knight(board, Color.BLACK));
        placeNewPiece('h', 1, new Rook(board, Color.BLACK));

        //placeNewPiece('a', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 2, new Pawn(board, Color.BLACK));
        //placeNewPiece('e', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 2, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 2, new Pawn(board, Color.BLACK));

        //placeNewPiece('b', 8, new Knight(board, Color.WHITE));
    }
}

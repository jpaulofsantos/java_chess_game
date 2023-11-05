package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;

    private Board board;

    private List<Piece> piecesOnTheBoardList = new ArrayList<>();
    private List<Piece> capturedPiecesList = new ArrayList<>();

    private boolean check;
    private boolean checkMate;

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

    private Color getOpponentColor(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    private ChessPiece king(Color color) { //busca o rei da cor passada
        List<Piece> list = piecesOnTheBoardList.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("Não existe um rei da cor " + color + " no tabuleiro");

    }

    private boolean testCheck(Color color) { //testando se o rei está em check
        Position kingPositon = king(color).getChessPosition().toPosition(); //pegando a posição do rei no formato de matriz
        List<Piece> listOpponent = piecesOnTheBoardList.stream().filter(x-> ((ChessPiece)x).getColor() == getOpponentColor(color)).collect(Collectors.toList());
        for (Piece p : listOpponent) {
            boolean[][] matriz = p.possibleMoves();
            if (matriz[kingPositon.getRow()][kingPositon.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) { //testando checkmate
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoardList.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); //pegando todas as peças da cor informada no parâmetro;
        for (Piece p : list) {
            boolean[][] matriz = p.possibleMoves();
            for (int i =0; i< board.getRows(); i++) {
                for (int j=0; j< board.getColumns(); j++) {
                    if (matriz[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) { //método passando as posições do xadrez (a8) ao inves da matriz
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoardList.add(piece);
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

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Este movimento te coloca em Check, tente outra posição.");
        }
        check = (testCheck(getOpponentColor(currentPlayer))) ? true : false;

        if (testCheckMate(getOpponentColor(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }
        return (ChessPiece) capturedPiece;
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("Movimento de destino não é válido para esta peça.");
        }
    }

    private Piece makeMove(Position source, Position target) {

        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoardList.remove(capturedPiece);
            capturedPiecesList.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPiecesList.remove(capturedPiece);
            piecesOnTheBoardList.add(capturedPiece);
        }
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

        placeNewPiece('d', 3, new Rook(board, Color.BLACK));
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

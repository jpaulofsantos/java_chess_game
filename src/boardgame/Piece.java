package boardgame;

public class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) { //para criar uma peça precisamos passar um tabuleiro e a posição inicial é nula
        this.board = board;
        position=null;
    }

    protected Board getBoard() { //somente classes dentro do mesmo pacote e sub classes tem acesso ao método get do tabuleiro. Acesso é protegido para manter o sistema coeso e evitar problemas
        return board;
    }
}

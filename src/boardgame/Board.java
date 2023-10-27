package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;

    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if (rows<1 || columns < 1) {
            throw new BoardException("Erro na criação do tabuleiro, é necessário pelo menos 1 coluna e 1 linha.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece piece(int row, int column){
        if (!positionExists(row, column)) {
            throw new BoardException("Posição informada inexistente!");
        }
        return pieces[row][column];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("Já existe uma peça nesta posição " + "(" +position+")" + " informada!");
        }
        //atribuindo uma peça na posição da matriz informada.
        pieces[position.getRow()][position.getColumn()] = piece; // a matriz de pieces é a declarada na classe e iniciada no construtor;
        piece.position = position; //acesso ao position é possivel pois estamos dentro do mesmo pacote
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Posição informada não existe!");
        }
        if (piece(position) == null) {
            //System.out.println("Não existe nenhuma peça nesta posição para ser removida");
            return null;
        } else {
            Piece aux = piece(position);
            aux.position = null;
            pieces[position.getRow()][position.getColumn()] = null;
            return aux;
        }
    }

    private boolean positionExists(int row, int column) {
        return (row>=0 && row<rows && column>=0 && column<columns);
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Posição informada inexistente!");
        }
        return piece(position) != null;
    }

    public Piece piece(Position position){
        if (!positionExists(position)) {
            throw new BoardException("Posição informada inexistente!");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public Integer getRows() {
        return rows;
    }
    public Integer getColumns() {
        return columns;
    }
}

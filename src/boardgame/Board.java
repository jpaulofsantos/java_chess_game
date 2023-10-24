package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;

    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece piece(int row, int column){
        return pieces[row][column];
    }

    public void placePiece(Piece piece, Position position) {
        //atribuindo uma peça na posição da matriz informada.
        pieces[position.getRow()][position.getColumn()] = piece; // a matriz de pieces é a declarada na classe e iniciada no construtor;
        piece.position = position; //acesso ao position é possivel pois estamos dentro do mesmo pacote
    }

    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }
}

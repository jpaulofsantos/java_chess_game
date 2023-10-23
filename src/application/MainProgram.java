package application;

import boardgame.Board;
import boardgame.Position;

public class MainProgram {
    public static void main(String[] args) {
        Board board = new Board(8,8);
        board.setColumns(12);
        System.out.println(board.getRows() + "-" + board.getRows() + "-");
    }
}

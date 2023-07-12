package pl.ryder;

import lombok.Getter;

import java.util.Random;

import static java.lang.System.out;

public class BoardController {
    @Getter
    private final Board board;

    public BoardController() {
        this(30);
    }
    public BoardController(int x) {
        this(x, x, System.nanoTime());
    }

    public BoardController(int x, int y, long seed) {
        board = new Board(x, y);
        Random rand = new Random(seed);

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                board.set(i, j, rand.nextBoolean());
    }

    private int countNeighbours(int x, int y) {
        int neighbours = 0;
        for (int i = x - 1; i < x + 2; i++) {
            if (i < 0 || i >= board.getWidth()) continue;

            for (int j = y - 1; j < y + 2; j++) {
                if ((i == x && j == y) || j < 0 || j >= board.getHeight()) continue;

                if (board.get(i, j)) neighbours++;
            }
        }
        return neighbours;
    }

    static void print(boolean[][] board) {
        for (boolean[] booleans : board) {
            for (int j = 0; j < board[0].length; j++) {
                out.print(booleans[j] ? 1 : 0);
            }
            out.println();
        }
    }

    public void next() {
        boolean[][] tmpBoard = new boolean[board.getHeight()][board.getWidth()];

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                int neighbours = countNeighbours(i, j);
                if (board.get(i, j)) {
                    if (neighbours == 2 || neighbours == 3) tmpBoard[i][j] = true;
                }
                else if (neighbours == 3) tmpBoard[i][j] = true;
            }
        }

        for (int i = 0; i < board.getWidth(); i++)
            for (int j = 0; j < board.getHeight(); j++)
                board.set(i, j, tmpBoard[i][j]);


    }
}

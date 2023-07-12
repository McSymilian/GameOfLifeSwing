package pl.ryder;


import java.io.Serializable;

public class Board implements Serializable {
    private final boolean[][] lifeStates;

    public Board(int x, int y) {
        lifeStates = new boolean[x][y];
    }

    public boolean get(int x, int y) {
        return lifeStates[x][y];
    }

    public void set(int x, int y, boolean state) {
        lifeStates[x][y] = state;
    }

    public int getWidth() {
        if (lifeStates.length == 0)
            throw new IllegalStateException("Empty board");

        return lifeStates.length;
    }

    public int getHeight() {
        if (getWidth() == 0)
            throw new IllegalStateException("Empty board");

        return lifeStates[0].length;
    }

    public boolean[][] getPlainBoard() {
        return lifeStates;
    }
}

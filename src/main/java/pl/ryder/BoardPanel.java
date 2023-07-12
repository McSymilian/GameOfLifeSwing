package pl.ryder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BoardPanel extends JPanel {
    private final Board board;

    private final int cellWidth;
    private final int cellHeight;

    public BoardPanel(Board board) {
        this.board = board;
        cellWidth = 500 / board.getWidth();
        cellHeight = 500 / board.getHeight();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX() / cellWidth;
                int y = e.getY() / cellHeight;

                board.set(x, y, !board.get(x, y));
                repaint(e.getX() - cellWidth,  e.getY() - cellHeight, cellWidth * 2 , cellHeight * 2);
            }
        });
    }

    void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.get(i, j)) g2d.setColor(Color.ORANGE);
                else g2d.setColor(Color.GRAY);

                g2d.fillRoundRect(cellWidth * i, cellHeight * j, cellWidth, cellHeight, (int) (cellWidth * .3), (int) (cellHeight * .3));
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawRectangles(g);
    }
}

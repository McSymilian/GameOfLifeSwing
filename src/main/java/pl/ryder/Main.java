package pl.ryder;

import javax.swing.*;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        BoardController boardController = new BoardController(500);
        MainPanel mainPanel = new MainPanel(boardController.getBoard());

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(mainPanel.getContent());
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });

        Thread gameThread = new Thread(() -> {
            long timeBegin = System.currentTimeMillis();
            while (true) {
                if (!mainPanel.isStopped()) {
                    long step = 100_000L / mainPanel.getSpeed();
                    if (timeBegin + step <= System.currentTimeMillis()) {
                        boardController.next();
                        mainPanel.repaintBoard();
                        timeBegin = System.currentTimeMillis();
                        mainPanel.nextGeneration();
                    }
                } else out.print("");
            }
        });
        gameThread.setPriority(Thread.MAX_PRIORITY);
        gameThread.setName("Game Thread");
        gameThread.start();
    }
}
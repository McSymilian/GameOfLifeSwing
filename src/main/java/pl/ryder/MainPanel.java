package pl.ryder;

import lombok.Getter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import java.util.Random;

public class MainPanel {
    private JButton actionButton;
    private JSlider speedSlider;
    private JPanel boardContainer;

    @Getter
    private JPanel content;
    private JLabel genLabel;
    private JButton clearButton;
    private JButton resetButton;

    @Getter
    private boolean stopped = true;

    @Getter
    private int speed = 100;
    @Getter
    private final Board board;

    private final BoardPanel boardPanel;

    private int generation = 0;
    public MainPanel(Board board) {
        this.board = board;

        boardPanel = new BoardPanel(board);

        actionButton.addActionListener(e -> {
            if (stopped) {
                actionButton.setText("Stop");
                stopped = false;
            }
            else {
                actionButton.setText("Start");
                stopped = true;
            }
        });
        speedSlider.addChangeListener(e -> speed = speedSlider.getValue());

        SwingUtilities.invokeLater(() -> boardContainer.add(boardPanel, "BOARD"));
        clearButton.addActionListener(e -> {
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    board.set(i, j, false);
                }
            }
            boardPanel.repaint();
        });
        resetButton.addActionListener(e -> {
            Random rand = new Random(System.nanoTime());
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    board.set(i, j, rand.nextBoolean());
                    boardPanel.repaint();
                    this.generation = 0;
                }
            }
        });
    }

    public void repaintBoard() {
        SwingUtilities.invokeLater(boardPanel::repaint);
    }

    public int nextGeneration() {
        setGeneration(++generation);
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
        SwingUtilities.invokeLater(() -> genLabel.setText("Gen# " + this.generation));
    }
}

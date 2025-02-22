package Body;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PuzzleGame extends JFrame {
    private static final int SIZE = 4; // 拼图的大小，4x4
    private final JPanel[][] panels = new JPanel[SIZE][SIZE];
    private final int[][] numbers = new int[SIZE][SIZE];
    private int emptyX = SIZE - 1;
    private int emptyY = SIZE - 1;

    public PuzzleGame() {
        setTitle("Java Puzzle Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE, 5, 5)); // 设置网格布局

        // 初始化面板和数字
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(panels[i][j]);
            }
        }

        // 打乱数字
        shuffleNumbers();

        // 添加鼠标监听器
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final int row = i;
                final int col = j;
                panels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (canMove(row, col)) {
                            movePiece(row, col);
                            updateUI();
                            if (isSolved()) {
                                JOptionPane.showMessageDialog(null, "恭喜你完成了拼图！");
                            }
                        }
                    }
                });
            }
        }

        updateUI();
    }

    private void updateUI() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                panels[i][j].removeAll();
                if (numbers[i][j] != 0) {
                    panels[i][j].add(new JLabel(String.valueOf(numbers[i][j]), SwingConstants.CENTER));
                }
                panels[i][j].revalidate();
                panels[i][j].repaint();
            }
        }
    }

    private void shuffleNumbers() {
        int[] flatNumbers = new int[SIZE * SIZE];
        for (int i = 0; i < flatNumbers.length; i++) {
            flatNumbers[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < flatNumbers.length; i++) {
            int randomIndex = random.nextInt(flatNumbers.length);
            int temp = flatNumbers[i];
            flatNumbers[i] = flatNumbers[randomIndex];
            flatNumbers[randomIndex] = temp;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                numbers[i][j] = flatNumbers[i * SIZE + j];
                if (numbers[i][j] == 0) {
                    emptyX = i;
                    emptyY = j;
                }
            }
        }
    }

    private boolean canMove(int row, int col) {
        return (Math.abs(row - emptyX) == 1 && col == emptyY) || (Math.abs(col - emptyY) == 1 && row == emptyX);
    }

    private void movePiece(int row, int col) {
        numbers[emptyX][emptyY] = numbers[row][col];
        numbers[row][col] = 0;
        emptyX = row;
        emptyY = col;
    }

    private boolean isSolved() {
        int count = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (numbers[i][j] != count % (SIZE * SIZE)) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuzzleGame game = new PuzzleGame();
            game.setVisible(true);
        });
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board extends JPanel {

    static int x_offset = 20;
    static int y_offset = 20;
    static int square_size = 15;

    static int additional_space = 70;

    public Board() {
        setSize(main.boardLength * square_size + additional_space,
                main.boardLength * square_size + additional_space);
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        paintSnake();
        paintBerry();

        paintSquares(g);
        drawScoreLabel(g);
    }

    void drawScoreLabel(Graphics g) {
        g.setColor(Color.GRAY);
        Font f = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(f);
        g.drawString(String.valueOf(main.score), 10, 15);
    }


    void paintSquares(Graphics g) {
        int value;
        for (int i = 0; i < main.matrix.length; i++) {
            for (int j = 0; j < main.matrix[i].length; j++) {
                value = main.matrix[i][j];
                switch (value) {
                    case 0: // Background
                        g.setColor(Color.BLACK);
                        break;
                    case 1: // Berry
                        g.setColor(Color.RED);
                        break;
                    case 2: // Snake
                        g.setColor(Color.GREEN);
                        break;
                }
                g.fillRect(square_size * i + x_offset, square_size * j + y_offset, square_size, square_size);
            }
        }
    }

    void paintSnake() {
        for (Point point : main.snake) {
            main.matrix[point.x][point.y] = 2;
        }
    }

    void paintBerry() {
        main.matrix[main.berry.x][main.berry.y] = 1;
    }

    public void blackout() {
        for (int i = 0; i < main.matrix.length; i++) {
            Arrays.fill(main.matrix[i], 0);
        }
    }

}


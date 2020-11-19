import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    static int x_offset = 20;
    static int y_offset = 20;
    static int square_size = 15;

    static int additional_space = 70;

    public Board() {
        setSize(main.boardLength * square_size + additional_space, main.boardLength * square_size + additional_space);
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
                    case 0:
                        g.setColor(Color.BLACK);
                        break;
                    case 1:
                        g.setColor(Color.RED);
                        break;
                    case 2:
                        g.setColor(Color.GREEN);
                        break;
                }
                g.fillRect(square_size * i + x_offset, square_size * j + y_offset, square_size, square_size);
            }
        }
    }

    void paintSnake() {
        for (int i = 0; i < main.snake.size(); i++) {
            main.matrix[main.snake.get(i).x][main.snake.get(i).y] = 2;
        }
    }

    void paintBerry() {
        main.matrix[main.berry_x][main.berry_y] = 1;
    }

    public void blackout() {
        for (int i = 0; i < main.matrix.length; i++) {
            for (int j = 0; j < main.matrix[i].length; j++) {
                main.matrix[i][j] = 0;
            }
        }
    }

}


import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public Board() {
        setSize(1000, 1000);
        repaint();
    }

    // SIZE of the Board 1000x1000
    // One square at size 20x20 -> 400 Squares

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        paintSquares(g);
        paintSnake();
        g.setColor(Color.GRAY);
        g.drawString(String.valueOf(main.score), 10, 10);
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(main.snake.size()), 20, 20);

        main.matrix[main.berryx][main.berryy] = 1;
    }

    void paintSquares(Graphics g) {
        int value = 0;
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
                    case 3:
                        g.setColor(Color.YELLOW);
                        break;
                }
                g.fillRect(20 * i, 20 * j, 20 * i + 10, 20 * j + 20);
            }
        }
    }

    void paintSnake() {
        for (int i = 0; i < main.snake.size(); i++) {
            main.matrix[main.snake.get(i).x][main.snake.get(i).y] = 2;
        }
    }

    void paintBerry() {
        main.matrix[main.berryx][main.berryy] = 1;
    }

    public void blackout() {
        for (int i = 0; i < main.matrix.length; i++) {
            for (int j = 0; j < main.matrix[i].length; j++) {
                main.matrix[i][j] = 0;
            }
        }

    }

}


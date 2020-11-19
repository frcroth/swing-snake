import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class main {

    static int boardLength = 50;

    public static int[][] matrix = new int[boardLength][boardLength];
    public static int score;

    static int berry_x;
    static int berry_y;

    static Frame frame;

    static ArrayList<Square> snake = new ArrayList<Square>();

    static int direction = 0; //Right 0 Down 1 Left 2 Up 3

    static int delay = 50;

    public static void main(String[] args) {
        frame = new Frame();
        init();

        while (true) {

            tick();
            frame.repaint();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void init() {
        newBerry();
        frame.gameBoard.blackout();
        snake.clear();
        snake.add(new Square(9, 10));
        snake.add(new Square(8, 10));
        snake.add(new Square(7, 10));
        score = 0;
    }

    public static void newBerry() {
        berry_x = ThreadLocalRandom.current().nextInt(1, boardLength);
        berry_y = ThreadLocalRandom.current().nextInt(1, boardLength);
    }

    static void tick() {
        //MOVE SNAKE
        moveSnake(direction);
        if (berry_x == snake.get(0).x && berry_y == snake.get(0).y) {
            score++;
            newBerry();
            addSquareToSnake();
        }
        if (nextMoveLastMove()) {
            init();
        }
    }

    private static boolean nextMoveLastMove() {
        switch (direction) {
            case 0:
                for (int i = 0; i < snake.size(); i++) {
                    if (snake.get(i).x == snake.get(0).x + 1 && snake.get(i).y == snake.get(0).y) {
                        return true;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < snake.size(); i++) {
                    if (snake.get(i).x == snake.get(0).x && snake.get(i).y == snake.get(0).y + 1) {
                        return true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < snake.size(); i++) {
                    if (snake.get(i).x == snake.get(0).x - 1 && snake.get(i).y == snake.get(0).y) {
                        return true;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < snake.size(); i++) {
                    if (snake.get(i).x == snake.get(0).x && snake.get(i).y == snake.get(0).y - 1) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    private static void addSquareToSnake() {
        switch (direction) {
            case 0:
                snake.add(new Square(snake.get(snake.size() - 1).x - 1, snake.get(snake.size() - 1).y));
                break;
            case 1:
                snake.add(new Square(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y - 1));
                break;
            case 2:
                snake.add(new Square(snake.get(snake.size() - 1).x + 1, snake.get(snake.size() - 1).y));
                break;
            case 3:
                snake.add(new Square(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y + 1));
                break;
        }
    }

    private static void moveSnake(int dir) {
        switch (dir) {
            case 0:
                snake.add(0, new Square(snake.get(0).x + 1, snake.get(0).y));
                matrix[snake.get(snake.size() - 1).x][snake.get(snake.size() - 1).y] = 0;
                snake.remove(snake.size() - 1);
                break;
            case 1:
                snake.add(0, new Square(snake.get(0).x, snake.get(0).y + 1));
                matrix[snake.get(snake.size() - 1).x][snake.get(snake.size() - 1).y] = 0;
                snake.remove(snake.size() - 1);
                break;
            case 2:
                snake.add(0, new Square(snake.get(0).x - 1, snake.get(0).y));
                matrix[snake.get(snake.size() - 1).x][snake.get(snake.size() - 1).y] = 0;
                snake.remove(snake.size() - 1);
                break;
            case 3:
                snake.add(0, new Square(snake.get(0).x, snake.get(0).y - 1));
                matrix[snake.get(snake.size() - 1).x][snake.get(snake.size() - 1).y] = 0;
                snake.remove(snake.size() - 1);
                break;
        }
        frame.gameBoard.repaint();
    }

}

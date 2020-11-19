import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class main {

    static int boardLength = 50;

    public static int[][] matrix = new int[boardLength][boardLength];
    public static int score;

    static Point berry;

    static Frame frame;

    static ArrayList<Point> snake = new ArrayList<Point>();

    enum Direction {
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0),
        UP(0, -1);

        final int x;
        final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Direction direction = Direction.RIGHT;

    static int delay = 50;

    public static void main(String[] args) {
        init();
        frame = new Frame();
        frame.gameBoard.blackout();

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

        snake.clear();
        snake.add(new Point(17, 10));
        snake.add(new Point(16, 10));
        snake.add(new Point(15, 10));
        score = 0;
    }

    public static void newBerry() {
        berry = new Point(ThreadLocalRandom.current().nextInt(1, boardLength),
                ThreadLocalRandom.current().nextInt(1, boardLength));
    }

    static void tick() {
        //MOVE SNAKE
        moveSnake(direction);
        if (snake.get(0).equals(berry)) {
            score++;
            newBerry();
            addSquareToSnake();
        }
        if (nextMoveDisallowed()) {
            frame.gameBoard.blackout();
            init();
        }
    }

    private static boolean nextMoveDisallowed() {
        Point nextPoint = new Point(snake.get(0).x + direction.x, snake.get(0).y + direction.y);

        for (Point point : snake) {
            if (point.equals(nextPoint)) {
                return true;
            }
        }
        return nextPoint.x > boardLength || nextPoint.y > boardLength || nextPoint.x < -1 || nextPoint.y < -1;
    }

    private static void addSquareToSnake() {
        snake.add(new Point(snake.get(snake.size() - 1).x - direction.x,
                snake.get(snake.size() - 1).y - direction.y));
    }

    private static void moveSnake(Direction direction) {
        snake.add(0, new Point(snake.get(0).x + direction.x, snake.get(0).y + direction.y));
        matrix[snake.get(snake.size() - 1).x][snake.get(snake.size() - 1).y] = 0;
        snake.remove(snake.size() - 1);

        frame.gameBoard.repaint();
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class Frame extends JFrame {

    Board gameBoard;

    public Frame() {
        gameBoard = new Board();

        setSize(gameBoard.getWidth(), gameBoard.getHeight());
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Swing Snake");
        add(gameBoard);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


		InputMap inputMap = gameBoard.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = gameBoard.getActionMap();

		AbstractAction right = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.direction = 0;

            }
        };
		AbstractAction left = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.direction = 2;

            }
        };
		AbstractAction up = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.direction = 3;

            }
        };
		AbstractAction down = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.direction = 1;

            }
        };

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
        actionMap.put("right", right);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
        actionMap.put("left", left);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
        actionMap.put("down", down);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
        actionMap.put("up", up);
    }

}

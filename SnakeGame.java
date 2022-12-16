import javax.swing.*;
import java.awt.*;

public class SnakeGame {

    JFrame jFrame;

    SnakeGame()
    {
        // create the panel having name Snake Game
        jFrame = new JFrame("Snake Game");
        jFrame.setBounds(10, 10, 905, 700);
        // MyPanel is used for creating the panel which is used for drawing or printing over the frame
        MyPanel panel = new MyPanel();
        // set the colour of the panel to gray.
        panel.setBackground(Color.gray);
        // add panel to frame.
        jFrame.add(panel);

        // to stop the unwanted execution of the code.
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // make frame visible
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGame s = new SnakeGame();
    }
}

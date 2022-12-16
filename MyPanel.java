import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {
    // Images of all snake movements.
    ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
    ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
    ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
    ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
    ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
    ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
    ImageIcon food = new ImageIcon(getClass().getResource("enemy.png"));



    // at starting snake face is towards right so isRight is true and every other is false
    // GameOver is false because at starting we suppose that the game is not start when press a key then it starts
    boolean isUp = false;
    boolean isDown = false;
    boolean isRight = true;
    boolean isLeft = false;
    boolean GameOver = false;


    // position of snake. where snake moves
    int[] snakeX = new int[750];
    int[] snakeY = new int[750];
    int move = 0;
    int lengthOfSnake = 3;  // at starting length of snake is 3.
    Timer time; // it is work as a Thread.timer to show the snake movements.
    int Score = 0;



    // position of food
    int[] xpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    int[] ypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};


    // get random value(position of food) from anything.
    Random random = new Random();
    int foodx = 150;
    int foody = 150;


    MyPanel()
    {
        addKeyListener(this);   // if any key(up, down, left, right is pressed then it go to the override keyPressed method.
        setFocusable(true);
        time = new Timer(150, this); // delay timer to show snake movement.
        time.start();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // making 2 rectangles with white colour
        g.setColor(Color.white);
        // one for title bar
        g.drawRect(24, 10, 851, 55);
        // one for game panel
        g.drawRect(24, 74, 851, 576);
        // Inage of the title bar
        snaketitle.paintIcon(this, g, 25, 11);

        // game panel fill with black colour
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        // starting position of snake from x-axis and y-axis.
        if(move == 0)
        {
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;

            snakeY[0] = 100;
            snakeY[1] = 100;
            snakeY[2] = 100;
        }

        // print the mouth of the snake according the movement of the snake.
        if(isRight)
            rightmouth.paintIcon(this, g, snakeX[0], snakeY[0]);
        if(isDown)
            downmouth.paintIcon(this,g, snakeX[0], snakeY[0]);
        if(isLeft)
            leftmouth.paintIcon(this, g, snakeX[0], snakeY[0]);
        if(isUp)
            upmouth.paintIcon(this, g, snakeX[0], snakeY[0]);

        // print the remaining portion(body) of the snake at the starting
        for(int i=1; i<lengthOfSnake; i++)
        {
            snakeimage.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // print the food of the snake.
        food.paintIcon(this, g, foodx, foody);

        // print the message when game is over.
        if(GameOver)
        {
            g.setColor(Color.white);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            g.drawString("Game Over", 300, 300);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
            g.drawString("Press the Space Key to restart the game", 330, 360);
        }

        // print the score and length of snake in the title bar
        g.setColor(Color.white);
        g.setFont(new Font("ITALIC", Font.PLAIN, 15));
        g.drawString("Score : " + Score, 750, 30);
        g.drawString("Length : " + lengthOfSnake, 750, 50);
        g.dispose();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    // method start working when movement key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        // e.getKeyCode() store the key value which was pressed.
        if(e.getKeyCode() == KeyEvent.VK_SPACE && GameOver)
        {
            restart();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && (!isLeft))
        {
            isUp = false;
            isLeft = false;
            isDown = false;
            isRight = true;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT && (!isRight))
        {
            isUp = false;
            isLeft = true;
            isDown = false;
            isRight = false;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && (!isDown))
        {
            isUp = true;
            isLeft = false;
            isDown = false;
            isRight = false;
            move++;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && (!isUp))
        {
            isUp = false;
            isLeft = false;
            isDown = true;
            isRight = false;
            move++;
        }

    }

    // in case when we restart the game.
    private void restart() {
        GameOver = false;
        Score = 0;
        move = 0;
        lengthOfSnake = 3;
        isLeft = false;
        isUp = false;
        isDown = false;
        isRight = true;
        time.start();
        newfood();
        repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    // snake movement when it moves in any direction.
    @Override
    public void actionPerformed(ActionEvent e) {
        // creation of snake body when snake is moving
        for(int i=lengthOfSnake-1; i>0; i--)
        {
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }

        // when snake move in any direction then what happens
        if(isLeft)
            snakeX[0] = snakeX[0] - 25;
        if(isRight)
            snakeX[0] = snakeX[0] + 25;
        if(isDown)
            snakeY[0] = snakeY[0] + 25;
        if(isUp)
            snakeY[0] = snakeY[0] - 25;

        // when snake disappear any side of the panel then it appear from opposite side of the panel
        if(snakeX[0]>850)   snakeX[0] = 25;
        if(snakeX[0]<25)    snakeX[0] = 850;
        if(snakeY[0]>625)   snakeY[0] = 75;
        if(snakeY[0]<75)    snakeY[0] = 625;

        CollisionWithfood();
        CollisionWithbody();

        repaint();
    }

    private void CollisionWithbody() {
        for(int i=lengthOfSnake-1; i>0; i--)
        {
            if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0])
            {
                time.stop();
                GameOver = true;
            }
        }
    }

    // when snake collide with food
    private void CollisionWithfood() {
        if(snakeX[0] == foodx && snakeY[0] == foody)
        {
            newfood();
            lengthOfSnake++;
            Score++;
            // make the snake length increase. by line 250 and 251.
            snakeX[lengthOfSnake-1] = snakeX[lengthOfSnake-2];
            snakeY[lengthOfSnake-1] = snakeY[lengthOfSnake-2];
        }
    }

    // creation of food.
    private void newfood() {
        // random is used to place the food at random places
        foodx = xpos[random.nextInt(xpos.length-1)];
        foody = ypos[random.nextInt(ypos.length-1)];

        // this for loop is used to not produce the food on the snake body like position of the snake not be the food position.
        for(int i=lengthOfSnake-1; i>0; i--)
        {
            if(snakeX[i] == foodx && snakeY[i] == foody)    // like if position of snake is equal to food position then we need to produce new food.
            {
                newfood();
            }
        }
    }
}

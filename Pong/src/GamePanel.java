import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Pong
 * Author: Peter Mitchell (2021)
 *
 * GamePanel class:
 * Creates the game space with two paddles and a ball.
 */
public class GamePanel extends JPanel {
    /**
     * Reference to the game object for passing score updates.
     */
    private Game game;
    /**
     * Paddles representing the player elements.
     */
    private Paddle leftPaddle, rightPaddle;
    /**
     * Ball that will bounce around the screen.
     */
    private Ball ball;
    /**
     * Score for each player.
     */
    private int leftScore, rightScore;

    /**
     * Initialises the game space by creating both paddles and the ball.
     *
     * @param gameWidth Width of the game space.
     * @param gameHeight Height of the game space.
     * @param game Reference to the game space.
     */
    public GamePanel(int gameWidth, int gameHeight, Game game) {
        this.game = game;
        setPreferredSize(new Dimension(gameWidth,gameHeight));
        setBackground(Color.BLACK);

        leftPaddle = new Paddle(true, gameWidth, gameHeight, KeyEvent.VK_W, KeyEvent.VK_S);
        rightPaddle = new Paddle(false, gameWidth, gameHeight, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ball = new Ball(gameWidth, gameHeight, leftPaddle, rightPaddle, this);
        leftScore = 0;
        rightScore = 0;
    }

    /**
     * Updates state information for the left paddle, right paddle, and the ball.
     * Then performs a repaint() to force redrawing using changes.
     */
    public void update() {
        leftPaddle.update();
        rightPaddle.update();
        ball.update();
        repaint();
    }

    /**
     * Passes the input to both paddles to let them handle individually swapping input states.
     *
     * @param keyCode A KeyEvent code for the key pressed or released.
     * @param isKeyPressed Key state of keyCode.
     */
    public void processInput(int keyCode, boolean isKeyPressed) {
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        leftPaddle.processInput(keyCode, isKeyPressed);
        rightPaddle.processInput(keyCode, isKeyPressed);
    }

    /**
     * Draws the left/right paddles and the ball.
     *
     * @param g Reference to the Graphics object for drawing.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        leftPaddle.paint(g);
        rightPaddle.paint(g);
        ball.paint(g);
    }

    /**
     * Updates the score of left or right player and passes the updated score
     * to the Game so that it can be shown on the score panel.
     *
     * @param isLeft The player whose score should be updated.
     */
    public void increaseScore(boolean isLeft) {
        if(isLeft) {
            leftScore++;
            game.setScore(leftScore, true);
        } else {
            rightScore++;
            game.setScore(rightScore, false);
        }
    }
}

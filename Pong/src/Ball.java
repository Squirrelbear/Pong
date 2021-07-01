import java.awt.*;
import java.util.Random;

/**
 * Pong
 * Author: Peter Mitchell (2021)
 *
 * Ball class:
 * Defines a single Ball that will bounce around the game space and
 * handles collision events with the walls and paddles.
 */
public class Ball extends CollidableRect {
    /**
     * Size in pixels of the ball.
     */
    private static final int BALL_SIZE = 20;
    /**
     * Reference to the GamePanel object for passing score changes.
     */
    private GamePanel gamePanel;
    /**
     * The translation vector for how much to move x and y during each update.
     */
    private Position moveVector;
    /**
     * The start position used for resetting the start position after the ball hits left/right walls.
     */
    private Position startPos;
    /**
     * Shared reference to Random object.
     */
    private Random rand;
    /**
     * Reference to the left/right paddles to test collisions against.
     */
    private Paddle leftPaddle, rightPaddle;

    /**
     * Creates a Ball in the middle of the gameplay area with default settings.
     * Then assigns a random move vector to start moving randomly.
     *
     * @param gameWidth Width of the game play area.
     * @param gameHeight Height of the game play area.
     * @param leftPaddle Reference to the left paddle.
     * @param rightPaddle Reference to the right paddle.
     * @param gamePanel Reference to the gamePanel.
     */
    public Ball(int gameWidth, int gameHeight, Paddle leftPaddle, Paddle rightPaddle, GamePanel gamePanel) {
        super(new Position(gameWidth/2, gameHeight/2), BALL_SIZE, BALL_SIZE, Color.WHITE, gameWidth, gameHeight);
        this.gamePanel = gamePanel;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        rand = new Random();
        setRandomMoveVector();
        startPos=new Position(gameWidth/2, gameHeight/2);
    }

    /**
     * Moves the ball by the amount defined in moveVector.
     * Collisions are checked with the walls and paddles.
     * Hitting a paddle without passing too far into it will bounce the ball.
     * Hitting the top wall will bounce the ball.
     * Hitting a back wall will apply scores to the opposite paddle and reset the ball.
     */
    public void update() {
        moveWithinBounds(moveVector);
        if(hitYEdge()) moveVector.y = -moveVector.y;
        if(hitXEdge()) {
            applyScoreAndReset();
        } else if(isCollidingWith(leftPaddle)) {
            int rightSide = leftPaddle.position.x+leftPaddle.width;
            if(position.x-2*moveVector.x>rightSide) {
                position.x = rightSide;
                moveVector.x = -moveVector.x;
            }
        } else if(isCollidingWith(rightPaddle)) {
            if(position.x+BALL_SIZE-2*moveVector.x<rightPaddle.position.x) {
                position.x = rightPaddle.position.x - width;
                moveVector.x = -moveVector.x;
            }
        }
    }

    /**
     * Test if a left/right wall is hit.
     *
     * @return True if the left/right wall is hit.
     */
    public boolean hitXEdge() {
        return (position.x == 0 || position.x == maxX);
    }

    /**
     * Test if a top/bottom wall is hit.
     *
     * @return True if the top/bottom wall is hit.
     */
    public boolean hitYEdge() {
        return (position.y == 0 || position.y == maxY);
    }

    /**
     * Update the score in gamePanel for the opposite paddle by 1.
     * Reset the ball to the middle. Then give the ball a new random move vector.
     */
    private void applyScoreAndReset() {
        gamePanel.increaseScore(position.x != 0);
        position.setPosition(startPos.x, startPos.y);
        setRandomMoveVector();
    }

    /**
     * Generate a new random move vector that can have a random positive/negative,
     * and random magnitude from 2 to 6 for both x, and y directions.
     */
    private void setRandomMoveVector() {
        boolean xPositive = rand.nextBoolean();
        boolean yPositive = rand.nextBoolean();
        int xMagnitude = rand.nextInt(5)+2;
        int yMagnitude = rand.nextInt(5)+2;
        moveVector = new Position(xPositive?xMagnitude:-xMagnitude,yPositive?yMagnitude:-yMagnitude);
    }
}

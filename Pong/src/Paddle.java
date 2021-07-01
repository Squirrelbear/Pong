import java.awt.*;

/**
 * Pong
 * Author: Peter Mitchell (2021)
 *
 * Paddle class:
 * Defines a single Paddle with movement handling to move up and down.
 */
public class Paddle extends  CollidableRect{
    /**
     * The keyCodes defined with KeyEvent.VK_xxxx where xxxx is the key name.
     */
    private int keyUp, keyDown;
    /**
     * Status of the keys for up and down to determine if movement should happen during updates.
     */
    private boolean keyUpIsPressed, keyDownIsPressed;
    /**
     * Padding between the paddle and the wall in pixels.
     */
    private static final int offsetFromWall = 30;
    /**
     * Height in pixels of the paddle.
     */
    private static final int paddleHeight = 80;
    /**
     * Width in pixels of the paddle.
     */
    private static final int paddleWidth = 20;
    /**
     * The magnitude of movement translation for up/down movement.
     */
    private final int moveRate = 10;

    /**
     * Creates a paddle on either left or right side with correct size and centres vertically.
     *
     * @param isLeftPaddle Used to align the position of the paddle on either left or right of the game space.
     * @param gameWidth Width of the panel for gameplay.
     * @param gameHeight Height of the panel for gameplay.
     * @param keyUp The key used to move this paddle up.
     * @param keyDown The key used to move this paddle down.
     */
    public Paddle(boolean isLeftPaddle, int gameWidth, int gameHeight, int keyUp, int keyDown) {
        super(new Position(isLeftPaddle ? (offsetFromWall) : (gameWidth - paddleWidth - offsetFromWall),
                gameHeight / 2 - paddleHeight / 2),
                paddleWidth, paddleHeight, Color.WHITE, gameWidth, gameHeight);
        this.keyDown = keyDown;
        this.keyUp = keyUp;
        keyUpIsPressed = false;
        keyDownIsPressed = false;
    }

    /**
     * Moves within the bounds of the gameplay space if either movement key is currently held.
     */
    public void update() {
        if(keyDownIsPressed) {
            moveWithinBounds(new Position(0,moveRate));
        }
        if(keyUpIsPressed) {
            moveWithinBounds(new Position(0,-moveRate));
        }
    }

    /**
     * Takes an update state about an input. If it is either of the keys used for
     * this paddle the state will be updated.
     *
     * @param keyCode The key to test against keyUp and keyDown.
     * @param isKeyPressed State of the key.
     */
    public void processInput(int keyCode, boolean isKeyPressed) {
        if(keyUp == keyCode) {
            keyUpIsPressed = isKeyPressed;
        } else if(keyDown == keyCode) {
            keyDownIsPressed = isKeyPressed;
        }
    }
}

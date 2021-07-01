import java.awt.*;

/**
 * Pong
 * Author: Peter Mitchell (2021)
 *
 * CollidableRect class:
 * Used to represent an object with x,y,width,height.
 * With a colour for the rect to render it, and
 * methods to test for collisions with other CollidableRects.
 */
public class CollidableRect {
    /**
     * Top left corner x,y position of the rect.
     */
    protected Position position;
    /**
     * Width and height of the rect.
     */
    protected int width, height;
    /**
     * Used to constrain the coordinates for moveWithinBounds().
     */
    protected int maxX, maxY;
    /**
     * Colour used to render the rect.
     */
    private Color color;

    /**
     * Creates a simple CollidableRect. Uses gameWidth and gameHeight to calculate
     * maxX and maxY based on width and height.
     *
     * @param startPos The position to start the object at.
     * @param width The width of the object.
     * @param height The height of the object.
     * @param rectColor The colour to render the rect with.
     * @param gameWidth  The width of the playable area. Used to calculate max X coordinate.
     * @param gameHeight The height of the playable area. Used to calculate max Y coordinate.
     */
    public CollidableRect(Position startPos, int width, int height, Color rectColor, int gameWidth, int gameHeight) {
        this.position = startPos;
        this.width = width;
        this.height = height;
        this.color = rectColor;
        maxX = gameWidth - width;
        maxY = gameHeight - height;
    }

    /**
     * Moves based on the translationVector, but clamps the movement within the bounds of the play space.
     *
     * @param translationVector Added to position to calculate the new position.
     */
    public void moveWithinBounds(Position translationVector) {
        int newX = position.x+translationVector.x;
        int newY = position.y+translationVector.y;
        if(newX < 0) newX = 0;
        else if(newX > maxX) newX = maxX;
        if(newY < 0) newY = 0;
        else if(newY > maxY) newY = maxY;
        position.setPosition(newX, newY);
    }

    /**
     * Moves by the amount defined in translationVector with no consideration for where it is moving to.
     *
     * @param translationVector Added to position to calculate the new position.
     */
    public void move(Position translationVector) {
        position.setPosition(position.x+translationVector.x, position.y+translationVector.y);
    }

    /**
     * Tests for edges intersecting the other object. If any edge is somewhere making it impossible for
     * an intersection the collision is not happening.
     *
     * @param other The other object to compare against for a collision.
     * @return True any part of this object is colliding with the other.
     */
    public boolean isCollidingWith(CollidableRect other) {
        // break if any of the following are true because it means they don't intersect
        if(position.y + height < other.position.y) return false;
        if(position.y > other.position.y + other.height) return false;
        if(position.x + width < other.position.x) return false;
        if(position.x > other.position.x + other.width) return false;

        // the bounding boxes do intersect
        return true;
    }

    /**
     * Draws the rectangle.
     *
     * @param g Reference to the  Graphics object for drawing.
     */
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, width, height);
    }
}

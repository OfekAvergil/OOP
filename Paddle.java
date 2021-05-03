//316279702

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 24/04/2021
 */
public class Paddle implements Sprite, Collidable {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BORDER_SIZE = 5;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;

    /**
     * Constructor.
     * @param keyboard - biuoop.KeyboardSensor Object
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        int width = 100;
        int height = 20;
        Point upperLeft = new Point(WINDOW_WIDTH / 2 - width / 2, WINDOW_HEIGHT - height - BORDER_SIZE);
        shape = new Rectangle(upperLeft, width, height);
    }

    /**
     * Constructor.
     * @param keyboard - biuoop.KeyboardSensor Object.
     * @param width - the paddle's width
     * @param height - the paddle's height
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int height) {
        this.keyboard = keyboard;
        Point upperLeft = new Point(WINDOW_WIDTH / 2 - width / 2, WINDOW_HEIGHT - height - BORDER_SIZE);
        shape = new Rectangle(upperLeft, width, height);
    }

    /**
     * moves the paddle one move to the left.
     */
    public void moveLeft() {
        Point newUp = new Point(this.shape.getUpperLeft().getX() - BORDER_SIZE, this.shape.getUpperLeft().getY());
        this.shape = new Rectangle(newUp, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * moves the paddle one move to the right.
     */
    public void moveRight() {
        Point newUp = new Point(this.shape.getUpperLeft().getX() + BORDER_SIZE, this.shape.getUpperLeft().getY());
        this.shape = new Rectangle(newUp, this.shape.getWidth(), this.shape.getHeight());
    }

    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.shape.getUpperLeft().getX() > BORDER_SIZE) {
                this.moveLeft();
            }
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.shape.getUpperLeft().getX() < WINDOW_WIDTH - BORDER_SIZE - this.shape.getWidth()) {
                this.moveRight();
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.shape.drawOn(d, Color.orange);
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        double width = this.shape.getWidth() / 5;
        double startX = this.shape.getUpperLeft().getX();
        double hitX = collisionPoint.getX();
        //section 1
        if (hitX < startX + width) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        //section 2
        if (hitX > startX + width && hitX < startX + 2 * width) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        //section 3
        if (hitX > startX + 2 * width && hitX < startX + 3 * width) {
            currentVelocity.dyDirection();
            return currentVelocity;
        }
        //section 4
        if (hitX > startX + 3 * width && hitX < startX + 4 * width) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        //section 5
        return Velocity.fromAngleAndSpeed(60, speed);

    }

    /**
     * adds the paddle to the collidable and the sprites lists.
     * @param g - Game object
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}

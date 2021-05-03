//316279702

import biuoop.DrawSurface;
import java.awt.*;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class Block implements Collidable, Sprite {
    private Rectangle collisionRectangle;
    private java.awt.Color color;

    // constructors
    /**
     * constructor.
     * @param r - Rectangle object
     * @param color - java.awt.Color object
     */
    public Block(Rectangle r, java.awt.Color color) {
        this.collisionRectangle = r;
        this.color = color;
    }
    /**
     * constructor.
     * @param r - Rectangle object
     */
    public Block(Rectangle r) {
        this.collisionRectangle = r;
        this.color = Color.BLUE;
    }
    /**
     * Constructor.
     * @param start - The rectangle's start point
     * @param width - the rectangle's width
     * @param height the rectangle's height
     */
    public Block(Point start, double width, double height) {
        this.collisionRectangle = new Rectangle(start, width, height);
        this.color = Color.black;
    }
    // accessors
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }
    /**
     * change the block color.
     * @param c - java.awt.color object
     */
    public void setColor(Color c) {
        this.color = c;
    }

    // Collidable
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double y = collisionPoint.getY();
        double x = collisionPoint.getX();
        Velocity finalV = currentVelocity;
        Line[] lines = this.collisionRectangle.rectangleLines();
        //if the collision point is on top or bottom
        if (lines[0].isInLine(collisionPoint) || lines[2].isInLine(collisionPoint)) {
            finalV.dyDirection();
        }
        //if the collision point is on the sides
        if (lines[1].isInLine(collisionPoint) || lines[3].isInLine(collisionPoint))  {
            finalV.dxDirection();
        }
        //id the collision is not on the rectangle, don't change velocity.
        return finalV;
    }

    // Sprite
    @Override
    public void drawOn(DrawSurface d) {
        this.collisionRectangle.drawOn(d, this.color);
    }
    @Override
    public void timePassed() { };

    /**
     * adds this block ball to the game's Sprites and Coolidables lists.
     * @param g -Game object
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}

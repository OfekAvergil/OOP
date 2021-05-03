//316279702

import biuoop.DrawSurface;

import java.awt.*;
/**
 * @author ofek avergil
 * @version 1.0
 * @since 09/04/2021
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment g;

    // constructors
    /**
     * constructor.
     * @param center - Point object
     * @param r - int
     * @param color -  java.awt.Color object
     * @param g - GameEnviroment object.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.g = g;
    }

    /**
     * constructor.
     * @param center - Point object
     * @param r - int
     * @param color -  java.awt.Color object
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
    }

    /**
     * constructor.
     * @param x - x coordinate
     * @param y - y coordinate
     * @param r - int
     * @param color -  java.awt.Color object
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor.
     * @param x - x coordinate in int
     * @param y - y coordinate in int
     * @param r - int
     * @param color -  java.awt.Color object
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    // accessors

    /**
     * returns the circle's center point.
     * @return - Point object
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * returns the ball's center x value.
     * @return x value in int
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * returns the ball's center y value.
     * @return y value in int
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * return the ball's radius.
     * @return radius in int
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * return the ball's color.
     * @return color in java.awt.Color
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * returns the ball's velocity.
     * @return Velocity object.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * change the center point of the ball.
     * @param c - Point object
     */
    public void setCenter(Point c) {
        this.center = c;
    }
    /**
     * change the radius of the ball.
     * @param r - int
     */
    public void setRadius(int r) {
        this.radius = r;
    }
    /**
     * change the color of the ball.
     * @param c - java.awt.color object
     */
    public void setColor(Color c) {
        this.color = c;
    }
    /**
     * set the GameEnvironment of the ball.
     * @param gE - GameEnvironment object.
     */
    public void setGameEnvironment(GameEnvironment gE) {
        this.g = gE;
    }
    /**
     * set ball's velocity field.
     * @param v - Velocity object
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * sets ball's velocity field.
     * @param dx - in double
     * @param dy - in double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * changes the ball's center according it's velocity.
     */
    public void moveOneStep() {
        Point p1 = this.getVelocity().applyToPoint(this.center);
        Line l1 = new Line(this.center, p1);
        CollisionInfo info = this.g.getClosestCollision(l1);
        if (info != null) {
            Point newCenter = stopBeforeHit(info.collisionPoint());
            this.center = newCenter;
            this.velocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * return the closest Point from the ball to the hit Point.
     * @param hit - Point object
     * @return the Point
     */
    private Point stopBeforeHit(Point hit) {
        double x = hit.getX();
        double y = hit.getY();
        if (this.center.getX() > hit.getX()) {
            x = hit.getX() + this.radius + 1;
        } else if (this.center.getX() < hit.getX()) {
            x = hit.getX() - this.radius - 1;
        }
        if (this.center.getY() > hit.getY()) {
            y = hit.getY() + this.radius + 1;
        } else if (this.center.getY() < hit.getY()) {
            y = hit.getY() - this.radius - 1;
        }
        return new Point(x, y);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * adds the ball to the game's Sprites list.
     * @param game -Game object
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
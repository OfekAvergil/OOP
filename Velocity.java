//316279702

/**
 * @author ofek avergil
 * @version 1.0
 * @since 14/03/2021
 */
public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * constructor.
     */
    public Velocity() {
        this.dx = 0;
        this.dy = 0;
    }
    /**
     * constructor.
     * @param dx - in double
     * @param dy - in double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * return the dx value.
     * @return dx in double
     */
    public double getDx() {
        return dx;
    }
    /**
     * return the dy value.
     * @return dy in double
     */
    public double getDy() {
        return dy;
    }

    /**
     * changing the dx value of this velocity.
     * @param vDx - double
     */
    public void setDx(double vDx) {
        this.dx = vDx;
    }

    /**
     * changing the dy value of this velocity.
     * @param vDy - double
     */
    public void setDy(double vDy) {
        this.dy = vDy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - Point object
     * @return Point object
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }

    /**
     * creating new Velocity instance from speed and angle.
     * @param angle - double
     * @param speed - double
     * @return new Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(angle / 180 * Math.PI);
        double dy = speed * Math.cos(angle / 180 * Math.PI);
        return new Velocity(dx, -dy);
    }
    /**
     * changes the dx direction to the other side.
     */
    public void dxDirection() {
        this.dx = this.dx * (-1);
    }
    /**
     * changes the dy direction to the other side.
     */
    public void dyDirection() {
        this.dy = this.dy * (-1);
    }
}

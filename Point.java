//316279702
/**
 * @author ofek avergil
 * @version 1.0
 * @since 09/04/2021
 */

public class Point  {
    private static final double EPSILON = Math.pow(10, -4);
    private double x;
    private double y;

    /**
     * constructor.
     * @param x - the X coordinate
     * @param y - the Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor.
     * @param x - the X coordinate
     * @param y - the Y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance of this point to the other point.
     * @param other - a Point object
     * @return the distance in double.
     */
    public double distance(Point other) {
        double dist =  ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        dist = Math.sqrt(dist);
        return dist;
    }

    /**
     * checks if two points are equal.
     * @param other - a Point object
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return !(Math.abs(this.x - other.x) > EPSILON) && !(Math.abs(this.y - other.y) > EPSILON);
    }

    /**
     * returns the x value of this point.
     * @return x value in double
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the y value of this point.
     * @return y value in double
     */
    public double getY() {
        return this.y;
    }

}

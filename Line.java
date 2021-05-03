//316279702
import java.util.*;
/**
 * @author ofek avergil
 * @version 1.0
 * @since 09/04/2021
 */

public class Line {
    private static final double EPSILON = Math.pow(10, -4);
    private Point start;
    private Point end;
    private boolean isVertical;
    private double m;

    // constructors

    /**
     * constructor.
     * @param start - Point object
     * @param end - Point object
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() == end.getX()) {
            this.isVertical = true;
            this.m = 0;
        } else {
            this.isVertical = false;
            m = (end.getY() - start.getY()) / (end.getX() - start.getX());
        }
    }

    /**
     * constructor.
     * @param x1 - x value (in double) of the start point.
     * @param y1 - y value (in double) of the start point.
     * @param x2 - x value (in double) of the end point.
     * @param y2 - y value (in double) of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (x1 == x2) {
            this.isVertical = true;
            this.m = 0;
        } else {
            this.isVertical = false;
            m = (end.getY() - start.getY()) / (end.getX() - start.getX());
        }
    }

    /**
     * constructor.
     * @param x1 - x value (in int) of the start point.
     * @param y1 - y value (in int) of the start point.
     * @param x2 - x value (in int) of the end point.
     * @param y2 - y value (in int) of the end point.
     */
    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (x1 == x2) {
            this.isVertical = true;
            this.m = 0;
        } else {
            this.isVertical = false;
            m = (end.getY() - start.getY()) / (end.getX() - start.getX());
        }
    }

    // accessors
    /**
     * returns the start point of this line.
     * @return Point object
     */
    public Point start() {
        return this.start;
    }
    /**
     * returns the end point of this line.
     * @return Point object
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate the length of this line.
     * @return length in double
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * finds the middle point of this line.
     * @return Point object
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }


    /**
     * checks if this line intersect with other line.
     * @param other - Point object
     * @return true if the lines intersect, false otherwise
     * */
    public boolean isIntersecting(Line other) {
        //if the lines are not parallels
        if (this.isVertical && other.isVertical) {
            if (this.start.getX() != other.start.getX()) {
                return false;
            }
            return this.isUnite(other);
        }
        if (this.m != other.m || this.isVertical || other.isVertical) {
            Point p1 = findIntersection(other);
            return p1 != null;
        }
        //if the lines are parallels
        return this.isUnite(other);
    }

    /**
     * checks if this line is unites with other line.
     * @param other - Line object
     * @return - true if they unite, false otherwise
     */
    private boolean isUnite(Line other) {
        if (this.equals(other)) {
            return true;
        }
        double x1 = Math.min(this.start.getX(), this.end.getX());
        double x2 = Math.min(other.start.getX(), other.end.getX());
        double x = Math.min(x1, x2);
        //in case this line start first
        if (x == this.start.getX() || x == this.end.getX()) {
            //if the other line starts or end on this line
            return this.isInLine(other.start) || this.isInLine(other.end);
        } else {
            //if this line starts or end on the other line
            return other.isInLine(this.start) || other.isInLine(this.end);
        }
    }

    /**
     * finds the intersection point of this line with other line.
     * @param other - Point object
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.m == other.m) {
            //if one of them vertical, I put it's m as 0.
            if (this.isVertical || other.isVertical) {
                return this.findIntersection(other);
            }
            //if they are not meeting or they unite return null
            return null;
        }
        return this.findIntersection(other);
    }

    /**
     * calculate the intersection point.
     * @param other - Line object
     * @return the intersection point
     */
    public Point findIntersection(Line other) {
        //if they meet at the edges
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        double x1 = this.end.getX();
        double y1 = this.end.getY();
        double x2 = other.end.getX();
        double y2 = other.end.getY();
        //checks if one of the lines is vertical
        if (this.isVertical) {
            double y = y2 + other.m * x1 - other.m * x2;
            Point p1 = new Point(x1, y);
            if (this.isInLine(p1) && other.isInLine(p1)) {
                return p1;
            }
            return null;
        } else if (other.isVertical) {
            double y = y1 + this.m * x2 - this.m * x1;
            Point p1 = new Point(x2, y);
            if (other.isInLine(p1)) {
                return p1;
            }
            return null;
        } else {
            //calculate the point
            double x = (other.m * x2 - this.m * x1 + y1 - y2) / (other.m - this.m);
            double y = this.m * x - this.m * x1 + y1;
            Point inter = new Point(x, y);
            //checks if the intersection point is in the lines range
            if (this.isInLine(inter) && other.isInLine(inter)) {
                return inter;
            }
        }
        return null;
    }


    /**
     * checks if a point is located on this line.
     * @param p - Point object (not null)
     * @return true if the point is found on the line, false otherwise
     */
    public boolean isInLine(Point p) {
        //if the point is on the edges of the line.
        if (this.isVertical) {
            if (this.start.getX() != p.getX()) {
                return false;
            }
            double yMax = Math.max(this.start.getY(), this.end.getY());
            double yMin = Math.min(this.start.getY(), this.end.getY());
            return !(p.getY() < yMin) && !(p.getY() > yMax);
        }
        //checks if the x value is in the right range
        double xMin = Math.min(this.start.getX(), this.end.getX());
        double xMax = Math.max(this.start.getX(), this.end.getX());
        if (xMin > p.getX() || p.getX() > xMax) {
            return false;
        }
        //check if the point match the line equation
        double y = this.m * p.getX() - this.m * this.start.getX() + this.start.getY();
        return Math.abs(p.getY() - y) <= EPSILON;
    }

    /**
     * checks if given line is equal to this line.
     * @param other - Line object
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.end.equals(other.start) && this.start.equals(other.end);
    }
    /**
     * return the closest intersection point to the start of the line.
     * @param rect - the rectangle
     * @return null if this line does not intersect with the rectangle, the Point otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double x;
        List l1 = rect.intersectionPoints(this);
        if (l1.isEmpty()) {
            return null;
        }
        //sorting the Points list
        Comparator<Point> comparator = new PointComparator();
        l1.sort(comparator);
        //return the closes point to the start point of the line
        if (this.start.getX() > this.end.getY()) {
            return (Point) l1.get(l1.size() - 1);
        } else {
            return (Point) l1.get(0);
        }
    }
}


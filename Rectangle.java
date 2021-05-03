//316279702

//import java.awt.*;
import biuoop.DrawSurface;
import java.util.*;
import java.util.List;

import static java.awt.Color.*;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point[] pointsArr;

    /**
     * Constructor.
     * @param upperLeft - starting point. Point object
     * @param width - rectangle's width in double
     * @param height - rectangle's height in double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.pointsArr = new Point[4];

        //calculate the rest of the rectangle's points
        double oldX = upperLeft.getX();
        double oldY = upperLeft.getY();
        double newX = upperLeft.getX() + width;
        double newY = upperLeft.getY() + height;

        this.pointsArr[0] = upperLeft;
        //upperRight
        this.pointsArr[1] = new Point(newX, oldY);
        //lowerLeft
        this.pointsArr[2] = new Point(oldX, newY);
        //lower right
        this.pointsArr[3] = new Point(newX, newY);
    }

    // accessors
    /**
     * Return the width of the rectangle.
     * @return width in double
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Return the height of the rectangle.
     * @return height in double
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of the rectangle.
     * @return starting point in Point object
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the points array of the rectangle.
     * @return array of Points
     */
    public Point[] getPoints() {
        return this.pointsArr;
    }

    /**
     * calculate the rectangle's lines.
     * @return Line array
     */
    public Line[] rectangleLines() {
        Line[] lines = new Line[4];
        lines[0] = new Line(this.pointsArr[0], this.pointsArr[1]);
        lines[1] = new Line(this.pointsArr[0], this.pointsArr[2]);
        lines[2] = new Line(this.pointsArr[2], this.pointsArr[3]);
        lines[3] = new Line(this.pointsArr[1], this.pointsArr[3]);
        return lines;
    }

    /**
     * finds the intersection point with the specified line.
     * @param line - Line object
     * @return possibly empty list of the intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        int i;
        Line[] lines = rectangleLines();
        List inters = new ArrayList<Point>();
        for (i = 0; i < 3; i++) {
            if (lines[i].isIntersecting(line)) {
                Point p1 = lines[i].findIntersection(line);
                if (p1 != null) {
                    inters.add(p1);
                }
            }
        }
        return inters;
    }

    /**
     * draw rectangle on the gui.
     * @param d - DrawSurface object
     * @param color - java.awt.Color object
     */
    public void drawOn(DrawSurface d, java.awt.Color color) {
        int x = (int) this.upperLeft.getX();
        int y = (int) this.upperLeft.getY();
        int recWidth = (int) this.getWidth();
        int recHeight = (int) this.getHeight();
        d.setColor(BLACK);
        d.drawRectangle(x, y, recWidth, recHeight);
        d.setColor(color);
        d.fillRectangle(x + 1, y + 1, recWidth - 1, recHeight - 1);
    }

}
//316279702

import java.util.Comparator;
/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class PointComparator implements Comparator<Point> {
    private static final int BIGGER = 1;
    private static final int SMALLER = -1;
    private static final int EVEN = 0;

    /**
     * compare between two point's x value.
     * @param c1 - Point object
     * @param c2 - Point object
     * @return 1 if c1's x value is bigger, -1 if c2's x value is bigger, 0 if equal
     */
    public int compare(Point c1, Point c2) {
        if (c1.getX() > c2.getX()) {
            return BIGGER;
        }
        if (c2.getX() > c1.getX()) {
            return SMALLER;
        }
        return EVEN;
    }
}

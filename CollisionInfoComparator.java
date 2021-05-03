//316279702

import java.util.Comparator;
/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class CollisionInfoComparator implements Comparator<CollisionInfo> {
    @Override
    public int compare(CollisionInfo o1, CollisionInfo o2) {
        Comparator<Point> comparator = new PointComparator();
        return comparator.compare(o1.collisionPoint(), (o2.collisionPoint()));
    }
}

//316279702

/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    // Constructors
    /**
     * Constructor.
     * @param p - point object
     * @param c - Collidable object
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionObject = c;
        this.collisionPoint = p;
    }

    // accessors
    /**
     * return the point at which the coolsion occurs.
     * @return Point object
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * return the collidable object involved in the collision.
     * @return Collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

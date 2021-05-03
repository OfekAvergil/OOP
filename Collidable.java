//316279702

/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return Rectangle object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint - Point object
     * @param currentVelocity - Velocity object
     * @return new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}

//316279702

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 21/04/2021
 */
public class GameEnvironment {
    private ArrayList<Collidable> cList;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.cList = new ArrayList<Collidable>();
    }

    /**
     * adding a Collidable object to the GameEnviroment's list.
     * @param c - Collidable object
     */
    public void addCollidable(Collidable c) {
        this.cList.add(c);
    }
    /**
     * returning the array list of collidable objects.
     * @return ArrayList
     */
    public ArrayList<Collidable> getCList() {
        return this.cList;
    }
    /**
     * return the information about the closest collision that is going to occur.
     * @param trajectory - Line object
     * @return a collision point. if there isn't one return null.
     */
    //didn't find the closest to the start....
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList arr = new ArrayList<CollisionInfo>();
        int i;
        for (i = 0; i < this.cList.size(); i++) {
            Rectangle collided = (Rectangle) this.cList.get(i).getCollisionRectangle();
            Point p = trajectory.closestIntersectionToStartOfLine(collided);
            if (p != null) {
               arr.add(new CollisionInfo(p, this.cList.get(i)));
            }
        }
        if (arr.isEmpty()) {
            return null;
        }
        Comparator<CollisionInfo> comparator = new CollisionInfoComparator();
        arr.sort(comparator);
        if (trajectory.start().getX() > trajectory.end().getY()) {
            return (CollisionInfo) arr.get(arr.size() - 1);
        } else {
            return (CollisionInfo) arr.get(0);
        }
    }
}

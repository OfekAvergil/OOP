//316279702

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 24/04/2021
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * adding new Sprite object to the list.
     * @param s - Sprite object
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        int i;
        for (i = 0; i < this.spritesList.size(); i++) {
            this.spritesList.get(i).timePassed();
        }
    }

    /**
     * draws all sprites.
     * @param d - DrawSurface object
     */
    public void drawAllOn(DrawSurface d) {
        int i;
        for (i = 0; i < this.spritesList.size(); i++) {
            this.spritesList.get(i).drawOn(d);
        }
    }
}

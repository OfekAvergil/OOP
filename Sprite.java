//316279702

import biuoop.DrawSurface;
/**
 * @author ofek avergil
 * @version 1.0
 * @since 24/04/2021
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - DrawSurface object.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}

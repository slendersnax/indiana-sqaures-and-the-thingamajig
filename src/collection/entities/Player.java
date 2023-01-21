// Orban Abel, 621
package collection.entities;

import java.io.File;
import java.io.IOException;

public class Player extends Square {
    public int pulseDistance = 0;

    public Player(int width, int x, int y, File imgsrc) throws IOException {
        super(width, x, y, imgsrc);
    }

    public boolean collision(Square obj) {
        int beingLeft    = this.x;
        int beingTop     = this.y;
        int beingRight   = this.x + this.width;
        int beingBottom  = this.y + this.width;

        int objLeft    = obj.x;
        int objTop     = obj.y;
        int objRight   = obj.x + obj.width;
        int objBottom  = obj.y + obj.width;

        if(((beingLeft < objRight    && beingLeft >= objLeft) && (beingTop < objBottom     && beingTop >= objTop)) || /* upper left corner overlap */
            ((beingRight <= objRight && beingRight > objLeft) && (beingTop < objBottom     && beingTop >= objTop)) || /* upper right corner */
            ((beingLeft < objRight   && beingLeft >= objLeft) && (beingBottom <= objBottom && beingBottom > objTop)) || /* bottom left corner */
            ((beingRight <= objRight && beingRight > objLeft) && (beingBottom <= objBottom && beingBottom > objTop))) { /* bottom right corner */

            return true;
        }
        return false;
    }
}

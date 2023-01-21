// Orban Abel, 621
package collection.entities;

import java.io.IOException;
import java.io.File;

public class FinishSquare extends Square {
    public FinishSquare(int width, int x, int y, File imgsrc) throws IOException {
        super(width, x, y, imgsrc);
        this.type = 1;
    }
}

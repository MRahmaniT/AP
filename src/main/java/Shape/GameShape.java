package Shape;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public interface GameShape {

    void update();

    void draw(Graphics2D g2d);

    float getRadius();

    Path2D.Float getPath();
}

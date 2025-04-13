package Shape;

import java.awt.Graphics2D;

public interface GameShape {
    /**
     * Update the shape's state (e.g., position, rotation) every frame.
     */
    void update();

    /**
     * Draw the shape using the provided Graphics2D context.
     */
    void draw(Graphics2D g2d);
}

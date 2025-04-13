package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class HexagonShape implements GameShape {

    // Position of the hexagon’s center
    private float x, y;

    // Radius from center to each vertex
    private float radius;

    // Current rotation angle in degrees
    private float rotation;

    public HexagonShape(float x, float y, float radius, float rotation) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.rotation = rotation;  // start unrotated
    }

    @Override
    public void update() {
        // rotate rate
        rotation += 0.1f;

        if (rotation >= 360) {
            rotation -= 360;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Save the original transform
        var originalTransform = g2d.getTransform();

        // Translate and rotate the Graphics2D context
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(rotation));

        // Create a path for the hexagon
        Path2D.Float hexagon = new Path2D.Float();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            float vx = (float)(radius * Math.cos(angle));
            float vy = (float)(radius * Math.sin(angle));

            if (i == 0) {
                hexagon.moveTo(vx, vy);
            } else {
                hexagon.lineTo(vx, vy);
            }
        }
        hexagon.closePath();

        // Choose a color and draw
        g2d.setColor(Color.BLUE);
        g2d.fill(hexagon);

        // Restore the original transform so other drawings aren’t affected
        g2d.setTransform(originalTransform);
    }
}

package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class HexagonShape implements GameShape {

    // Position of the hexagon’s center
    private float x, y;

    // Radius from center to each vertex
    private float radius;

    // Current rotation angle in degrees
    private float rotation;
    private float rotationSpeed;

    public HexagonShape(float x, float y, float radius, float rotationSpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.rotation = 0f;
    }

    public void increaseRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = this.rotationSpeed + rotationSpeed;
    }

    @Override
    public void update() {
        // Rotation rate
        rotation += rotationSpeed;
        if (rotation >= 360f) {
            rotation -= 360f;
        }
        increaseRotationSpeed(0.001f);
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

    @Override
    public float getRadius() {
        return this.radius;
    }
}

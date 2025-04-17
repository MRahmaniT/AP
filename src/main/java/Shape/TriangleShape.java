package Shape;

import java.awt.*;
import java.awt.geom.Path2D;

public class TriangleShape implements GameShape {

    private Path2D.Float triangle;

    // Position of the hexagon’s center
    private float x, y;

    // Radius from center to each vertex
    private float radius;

    // Current rotation angle in degrees
    private float rotation;
    private float rotationSpeed;

    public TriangleShape(float x, float y, float radius, float rotationSpeed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.rotation = -30f;
    }

    public void setRadius(float radius) {
        this.radius = this.radius + radius;
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

        // Choose a color and draw
        g2d.setColor(Color.BLACK);
        g2d.fill(getPath());

        // Restore the original transform so other drawings aren’t affected
        g2d.setTransform(originalTransform);
    }

    @Override
    public void rotate(int i) {
        rotation += i*60;
        if (rotation >= 360f) {
            rotation -= 360f;
        }
    }

    @Override
    public float getRadius() {
        return this.radius;
    }

    @Override
    public Path2D.Float getPath() {
        Path2D.Float triangle = new Path2D.Float();

        double angle1 = Math.toRadians(-12 + rotation);
        float x1 = (float)(radius * Math.cos(angle1)) + x;
        float y1 = (float)(radius * Math.sin(angle1)) + y;

        double angle2 = Math.toRadians(0 + rotation);
        float x2 = (float)((radius + 8) * Math.cos(angle2)) + x;
        float y2 = (float)((radius + 8) * Math.sin(angle2)) + y;

        double angle3 = Math.toRadians(12 + rotation);
        float x3 = (float)(radius * Math.cos(angle3)) + x;
        float y3 = (float)(radius * Math.sin(angle3)) + y;

        triangle.moveTo(x1, y1);
        triangle.lineTo(x2, y2);
        triangle.lineTo(x3, y3);
        triangle.closePath();

        return triangle;
    }
}

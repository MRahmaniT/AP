package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class RotatingBackground implements GameShape {
    private float rotation;//in degrees
    private float rotationSpeed;
    private final float radius;

    private final Color[] sliceColors;

    public RotatingBackground(float radius, float rotationSpeed) {
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.rotation = 0f;

        // Example color scheme; customize as you like
        sliceColors = new Color[] {
                Color.RED,
                Color.ORANGE,
        };
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

        var originalTransform = g2d.getTransform();

        // Rotate the entire context
        g2d.rotate(Math.toRadians(rotation));

        // Draw 6 slices, each covering 60 degrees
        for (int i = 0; i < 6; i++) {
            float startAngleDegrees = i * 60f;
            float endAngleDegrees = (i + 1) * 60f;

            // Pick a color for this slice
            Color sliceColor = sliceColors[i % sliceColors.length];
            g2d.setColor(sliceColor);

            // Build a triangular shape from center (0,0) to the edges of the slice
            Path2D slice = new Path2D.Float();
            slice.moveTo(0, 0);

            // Convert degrees to radians
            double startRad = Math.toRadians(startAngleDegrees);
            double endRad   = Math.toRadians(endAngleDegrees);

            // First outer point of the slice
            float x1 = (float) (radius * Math.cos(startRad));
            float y1 = (float) (radius * Math.sin(startRad));
            slice.lineTo(x1, y1);

            // Second outer point of the slice
            float x2 = (float) (radius * Math.cos(endRad));
            float y2 = (float) (radius * Math.sin(endRad));
            slice.lineTo(x2, y2);

            slice.closePath();

            // Fill the triangular slice
            g2d.fill(slice);
        }

        // Restore transform so subsequent drawing isn’t rotated
        g2d.setTransform(originalTransform);
    }
}

package Shape;

import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonShapeMode2 implements GameShape {

    private Path2D.Float hexagon;
    private Path2D.Float path = new Path2D.Float();

    // Position of the hexagon’s center
    private float x, y;

    // Radius from center to each vertex
    private float radius;

    // Current rotation angle in degrees
    private float rotation;
    private float rotationSpeed;
    private float speed;
    private int notPaint;

    public HexagonShapeMode2(float x, float y, float radius, float rotationSpeed, float speed, int notPaint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.rotation = 0f;
        this.speed = speed;
        this.notPaint = notPaint;
    }

    public void increaseRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = this.rotationSpeed + rotationSpeed;
    }
    public void increaseSpeed(float speed) {
        this.speed = this.speed + speed;
    }

    @Override
    public void update() {
        // Rotation rate
        rotation += rotationSpeed;

        //Closing rate
        radius -= speed;

        if (rotation >= 360f) {
            rotation -= 360f;
        }
        increaseRotationSpeed(0.001f);
        increaseSpeed(0.001f);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Save the original transform
        var originalTransform = g2d.getTransform();

        // Translate and rotate the Graphics2D context
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(rotation));

        // Create a path for the hexagon
        hexagon = new Path2D.Float();
        for (int i = 0; i < 6; i++) {
            Path2D.Float part = new Path2D.Float();
            double angle = Math.toRadians(60 * i);
            float vx1 = (float)(radius * Math.cos(angle));
            float vy1 = (float)(radius * Math.sin(angle));
            float vx2 = (float)((radius + 10f) * Math.cos(angle));
            float vy2 = (float)((radius + 10f) * Math.sin(angle));
            float vx3 = (float)((radius + 10f) * Math.cos(angle+Math.toRadians(60)));
            float vy3 = (float)((radius + 10f) * Math.sin(angle+Math.toRadians(60)));
            float vx4 = (float)(radius * Math.cos(angle+Math.toRadians(60)));
            float vy4 = (float)(radius * Math.sin(angle+Math.toRadians(60)));
            part.moveTo(vx1, vy1);
            part.lineTo(vx2, vy2);
            part.lineTo(vx3, vy3);
            part.lineTo(vx4, vy4);
            part.closePath();
            if (i != this.notPaint && i != this.notPaint+2 && i != this.notPaint+4){
                hexagon.append(part,false);
            }
        }

        path = hexagon;
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

    @Override
    public Path2D.Float getPath() {
        return this.path;
    }
}

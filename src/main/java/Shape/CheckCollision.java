package Shape;

import java.awt.geom.Area;

public class CheckCollision {
    public static boolean checkCollision(GameShape shape1, GameShape shape2) {
        Area area1 = new Area(shape1.getPath());
        Area area2 = new Area(shape2.getPath());

        area1.intersect(area2);

        return !area1.isEmpty();
    }
}

package universe;

import assets.geometries.ColoredGeometry;

public class Moon extends Celestial {
    
    public Moon(float radius, ColoredGeometry collisionGeometry, ColoredGeometry body) {
        super(radius, collisionGeometry);
        attachSpatial(body);
        registerColoredSpatial(body);
    }
    
}

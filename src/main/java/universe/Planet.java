package universe;

import assets.geometries.ColoredGeometry;

public class Planet extends Celestial {
    
    public Planet(float radius, ColoredGeometry collisionGeometry, ColoredGeometry body) {
        super(radius, collisionGeometry);
        attachSpatial(body);
        registerColoredSpatial(body);
    }
    
}

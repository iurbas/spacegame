package universe.buildings;

import universe.Celestial;
import assets.geometries.ColoredGeometry;

public class Building extends Celestial {
    
    public Building(float size, ColoredGeometry collisionGeometry, ColoredGeometry building) {
        super(size, collisionGeometry);
        attachSpatial(building);
        registerColoredSpatial(building);
    }
    
}

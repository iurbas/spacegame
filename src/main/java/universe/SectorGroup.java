package universe;

import assets.geometries.ColoredGeometry;

public class SectorGroup extends Celestial {
    
    public SectorGroup(float width, ColoredGeometry collisionGeometry) {
        super(width, collisionGeometry);
    }
    
    public void attachSector(Sector sector) {
        attachCelestial(sector);
    }
    
}

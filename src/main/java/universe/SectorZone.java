package universe;

import assets.geometries.ColoredGeometry;

public class SectorZone extends Celestial {
    
    public SectorZone(float width, ColoredGeometry collisionGeometry) {
        super(width, collisionGeometry);
    }
    
    public void attachSectorGroup(SectorGroup sectorGroup) {
        attachCelestial(sectorGroup);
    }
    
}

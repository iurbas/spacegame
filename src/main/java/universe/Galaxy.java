package universe;

import com.jme3.light.AmbientLight;
import assets.geometries.ColoredGeometry;

public class Galaxy extends Celestial {
    
    public Galaxy(float radius, ColoredGeometry collisionGeometry, AmbientLight galacticLight) {
        super(radius, collisionGeometry);
        attachLight(galacticLight);
    }
    
    public void attachSectorGroup(Sector sectorGroup) {
        attachCelestial(sectorGroup);
    }
    
}

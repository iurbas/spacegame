package universe;

import assets.geometries.ColoredGeometry;

public class PlanetSystem extends Celestial {
    
    private Planet planet;
    
    public PlanetSystem(float radius, ColoredGeometry collisionGeometry, Planet planet) {
        super(radius, collisionGeometry);
        this.planet = planet;
        attachCelestial(planet);
    }
    
    public void attachMoon(Moon moon) {
        attachCelestial(moon);
    }
    
    @Override
    public void highlight(boolean highlight) {
        super.highlight(highlight);
        showDetails(highlight);
    }
    
}

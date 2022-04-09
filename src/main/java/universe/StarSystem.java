package universe;

import assets.geometries.ColoredGeometry;

public class StarSystem extends Celestial {
    
    private Star star;
    
    public StarSystem(float radius, ColoredGeometry collisionGeometry, Star star) {
        super(radius, collisionGeometry);
        this.star = star;
        star.getLight().setRadius(radius * 2f);
        attachLight(star.getLight());
        attachCelestial(star);
    }
    
    public void attachPlanetSystem(PlanetSystem planetSystem) {
        attachCelestial(planetSystem);
    }
    
    @Override
    public void highlight(boolean highlight) {
        super.highlight(highlight);
        showDetails(highlight);
    }
    
    @Override
    protected void setLowLOD() {
        showChildren(false);
        star.show(true);
    }
    
    @Override
    protected void setHighLOD() {
        showChildren(true);
    }
    
    @Override
    public void update(float seconds) {
        super.update(seconds);
        star.getLight().setPosition(getNode().getWorldTranslation());
    }
    
}

package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import universe.Galaxy;
import universe.Sector;
import universe.SectorGroup;
import assets.geometries.ColoredGeometry;

public class GalaxyGenerator extends CelestialGenerator {

    private static final float AMBIENT_LIGHT = .1f;
    
    public GalaxyGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public Galaxy generate(float radius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius);
        AmbientLight galacticLight = generateGalacticLight();
        Galaxy galaxy = new Galaxy(radius, collisionSphere, galacticLight);
        generateSectorGroups(galaxy, radius);
        return galaxy;
    }
    
    private void generateSectorGroups(Galaxy galaxy, float width) {
        Sector sectorGroup = generateSectorGroup(width);
        galaxy.attachSectorGroup(sectorGroup);
    }
    
    private Sector generateSectorGroup(float width) {
        SectorGenerator generator = new SectorGenerator(assetManager);
        Sector sectorGroup = generator.generate(width);
        return sectorGroup;
    }
    
    private AmbientLight generateGalacticLight() {
        AmbientLight galacticLight = new AmbientLight();
        galacticLight.setColor(ColorRGBA.White.mult(AMBIENT_LIGHT));
        return galacticLight;
    }
    
}

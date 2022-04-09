package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import math.Seed;
import universe.Celestial;
import universe.Sector;
import universe.StarSystem;
import assets.UnitMeshes;
import assets.geometries.ColoredGeometry;
import assets.materials.UnshadedMaterial;

public class SectorGenerator extends CelestialGenerator {
    
    public SectorGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public Sector generate(float width) {
        ColoredGeometry collisionCube = generateCollisionGeometry(width);
        Sector sector = new Sector(width, collisionCube);
        generateStars(sector, width);
        return sector;
    }
    
    private void generateStars(Sector sector, float width) {
        float starSystemScale = width / 100f;
        float minStarSystemRadius = starSystemScale * 2;
        float maxStarSystemRadius = starSystemScale * 8;
        
        float starSystemDensity = 1f;
        float minDistanceBetweenStars = maxStarSystemRadius;
        float maxStarSystemVolume = (float) Math.pow(maxStarSystemRadius + minDistanceBetweenStars, 3);
        float maxStarCount = (float) Math.pow(width, 3) / maxStarSystemVolume;
        float starCount = maxStarCount * starSystemDensity;
        
        for(int i = 0; i < starCount; i++) {
            float starSystemRadius = Seed.randFloat(minStarSystemRadius, maxStarSystemRadius);
            Vector3f starSystemLocation = randLocalTranslation(width - (starSystemRadius * 2f));
            if(isValidLocation(sector, starSystemLocation, starSystemRadius, minDistanceBetweenStars)) {
                StarSystem starSystem = generateStarSystem(starSystemRadius, starSystemLocation);
                sector.attachStarSystem(starSystem);
            }
        }
    }
    
    @Override
    protected ColoredGeometry generateCollisionGeometry(float width) {
        UnshadedMaterial collisionMaterial = new UnshadedMaterial(assetManager);
        ColoredGeometry collisionCube = new ColoredGeometry("Collision Cube", UnitMeshes.UnitCube, collisionMaterial);
        collisionCube.scale(width);
        collisionCube.setTransparency(COLLISION_GEOMETRY_TRANSPARENCY);
        collisionCube.setColor(ColorRGBA.White);
        return collisionCube;
    }
    
    private StarSystem generateStarSystem(float radius, Vector3f localTranslation) {
        StarSystemGenerator generator = new StarSystemGenerator(assetManager);
        StarSystem starSystem = generator.generate(radius);
        starSystem.setLOD(Celestial.LOD.LOW);
        starSystem.getTransformation().enableTranslation(false);
        starSystem.getTransformation().setLocalTranslation(localTranslation);
        starSystem.getTransformation().setOrbitRadius(localTranslation.length());
        randomizeOrbit(starSystem);
        return starSystem;
    }
    
    private Vector3f randLocalTranslation(float sectorWidth) {
        float halfWidth = sectorWidth / 2f;
        float x = Seed.randFloat(-halfWidth, halfWidth);
        float y = Seed.randFloat(-halfWidth, halfWidth);
        float z = Seed.randFloat(-halfWidth, halfWidth);
        return new Vector3f(x, y, z);
    }
    
    @Override
    protected float randInclination() {
        return Seed.randFloat(-FastMath.QUARTER_PI, FastMath.QUARTER_PI) * 1.5f;
    }
    
    private boolean isValidLocation(Celestial sector, Vector3f location, float starSystemRadius, float minDistanceBetweenStars) {
        for(Celestial child : sector.getChildren()) {
            if(location.distance(child.getTransformation().getLocalTranslation()) < (starSystemRadius + minDistanceBetweenStars + child.getRadius())) {
                return false;
            }
        }
        return true;
    }
    
}

package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import universe.SectorGroup;
import universe.SectorZone;
import assets.UnitMeshes;
import assets.geometries.ColoredGeometry;
import assets.materials.UnshadedMaterial;

public class SectorZoneGenerator extends CelestialGenerator {
    
    public SectorZoneGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public SectorZone generate(float width) {
        ColoredGeometry collisionCube = generateCollisionGeometry(width);
        SectorZone sectorZone = new SectorZone(width, collisionCube);
        generateSectorGroups(sectorZone, width);
        return sectorZone;
    }
    
    private void generateSectorGroups(SectorZone sectorZone, float width) {
        float sectorGroupWidth = width / 2f;
        
        SectorGroup rightTopFront = generateSectorGroup(sectorGroupWidth, 1, 1, 1);
        SectorGroup rightTopBack = generateSectorGroup(sectorGroupWidth, 1, 1, -1);
        SectorGroup rightBottomFront = generateSectorGroup(sectorGroupWidth, 1, -1, 1);
        SectorGroup rightBottomBack = generateSectorGroup(sectorGroupWidth, 1, -1, -1);
        SectorGroup leftTopFront = generateSectorGroup(sectorGroupWidth, -1, 1, 1);
        SectorGroup leftTopBack = generateSectorGroup(sectorGroupWidth, -1, 1, -1);
        SectorGroup leftBottomFront = generateSectorGroup(sectorGroupWidth, -1, -1, 1);
        SectorGroup leftBottomBack = generateSectorGroup(sectorGroupWidth, -1, -1, -1);
        
        sectorZone.attachSectorGroup(rightTopFront);
        sectorZone.attachSectorGroup(rightTopBack);
        sectorZone.attachSectorGroup(rightBottomFront);
        sectorZone.attachSectorGroup(rightBottomBack);
        sectorZone.attachSectorGroup(leftTopFront);
        sectorZone.attachSectorGroup(leftTopBack);
        sectorZone.attachSectorGroup(leftBottomFront);
        sectorZone.attachSectorGroup(leftBottomBack);
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
    
    private SectorGroup generateSectorGroup(float width, int x, int y, int z) {
        float sectorGroupTrueWidth = width * .9f;
        float halfWidth = width / 2f;
        Vector3f localTranslation = new Vector3f(x * halfWidth, y * halfWidth, z * halfWidth);
        SectorGroupGenerator generator = new SectorGroupGenerator(assetManager);
        SectorGroup sector = generator.generate(sectorGroupTrueWidth);
        sector.getTransformation().enableTranslation(false);
        sector.getTransformation().setLocalTranslation(localTranslation);
        sector.getTransformation().setOrbitRadius(localTranslation.length());
        return sector;
    }
    
}

package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import universe.Sector;
import universe.SectorGroup;
import assets.UnitMeshes;
import assets.geometries.ColoredGeometry;
import assets.materials.UnshadedMaterial;

public class SectorGroupGenerator extends CelestialGenerator {
    
    public SectorGroupGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public SectorGroup generate(float width) {
        ColoredGeometry collisionCube = generateCollisionGeometry(width);
        SectorGroup sectorGroup = new SectorGroup(width, collisionCube);
        generateSectors(sectorGroup, width);
        return sectorGroup;
    }
    
    private void generateSectors(SectorGroup sectorGroup, float width) {
        float sectorWidth = width / 2f;
        
        Sector rightTopFront = generateSector(sectorWidth, 1, 1, 1);
        Sector rightTopBack = generateSector(sectorWidth, 1, 1, -1);
        Sector rightBottomFront = generateSector(sectorWidth, 1, -1, 1);
        Sector rightBottomBack = generateSector(sectorWidth, 1, -1, -1);
        Sector leftTopFront = generateSector(sectorWidth, -1, 1, 1);
        Sector leftTopBack = generateSector(sectorWidth, -1, 1, -1);
        Sector leftBottomFront = generateSector(sectorWidth, -1, -1, 1);
        Sector leftBottomBack = generateSector(sectorWidth, -1, -1, -1);
        
        sectorGroup.attachSector(rightTopFront);
        sectorGroup.attachSector(rightTopBack);
        sectorGroup.attachSector(rightBottomFront);
        sectorGroup.attachSector(rightBottomBack);
        sectorGroup.attachSector(leftTopFront);
        sectorGroup.attachSector(leftTopBack);
        sectorGroup.attachSector(leftBottomFront);
        sectorGroup.attachSector(leftBottomBack);
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
    
    private Sector generateSector(float width, int x, int y, int z) {
        float sectorTrueWidth = width * .9f;
        float halfWidth = width / 2f;
        Vector3f localTranslation = new Vector3f(x * halfWidth, y * halfWidth, z * halfWidth);
        SectorGenerator generator = new SectorGenerator(assetManager);
        Sector sector = generator.generate(sectorTrueWidth);
        sector.getTransformation().enableTranslation(false);
        sector.getTransformation().setLocalTranslation(localTranslation);
        sector.getTransformation().setOrbitRadius(localTranslation.length());
        return sector;
    }
    
}

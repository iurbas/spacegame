package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import math.Seed;
import universe.Planet;
import assets.geometries.ColoredGeometry;
import assets.universe.CelestialTextures;
import assets.geometries.TexturedSphere;
import assets.materials.ShadedMaterial;
import assets.textures.CombinedTexture;

public class PlanetGenerator extends CelestialGenerator {
    
    public PlanetGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public Planet generate(float radius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius);
        ColoredGeometry body = generatePlanetBody(radius);
        Planet planet = new Planet(radius, collisionSphere, body);
        randomizeRotation(planet);
        return planet;
    }
    
    public ColoredGeometry generatePlanetBody(float radius) {
        int textureIndex = Seed.randInt(0, CelestialTextures.getNumPlanetTextures());
        CombinedTexture texture = CelestialTextures.getPlanetTexture(textureIndex);
        ShadedMaterial material = new ShadedMaterial(assetManager, texture);
        material.setShininess(16f);
        TexturedSphere body = new TexturedSphere("Planet Body", material, radius);
        body.setColor(ColorRGBA.White);
        return body;
    }
    
}

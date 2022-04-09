package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import math.Seed;
import universe.Moon;
import assets.geometries.ColoredGeometry;
import assets.universe.CelestialTextures;
import assets.geometries.TexturedSphere;
import assets.materials.ShadedMaterial;
import assets.textures.CombinedTexture;

public class MoonGenerator extends CelestialGenerator {
    
    public MoonGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public Moon generate(float radius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius);
        ColoredGeometry body = generateMoonBody(radius);
        Moon moon = new Moon(radius, collisionSphere, body);
        randomizeRotation(moon);
        return moon;
    }
    
    public ColoredGeometry generateMoonBody(float radius) {
        int textureIndex = Seed.randInt(0, CelestialTextures.getNumMoonTextures());
        CombinedTexture texture = CelestialTextures.getMoonTexture(textureIndex);
        ShadedMaterial material = new ShadedMaterial(assetManager, texture);
        material.setShininess(16f);
        TexturedSphere body = new TexturedSphere("Moon Body", material, radius);
        body.setColor(ColorRGBA.White);
        return body;
    }
    
}

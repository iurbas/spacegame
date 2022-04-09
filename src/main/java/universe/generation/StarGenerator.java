package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import math.GameMath;
import math.Seed;
import universe.Star;
import assets.universe.CelestialTextures;
import assets.geometries.ColoredGeometry;
import assets.geometries.TexturedSphere;
import assets.materials.UnshadedMaterial;
import assets.textures.CombinedTexture;

import java.util.Arrays;
import java.util.List;

public class StarGenerator extends CelestialGenerator {

    private static final List<ColorRGBA> STAR_COLORS = Arrays.asList(ColorRGBA.White, ColorRGBA.Red, ColorRGBA.Yellow, ColorRGBA.Blue);
    
    public StarGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public Star generate(float radius, float systemRadius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius);
        ColoredGeometry body = generateStarBody(radius);
        PointLight light = generateStarLight(systemRadius * 10f);
        Star star = new Star(radius, collisionSphere, body, light);
        randomizeRotation(star);
        return star;
    }
    
    private ColoredGeometry generateStarBody(float radius) {
        int textureIndex = Seed.randInt(0, CelestialTextures.getNumStarTextures());
        CombinedTexture texture = CelestialTextures.getStarTexture(textureIndex);
        UnshadedMaterial material = new UnshadedMaterial(assetManager, texture);
        TexturedSphere body = new TexturedSphere("Star Body", material, radius);
        body.setColor(randomColor());
        return body;
    }

    private PointLight generateStarLight(float radius) {
        PointLight starLight = new PointLight();
        starLight.setRadius(radius);
        starLight.setColor(ColorRGBA.White);
        return starLight;
    }

    private ColorRGBA randomColor() {
        ColorRGBA baseColor = STAR_COLORS.get(Seed.randInt(0, STAR_COLORS.size()));
        return GameMath.mixColors(baseColor, ColorRGBA.White);
    }
    
}

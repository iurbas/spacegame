package visualtests.universe.generation.assets;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import math.GameMath;
import assets.materials.ShadedMaterial;
import assets.universe.CelestialTextureDefs;
import assets.textures.CombinedTexture;
import assets.textures.CombinedTextureDef;
import visualtests.VisualTest;

public class TexturedMaterialTest extends VisualTest {
    
    public static void main(String[] args) {
        TexturedMaterialTest test = new TexturedMaterialTest();
        test.start();
    }

    @Override
    public void init() {
        addAmbientLight(.3f);
        addPointLight();
        Sphere mesh = new Sphere(32, 32, 1f);
        CombinedTextureDef textureDef = CelestialTextureDefs.Earth;
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        ShadedMaterial material = new ShadedMaterial(assetManager, texture);
        material.setColor(GameMath.mixColors(ColorRGBA.Red, ColorRGBA.White));
        Geometry geometry = new Geometry("test geometry", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

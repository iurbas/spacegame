
package visualtests.universe.generation.assets;

import com.jme3.math.ColorRGBA;
import assets.universe.CelestialTextureDefs;
import assets.geometries.TexturedSphere;
import assets.materials.ShadedMaterial;
import assets.textures.CombinedTexture;
import assets.textures.CombinedTextureDef;
import visualtests.VisualTest;

public class TexturedSphereTest extends VisualTest {
    
    public static void main(String[] args) {
        TexturedSphereTest test = new TexturedSphereTest();
        test.start();
    }

    @Override
    public void init() {
        addPointLight();
        CombinedTextureDef textureDef = CelestialTextureDefs.Earth;
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        ShadedMaterial material = new ShadedMaterial(assetManager, texture);
        TexturedSphere geometry = new TexturedSphere("test geometry", material, 1f);
        geometry.setColor(ColorRGBA.White);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

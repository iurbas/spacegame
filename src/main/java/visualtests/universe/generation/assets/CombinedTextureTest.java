package visualtests.universe.generation.assets;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import assets.universe.CelestialTextureDefs;
import assets.textures.CombinedTexture;
import assets.textures.CombinedTextureDef;
import visualtests.VisualTest;

public class CombinedTextureTest extends VisualTest {
    
    public static void main(String[] args) {
        CombinedTextureTest test = new CombinedTextureTest();
        test.start();
    }

    @Override
    public void init() {
        addAmbientLight();
        addPointLight();
        Sphere mesh = new Sphere(32, 32, 1f);
        CombinedTextureDef textureDef = CelestialTextureDefs.Earth;
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        Material material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        material.setTexture("DiffuseMap", texture.getDiffuse());
        material.setTexture("NormalMap", texture.getNormal());
        Geometry geometry = new Geometry("test geometry", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

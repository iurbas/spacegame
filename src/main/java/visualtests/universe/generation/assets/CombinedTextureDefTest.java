package visualtests.universe.generation.assets;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import assets.universe.CelestialTextureDefs;
import assets.textures.CombinedTextureDef;
import visualtests.VisualTest;

public class CombinedTextureDefTest extends VisualTest {
    
    public static void main(String[] args) {
        CombinedTextureDefTest test = new CombinedTextureDefTest();
        test.start();
    }

    @Override
    public void init() {
        addAmbientLight();
        addPointLight();
        Sphere mesh = new Sphere(32, 32, 1f);
        CombinedTextureDef textureDef = CelestialTextureDefs.Earth;
        Texture texture = assetManager.loadTexture(textureDef.getDiffuse());
        Texture normalTexture = assetManager.loadTexture(textureDef.getNormal());
        Material material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        material.setTexture("DiffuseMap", texture);
        material.setTexture("NormalMap", normalTexture);
        Geometry geometry = new Geometry("test geometry", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

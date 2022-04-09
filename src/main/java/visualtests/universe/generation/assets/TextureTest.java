package visualtests.universe.generation.assets;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import visualtests.VisualTest;

public class TextureTest extends VisualTest {
    
    public static void main(String[] args) {
        TextureTest test = new TextureTest();
        test.start();
    }

    @Override
    public void init() {
        addAmbientLight();
        addPointLight();
        // Unshaded Textured Sphere
        Sphere mesh = new Sphere(32, 32, 1f);
        Texture texture = assetManager.loadTexture("Textures/Planets/Earth.jpg");
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setTexture("ColorMap", texture);
        Geometry geometry = new Geometry("unshaded textured sphere", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(-2f, 0f, -5f);
        rootNode.attachChild(geometry);
        //Shaded Textured Sphere
        Sphere mesh2 = new Sphere(32, 32, 1f);
        Texture texture2 = assetManager.loadTexture("Textures/Planets/Earth.jpg");
        Texture normalTexture2 = assetManager.loadTexture("Textures/Planets/Earth_normal.jpg");
        Material material2 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        material2.setTexture("DiffuseMap", texture2);
        material2.setTexture("NormalMap", normalTexture2);
        Geometry geometry2 = new Geometry("shaded textured sphere", mesh2);
        geometry2.setMaterial(material2);
        geometry2.setLocalTranslation(2f, 0f, -5f);
        rootNode.attachChild(geometry2);
    }
    
}

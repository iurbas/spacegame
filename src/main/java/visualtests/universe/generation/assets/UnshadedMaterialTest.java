package visualtests.universe.generation.assets;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import assets.materials.UnshadedMaterial;
import visualtests.VisualTest;

public class UnshadedMaterialTest extends VisualTest {
    
    public static void main(String[] args) {
        UnshadedMaterialTest test = new UnshadedMaterialTest();
        test.start();
    }

    @Override
    public void init() {
        Sphere mesh = new Sphere(32, 32, 1f);
        UnshadedMaterial material = new UnshadedMaterial(assetManager);
        Geometry geometry = new Geometry("test geometry", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

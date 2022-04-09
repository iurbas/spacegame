package visualtests.universe.generation.assets;

import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import assets.materials.UnshadedMaterial;
import assets.meshes.Circle;
import visualtests.VisualTest;

public class CircleTest extends VisualTest {
    
    public static void main(String[] args) {
        CircleTest test = new CircleTest();
        test.start();
    }

    @Override
    public void init() {
        Circle mesh = new Circle(1f, 64);
        UnshadedMaterial material = new UnshadedMaterial(assetManager);
        Geometry geometry = new Geometry("test geometry", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0f, 0f, -5f);
        geometry.rotate(FastMath.HALF_PI, 0f, 0f);
        rootNode.attachChild(geometry);
    }
    
}

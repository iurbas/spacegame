
package visualtests.universe.generation.assets;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Sphere;
import assets.geometries.ColoredGeometry;
import assets.materials.UnshadedMaterial;
import visualtests.VisualTest;

public class ColoredGeometryTest extends VisualTest {
    
    public static void main(String[] args) {
        ColoredGeometryTest test = new ColoredGeometryTest();
        test.start();
    }

    @Override
    public void init() {
        Sphere mesh = new Sphere(32, 32, 1f);
        UnshadedMaterial material = new UnshadedMaterial(assetManager);
        ColoredGeometry geometry = new ColoredGeometry("test geometry", mesh, material);
        geometry.setColor(ColorRGBA.Blue);
        geometry.setLocalTranslation(0f, 0f, -5f);
        rootNode.attachChild(geometry);
    }
    
}

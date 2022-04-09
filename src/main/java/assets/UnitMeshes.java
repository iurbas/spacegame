package assets;

import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import assets.meshes.Circle;

public class UnitMeshes {
    
    public static Sphere UnitSphere = new Sphere(32, 32, 1f);
    public static Box UnitCube = new Box(.5f, .5f, .5f);
    public static Circle UnitCircle = new Circle(1f, 128);
    public static Quad UnitQuad = new Quad(1f, 1f);
    
    public static void initMeshes() {
        UnitSphere.setTextureMode(Sphere.TextureMode.Projected);
    }
    
}

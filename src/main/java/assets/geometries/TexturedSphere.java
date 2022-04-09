package assets.geometries;

import com.jme3.math.FastMath;
import com.jme3.util.TangentBinormalGenerator;
import assets.materials.ColoredMaterial;

public class TexturedSphere extends ColoredSphere {
    
    public TexturedSphere(String name, ColoredMaterial material, float radius) {
        super(name, material, radius);
        rotate(3f * FastMath.HALF_PI, 0f, 0f);
        TangentBinormalGenerator.generate(getMesh());
    }
    
}

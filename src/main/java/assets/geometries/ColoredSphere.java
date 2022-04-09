package assets.geometries;

import assets.UnitMeshes;
import assets.materials.ColoredMaterial;

public class ColoredSphere extends ColoredGeometry {
    
    public ColoredSphere(String name, ColoredMaterial material, float radius) {
        super(name, UnitMeshes.UnitSphere, material);
        scale(radius);
    }
    
}

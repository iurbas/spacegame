package universe;

import com.jme3.light.PointLight;
import assets.geometries.ColoredGeometry;

public class Star extends Celestial {
    
    PointLight light;
    
    public Star(float radius, ColoredGeometry collisionGeometry, ColoredGeometry body, PointLight light) {
        super(radius, collisionGeometry);
        this.light = light;
        attachSpatial(body);
        registerColoredSpatial(body);
    }
    
    public PointLight getLight() {
        return light;
    }
    
}

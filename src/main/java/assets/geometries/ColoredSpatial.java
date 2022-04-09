package assets.geometries;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;

public interface ColoredSpatial {
    
    public void setColor(ColorRGBA color);
    
    public Spatial getBase();
    
}

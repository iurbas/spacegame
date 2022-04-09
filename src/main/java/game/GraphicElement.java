package game;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.LinkedHashSet;
import java.util.Set;
import assets.geometries.ColoredSpatial;

public abstract class GraphicElement {
    
    private Node node;
    
    private Set<ColoredSpatial> coloredSpatials;
    private ColorRGBA color;
    
    public GraphicElement() {
        node = new Node();
        coloredSpatials = new LinkedHashSet<ColoredSpatial>();
        color = ColorRGBA.White;
    }
    
    public final Node getNode() {
        return node;
    }
    
    public final ColorRGBA getColor() {
        return color;
    }
    
    public void setColor(ColorRGBA color) {
        for(ColoredSpatial spatial : coloredSpatials) {
            spatial.setColor(color);
        }
        this.color = color;
    }
    
    public final void attachSpatial(Spatial spatial) {
        node.attachChild(spatial);
    }
    
    public final void dettachSpatial(Spatial spatial) {
        node.detachChild(spatial);
    }
    
    public final void registerColoredSpatial(ColoredSpatial spatial) {
        coloredSpatials.add(spatial);
    }
    
    public final void unregisterColoredSpatial(ColoredSpatial spatial) {
        coloredSpatials.remove(spatial);
    }
    
    public final void show(boolean show) {
        node.setCullHint(show ? Spatial.CullHint.Inherit : Spatial.CullHint.Always);
    }
    
}

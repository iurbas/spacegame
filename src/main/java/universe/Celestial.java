package universe;

import com.jme3.light.Light;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import assets.geometries.ColoredGeometry;
import game.GraphicElement;

public abstract class Celestial extends GraphicElement {

    private static final float HIGHLIGHT_ALPHA = .025f;
    
    private float radius;
    private ColoredGeometry collisionGeometry;
    private Set<Spatial> detailSpatials;
    
    private Node collisionNode;
    private CelestialTransformation celestialTransformation;
    
    private Celestial parent;
    private List<Celestial> children;
    
    private LOD lod;
    
    public Celestial(float radius, ColoredGeometry collisionGeometry) {
        this.radius = radius;
        this.collisionGeometry = collisionGeometry;
        detailSpatials = new LinkedHashSet<Spatial>();
        collisionNode = new Node();
        celestialTransformation = new CelestialTransformation();
        getNode().attachChild(collisionNode);
        parent = null;
        children = new ArrayList<Celestial>();
        lod = LOD.HIGH;
        registerColoredSpatial(collisionGeometry);
        showCollisionGeometry(false);
    }
    
    public final float getRadius() {
        return radius;
    }
    
    public final ColoredGeometry getCollisionGeometry() {
        return collisionGeometry;
    }
    
    public final Node getCollisionNode() {
        return collisionNode;
    }
    
    public ArrayList<Node> getCollisionNodes() {
        ArrayList<Node> collisionNodes = new ArrayList<Node>();
        collisionNodes.add(collisionNode);
        if(hasParent()) {
            collisionNodes.add(parent.getCollisionNode());
        }
        return collisionNodes;
    }
    
    public final CelestialTransformation getTransformation() {
        return celestialTransformation;
    }
    
    public final Celestial getParent() {
        return parent;
    }
    
    public final List<Celestial> getChildren() {
        return children;
    }
    
    public final LOD getLOD() {
        return lod;
    }
    
    public final void attachLight(Light light) {
        getNode().addLight(light);
    }
    
    public final void registerDetailSpatial(Spatial spatial) {
        spatial.setCullHint(Spatial.CullHint.Always);
        detailSpatials.add(spatial);
    }
    
    public final void unregisterDetailSpatial(Spatial spatial) {
        detailSpatials.remove(spatial);
    }
    
    private void setParent(Celestial parent) {
        this.parent = parent;
    }
    
    protected final void attachCelestial(Celestial child) {
        if(children.contains(child)) {
            return;
        }
        child.removeFromParent();
        child.setParent(this);
        getNode().attachChild(child.getNode());
        collisionNode.attachChild(child.getCollisionGeometry());
        children.add(child);
        if(child.hasChildren()) {
            registerDetailSpatial(child.getCollisionGeometry());
        }
    }
    
    protected final void detachCelestial(Celestial child) {
        if(!children.contains(child)) {
            return;
        }
        child.setParent(null);
        getNode().detachChild(child.getNode());
        collisionNode.detachChild(child.getCollisionGeometry());
        children.remove(child);
        unregisterDetailSpatial(child.getCollisionGeometry());
    }
    
    protected final void removeFromParent() {
        if(parent != null) {
            parent.detachCelestial(this);
        }
    }
    
    public final boolean hasParent() {
        return (parent != null);
    }
    
    public final boolean hasChildren() {
        return (children.size() > 0);
    }
    
    public final boolean isAncestorOf(Celestial celestial) {
        return celestial.isDescendantOf(this);
    }
    
    public final boolean isParentOf(Celestial celestial) {
        return celestial.isChildOf(this);
    }
    
    public final boolean isSiblingOrParentOf(Celestial celestial) {
        return !isDescendantOf(celestial);
    }
    
    public final boolean isSiblingOf(Celestial celestial) {
        if(parent == null || !celestial.hasParent()) {
            return false;
        } else {
            return parent.equals(celestial.getParent());
        }
    }
    
    public final boolean isChildOf(Celestial celestial) {
        if(parent == null) {
            return false;
        } else {
            return parent.equals(celestial);
        }
    }
    
    public final boolean isDescendantOf(Celestial celestial) {
        if(parent == null) {
            return false;
        } else {
            if(isChildOf(celestial)) {
                return true;
            } else {
                return parent.isDescendantOf(celestial);
            }
        }
    }
    
    protected final void showCollisionGeometry(boolean show) {
        collisionGeometry.setCullHint(show ? Spatial.CullHint.Inherit : Spatial.CullHint.Always);
    }
    
    public void showDetails(boolean show) {
        for(Spatial detail : detailSpatials) {
            detail.setCullHint(show ? Spatial.CullHint.Inherit : Spatial.CullHint.Always);
        }
    }
    
    public final void showChildren(boolean show) {
        for(Celestial child : children) {
            child.show(show);
        }
    }
    
    public void highlight(boolean highlight) {
        showCollisionGeometry(highlight);
        collisionGeometry.highlightAlpha(highlight, HIGHLIGHT_ALPHA);
    }
    
    public void setLOD(LOD newLOD) {
        if(!newLOD.equals(lod)) {
            switch(newLOD) {
                case LOW:   setLowLOD();    break;
                case HIGH:  setHighLOD();   break;
            }
            this.lod = newLOD;
        }
    }
    
    protected void setLowLOD() {}
    
    protected void setHighLOD() {}
    
    public final void enableCollisionGeometry(boolean enable) {
        if(hasChildren() && hasParent()) {
            if(enable) {
                parent.getCollisionNode().attachChild(collisionGeometry);
            } else {
                collisionGeometry.removeFromParent();
            }
        }
    }
    
    public final Celestial findCelestialFromCollision(Geometry navigationGeometry) {
        for(Celestial child : children) {
            if(navigationGeometry.equals(child.getCollisionGeometry())) {
                return child;
            }
        }
        return null;
    }
    
    public void update(float seconds) {
        celestialTransformation.update(seconds);
        getNode().setLocalTranslation(celestialTransformation.getLocalTranslation());
        getNode().setLocalRotation(celestialTransformation.getLocalRotation());
        collisionGeometry.setLocalTranslation(celestialTransformation.getLocalTranslation());
        collisionGeometry.setLocalRotation(celestialTransformation.getLocalRotation());
        for(Celestial child : children) {
            child.update(seconds);
        }
    }
    
    public enum LOD {
        LOW,
        HIGH
    }
    
}

package gui;

import java.util.ArrayList;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import game.GraphicElement;
import input.handlers.gui.GuiEvent;
import input.handlers.gui.GuiEventHandler;

public abstract class GuiElement extends GraphicElement {
    
    private int id;
    private GuiEventHandler handler;
    
    private GuiElement parent;
    private ArrayList<GuiElement> children;
    
    public GuiElement() {
        id = -1;
        handler = null;
        parent = null;
        children = new ArrayList<GuiElement>();
        getNode().setLocalTranslation(0f, 0f, 10f);
    }
    
    public int getId() {
        return id;
    }
    
    public GuiElement getParent() {
        return parent;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void bind(GuiEventHandler handler) {
        this.handler = handler;
    }
    
    private void setParent(GuiElement parent) {
        this.parent = parent;
    }
    
    protected final void attachChild(GuiElement child) {
        if(children.contains(child)) {
            return;
        }
        child.removeFromParent();
        child.setParent(this);
        getNode().attachChild(child.getNode());
        children.add(child);
    }
    
    protected final void detachChild(GuiElement child) {
        if(!children.contains(child)) {
            return;
        }
        child.setParent(null);
        getNode().detachChild(child.getNode());
        children.remove(child);
    }
    
    protected final void removeFromParent() {
        if(parent != null) {
            parent.detachChild(this);
        }
    }
    
    public final boolean hasParent() {
        return (parent != null);
    }
    
    public final boolean hasChildren() {
        return (children.size() > 0);
    }
    
    public final void setPosition(float x, float y) {
        setPosition(new Vector2f(x, y));
    }
    
    public final void setPosition(Vector2f position) {
        getNode().setLocalTranslation(position.getX(), position.getY(), getNode().getLocalTranslation().getZ());
    }
    
    public final GuiElement findGuiElementFromCollision(Geometry guiGeometry) {
        if(guiGeometry.hasAncestor(getNode())) {
            GuiElement foundElement;
            for(GuiElement child : children) {
                foundElement = child.findGuiElementFromCollision(guiGeometry);
                if(foundElement != null) {
                    return foundElement;
                }
            }
            return this;
        } else {
            return null;
        }
    }
    
    public final void triggerEvent() {
        if(handler != null) {
            handler.handle(new GuiEvent(this));
        }
    }
    
    public abstract void handlePress();
    
    public abstract void handleDepress();
    
}

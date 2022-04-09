package input.handlers;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import gui.Gui;
import gui.GuiElement;

public class GuiInputHandler {
    
    private Gui gui;
    private GuiElement currentlyPressedElement;
    
    public GuiInputHandler(Gui gui) {
        this.gui = gui;
        currentlyPressedElement = null;
    }
    
    public Node getCollisionNode() {
        return gui.getRootNode();
    }
    
    public void handleLeftClick(Geometry selectedGuiGeometry, boolean isPressed) {
        if(isPressed) {
            handlePress(selectedGuiGeometry);
        } else {
            handleDepress(selectedGuiGeometry);
        }
    }
    
    private void handlePress(Geometry selectedGuiGeometry) {
        if(currentlyPressedElement != null) {
            currentlyPressedElement.handleDepress();
            currentlyPressedElement = null;
        }
        if(selectedGuiGeometry != null) {
            currentlyPressedElement = gui.findGuiElementFromCollision(selectedGuiGeometry);
            currentlyPressedElement.handlePress();
        }
    }
    
    private void handleDepress(Geometry selectedGuiGeometry) {
        if(currentlyPressedElement != null) {
            if(selectedGuiGeometry != null) {
            GuiElement selectedGuiElement = gui.findGuiElementFromCollision(selectedGuiGeometry);
                if(selectedGuiElement.equals(currentlyPressedElement)) {
                    currentlyPressedElement.triggerEvent();
                }
            }
            currentlyPressedElement.handleDepress();
            currentlyPressedElement = null;
        }
    }
    
    public void handleRightClick(Geometry selectedGuiGeometry, boolean isPressed) {
        
    }
    
    public void handleMouseMove(Geometry selectedGuiGeometry) {
        
    }
    
}

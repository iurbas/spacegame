package gui;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import universe.Celestial;

public class Gui {
    
    GuiElement rootElement;
    
    public Gui(){
        rootElement = new TextButton(1, 2, 2, "");
    }
    
    public Node getRootNode() {
        return rootElement.getNode();
    }
    
    public GuiElement findGuiElementFromCollision(Geometry guiGeometry) {
        return rootElement.findGuiElementFromCollision(guiGeometry);
    }
    
    public void showCelestialGui(Celestial celestial) {
        
    }
    
}

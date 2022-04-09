package gui;

import assets.geometries.ColoredGeometry;
import assets.gui.GuiMaterials;
import com.jme3.scene.shape.Quad;

public class Button extends GuiElement {
    
    ColoredGeometry button;
    
    public Button(int id, float width, float height) {
        setId(id);
        Quad quad = new Quad(width, height);
        button = new ColoredGeometry("Button", quad, GuiMaterials.getButtonMaterial());
        attachSpatial(button);
        registerColoredSpatial(button);
    }
    
    @Override
    public void handlePress() {
        button.highlight(true);
    }

    @Override
    public void handleDepress() {
        button.highlight(false);
    }
    
}

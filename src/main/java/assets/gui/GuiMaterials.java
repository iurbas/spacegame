package assets.gui;

import assets.materials.ColoredMaterial;

public class GuiMaterials {
    
    private static ColoredMaterial button = null;
    
    public static void loadButtonMaterial(ColoredMaterial material) {
        button = material;
    }
    
    public static ColoredMaterial getButtonMaterial() {
        return button;
    }
    
}

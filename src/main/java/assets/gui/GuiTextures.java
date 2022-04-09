package assets.gui;

import assets.textures.CombinedTexture;

public class GuiTextures {
    
    private static CombinedTexture coloredButton = null;
    
    public static void loadColoredButtonTexture(CombinedTexture texture) {
        coloredButton = texture;
    }
    
    public static CombinedTexture getColoredButtonTexture() {
        return coloredButton;
    }
    
}

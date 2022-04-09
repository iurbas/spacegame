package assets.gui;

import com.jme3.font.BitmapFont;

public class GuiFonts {
    
    private static BitmapFont defaultFont = null;
    
    public static void loadDefaultFont(BitmapFont font) {
        defaultFont = font;
    }
    
    public static BitmapFont getDefaultFont() {
        return defaultFont;
    }
    
}

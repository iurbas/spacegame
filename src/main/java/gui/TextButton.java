package gui;

import assets.gui.GuiFonts;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;

public class TextButton extends Button {
    
    BitmapText text;
    
    public TextButton(int id, float width, float height, String label) {
        super(id, width, height);
        BitmapFont font = GuiFonts.getDefaultFont();
        text = new BitmapText(font, false);
        text.setSize(font.getCharSet().getRenderedSize());
        text.setText(label);
        float textX = (width / 2) - (text.getLineWidth() / 2);
        float textY = (height / 2) + (text.getLineHeight() / 2);
        text.setLocalTranslation(textX, textY, 1f);
        attachSpatial(text);
    }
    
    public void setTextColor(ColorRGBA color) {
        text.setColor(color);
    }
    
}

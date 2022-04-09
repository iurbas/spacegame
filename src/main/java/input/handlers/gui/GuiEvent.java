package input.handlers.gui;

import gui.GuiElement;

public class GuiEvent {
    
    private GuiElement guiElement;
    private boolean boolVal;
    private int intVal;
    private float floatVal;
    
    public GuiEvent(GuiElement element, boolean boolValue, int intValue, float floatValue) {
        guiElement = element;
        boolVal = boolValue;
        intVal = intValue;
        floatVal = floatValue;
    }
    
    public GuiEvent(GuiElement element) {
        this(element, false, 0, 0f);
    }
    
    public GuiEvent(GuiElement element, boolean value) {
        this(element, value, value ? 1 : 0, value ? 1f : 0f);
    }
    
    public GuiEvent(GuiElement element, int value) {
        this(element, value != 0, value, (float) value);
    }
    
    public GuiEvent(GuiElement element, float value) {
        this(element, value != 0f, (int) value, value);
    }
    
    public GuiElement getGuiElement() {
        return guiElement;
    }
    
    public boolean getBooleanValue() {
        return boolVal;
    }
    
    public int getIntValue() {
        return intVal;
    }
    
    public float getFloatValue() {
        return floatVal;
    }
    
}

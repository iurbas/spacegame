package input.listeners;

import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import input.handlers.MouseInputHandler;

public class MouseInputListener  implements ActionListener, AnalogListener {
    
    private InputManager inputManager;
    private MouseInputHandler handler;
    
    public MouseInputListener(InputManager inputManager, MouseInputHandler handler) {
        this.inputManager = inputManager;
        this.handler = handler;
        initMapping();
    }
    
    public final void initMapping() {
        inputManager.addMapping("Cursor_Left_Click",    new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Cursor_Right_Click",   new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping("Cursor_Move",          new MouseAxisTrigger(MouseInput.AXIS_X, true),
                                                        new MouseAxisTrigger(MouseInput.AXIS_X, false),
                                                        new MouseAxisTrigger(MouseInput.AXIS_Y, true),
                                                        new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(this, "Cursor_Left_Click", "Cursor_Right_Click", "Cursor_Move");
    }
    
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("Cursor_Left_Click"))    handler.handleLeftClick(inputManager.getCursorPosition(), isPressed);
        if(name.equals("Cursor_Right_Click"))   handler.handleRightClick(inputManager.getCursorPosition(), isPressed);
    }

    public void onAnalog(String name, float value, float tpf) {
        if(name.equals("Cursor_Move"))          handler.handleMouseMove(inputManager.getCursorPosition());
    }
    
}

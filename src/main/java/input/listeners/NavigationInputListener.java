package input.listeners;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import input.handlers.NavigationInputHandler;

public class NavigationInputListener implements ActionListener {
    
    private InputManager inputManager;
    private NavigationInputHandler handler;
    
    public NavigationInputListener(InputManager inputManager, NavigationInputHandler handler) {
        this.inputManager = inputManager;
        this.handler = handler;
        initKeys();
    }
    
    public final void initKeys() {
        inputManager.addMapping("Navigation_Back",            new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Navigation_ShowDetails",     new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addMapping("Navigation_ShowFullDetails", new KeyTrigger(KeyInput.KEY_CAPITAL));
        inputManager.addListener(this, "Navigation_Back", "Navigation_ShowDetails", "Navigation_ShowFullDetails");
    }
    
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Navigation_Back"))             { if(isPressed) { handler.handleBack();            } }
        if (name.equals("Navigation_ShowDetails"))      { if(isPressed) { handler.handleShowDetails();     } }
        if (name.equals("Navigation_ShowFullDetails"))  { if(isPressed) { handler.handleShowFullDetails(); } }
    }
    
}

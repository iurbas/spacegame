package input;

import com.jme3.input.InputManager;
import gui.Gui;
import input.handlers.CameraInputHandler;
import input.handlers.GuiInputHandler;
import input.handlers.NavigationInputHandler;
import input.handlers.MouseInputHandler;
import input.listeners.CameraInputListener;
import input.listeners.MouseInputListener;
import input.listeners.NavigationInputListener;
import navigation.CelestialNavigator;
import universe.camera.CelestialCamera;

public class InputController {
    
    GuiInputHandler guiInputHandler;
    CameraInputHandler cameraInputHandler;
    NavigationInputHandler navigationInputHandler;
    MouseInputHandler mouseInputHandler;
    CameraInputListener cameraInputListener;
    NavigationInputListener navigationInputListener;
    MouseInputListener mouseInputListener;
    
    public InputController(InputManager inputManager, CelestialCamera camera, Gui gui, CelestialNavigator navigator) {
        guiInputHandler = new GuiInputHandler(gui);
        cameraInputHandler = new CameraInputHandler(camera);
        navigationInputHandler = new NavigationInputHandler(navigator);
        mouseInputHandler = new MouseInputHandler(guiInputHandler, navigationInputHandler, camera.getCamera());
        cameraInputListener = new CameraInputListener(inputManager, cameraInputHandler);
        navigationInputListener = new NavigationInputListener(inputManager, navigationInputHandler);
        mouseInputListener = new MouseInputListener(inputManager, mouseInputHandler);
    }
    
}

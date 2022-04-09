package game.states;

import gui.Gui;
import input.InputController;
import navigation.CelestialNavigator;
import universe.Galaxy;
import universe.camera.CelestialCamera;
import universe.generation.GalaxyGenerator;

public class PlayState extends GameState {
    
    private Galaxy galaxy;
    private CelestialCamera camera;
    private Gui gui;
    private CelestialNavigator navigator;
    private InputController input;
    
    @Override
    public void init() {
        initGalaxy();
        initCamera();
        initGui();
        initNavigation();
        initInputHandling();
    }
    
    private void initGalaxy() {
        GalaxyGenerator galaxyGenerator = new GalaxyGenerator(assetManager);
        galaxy = galaxyGenerator.generate(1000f);
        rootNode.attachChild(galaxy.getNode());
    }
    
    private void initCamera() {
        camera = new CelestialCamera(cam);
        camera.setFrustum(.1f, galaxy.getRadius() * 100f);
        camera.setMoveSpeed(1f);
    }

    private void initGui() {
        gui = new Gui();
        guiNode.attachChild(gui.getRootNode());
    }
    
    private void initNavigation() {
        navigator = new CelestialNavigator(galaxy, camera, gui);
    }
    
    private void initInputHandling() {
        input = new InputController(inputManager, camera, gui, navigator);
    }
    
    @Override
    public void update(float tpf) {
        float seconds = timer.getTimeInSeconds();
        galaxy.update(seconds);
        camera.update(tpf, seconds);
    }
    
}

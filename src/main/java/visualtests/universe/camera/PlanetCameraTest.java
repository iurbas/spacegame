
package visualtests.universe.camera;

import input.handlers.CameraInputHandler;
import input.listeners.CameraInputListener;
import universe.Celestial;
import universe.camera.CelestialCamera;
import universe.generation.PlanetGenerator;
import visualtests.VisualTest;

public class PlanetCameraTest extends VisualTest {
    
    private Celestial celestial;
    private CelestialCamera camera;
    
    public static void main(String[] args) {
        PlanetCameraTest test = new PlanetCameraTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        
        PlanetGenerator generator = new PlanetGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
        
        camera = new CelestialCamera(cam);
        camera.orbit(celestial);
        
        CameraInputHandler cameraInputHandler = new CameraInputHandler(camera);
        CameraInputListener cameraInputListener = new CameraInputListener(inputManager, cameraInputHandler);
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
        camera.update(tpf, seconds);
    }
    
}

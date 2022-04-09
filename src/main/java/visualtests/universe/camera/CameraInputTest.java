
package visualtests.universe.camera;

import input.handlers.CameraInputHandler;
import input.listeners.CameraInputListener;
import universe.Celestial;
import universe.camera.CelestialCamera;
import universe.generation.PlanetSystemGenerator;
import visualtests.VisualTest;

public class CameraInputTest extends VisualTest {
    
    private Celestial celestial;
    private CelestialCamera camera;
    
    public static void main(String[] args) {
        CameraInputTest test = new CameraInputTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        
        PlanetSystemGenerator generator = new PlanetSystemGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
        
        camera = new CelestialCamera(cam);
        camera.orbit(celestial);
        
        CameraInputHandler cameraInputHandler = new CameraInputHandler(camera);
        CameraInputListener cameraInputListener = new CameraInputListener(inputManager, cameraInputHandler);
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(0f);
        camera.update(tpf, seconds);
    }
    
}

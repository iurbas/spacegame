
package visualtests.universe.camera;

import input.handlers.CameraInputHandler;
import input.listeners.CameraInputListener;
import universe.Celestial;
import universe.camera.CelestialCamera;
import universe.generation.StarSystemGenerator;
import visualtests.VisualTest;

public class StarSystemCameraTest extends VisualTest {
    
    private Celestial celestial;
    private CelestialCamera camera;
    
    public static void main(String[] args) {
        StarSystemCameraTest test = new StarSystemCameraTest();
        test.start();
    }

    @Override
    public void init() {
        loadAllTextures();
        addAmbientLight(.2f);
        addPointLight();
        
        StarSystemGenerator generator = new StarSystemGenerator(assetManager);
        celestial = generator.generate(1000f);
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


package visualtests.universe.generation;

import universe.Celestial;
import universe.generation.PlanetSystemGenerator;
import visualtests.VisualTest;

public class PlanetSystemTest extends VisualTest {
    
    private Celestial celestial;
    
    public static void main(String[] args) {
        PlanetSystemTest test = new PlanetSystemTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        PlanetSystemGenerator generator = new PlanetSystemGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
    }
    
}

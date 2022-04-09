
package visualtests.universe.generation;

import universe.Celestial;
import universe.generation.PlanetGenerator;
import visualtests.VisualTest;

public class PlanetTest extends VisualTest {
    
    private Celestial celestial;
    
    public static void main(String[] args) {
        PlanetTest test = new PlanetTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        PlanetGenerator generator = new PlanetGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
    }
    
}


package visualtests.universe.generation;

import universe.Celestial;
import universe.generation.MoonGenerator;
import visualtests.VisualTest;

public class MoonTest extends VisualTest {
    
    private Celestial celestial;
    
    public static void main(String[] args) {
        MoonTest test = new MoonTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        MoonGenerator generator = new MoonGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
    }
    
}

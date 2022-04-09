
package visualtests.universe.generation;

import universe.Celestial;
import universe.generation.StarSystemGenerator;
import visualtests.VisualTest;

public class StarSystemTest extends VisualTest {
    
    private Celestial celestial;
    
    public static void main(String[] args) {
        StarSystemTest test = new StarSystemTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        StarSystemGenerator generator = new StarSystemGenerator(assetManager);
        celestial = generator.generate(1f);
        rootNode.attachChild(celestial.getNode());
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
    }
    
}

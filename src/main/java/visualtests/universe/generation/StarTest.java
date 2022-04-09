
package visualtests.universe.generation;

import universe.Celestial;
import universe.generation.StarGenerator;
import visualtests.VisualTest;

public class StarTest extends VisualTest {
    
    private Celestial celestial;
    
    public static void main(String[] args) {
        StarTest test = new StarTest();
        test.start();
    }

    @Override
    public void init() {
        loadBasicTextures();
        addPointLight();
        StarGenerator generator = new StarGenerator(assetManager);
        celestial = generator.generate(1f, 10f);
        rootNode.attachChild(celestial.getNode());
    }
    
    @Override
    public void update(float tpf, float seconds) {
        celestial.update(seconds);
    }
    
}

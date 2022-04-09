
package visualtests.universe.generation.assets;

import assets.AssetLoader;
import visualtests.VisualTest;

public class AssetLoaderTest extends VisualTest {
    
    private AssetLoader assetLoader;
    
    public static void main(String[] args) {
        AssetLoaderTest test = new AssetLoaderTest();
        test.start();
    }

    @Override
    public void init() {
        assetLoader = new AssetLoader(assetManager, rootNode);
    }
    
    @Override
    public void update(float tpf, float seconds) {
        if(assetLoader.hasNext()) {
            assetLoader.loadNext();
        }
    }
    
}

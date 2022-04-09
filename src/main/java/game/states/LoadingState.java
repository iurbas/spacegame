package game.states;

import assets.AssetLoader;

public class LoadingState extends GameState {
    
    AssetLoader assetLoader;
    
    @Override
    public void init() {
        assetLoader = new AssetLoader(assetManager, rootNode);
    }
    
    @Override
    public void update(float tpf) {
        if(assetLoader.hasNext()) {
            assetLoader.loadNext();
        } else {
            assetLoader.clearScene();
            nextState();
        }
    }
    
}

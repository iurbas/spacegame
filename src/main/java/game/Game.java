package game;

import com.jme3.app.SimpleApplication;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.system.AppSettings;

public abstract class Game extends SimpleApplication {
    
    @Override
    public void simpleInitApp() {
        enableDebug(false);
        enableFlycam(false);
        addBloomFilter();
        initGameStates();
    }
    
    private void addBloomFilter() {
        FilterPostProcessor fpp=new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        bloom.setDownSamplingFactor(2.0f);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
    }
    
    private void enableDebug(boolean enable) {
        setDisplayStatView(enable);
        setDisplayFps(enable);
    }
    
    private void enableFlycam(boolean enable) {
        flyCam.setEnabled(enable);
    }
    
    protected abstract void initGameStates();
    
    public AppSettings getSettings() {
        return settings;
    }
}

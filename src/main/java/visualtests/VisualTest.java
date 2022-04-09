package visualtests;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import assets.AssetLoader;
import assets.universe.CelestialTextureDefs;

public abstract class VisualTest extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        cam.setFrustumPerspective(45f, (float) cam.getWidth() / cam.getHeight(), 0.1f, 10000f);
        flyCam.setMoveSpeed(5f);
        init();
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        float seconds = timer.getTimeInSeconds();
        update(tpf, seconds);
    }
    
    public abstract void init();
    
    public void update(float tpf, float seconds) {}
    
    protected void loadBasicTextures() {
        AssetLoader assetLoader = new AssetLoader(assetManager);
        assetLoader.loadStarTexture(CelestialTextureDefs.Sun);
        assetLoader.loadPlanetTexture(CelestialTextureDefs.Earth);
        assetLoader.loadMoonTexture(CelestialTextureDefs.Moon);
    }
    
    protected void loadAllTextures() {
        AssetLoader assetLoader = new AssetLoader(assetManager);
        assetLoader.loadAll();
    }
    
    protected void addAmbientLight() {
        addAmbientLight(.5f);
    }
    
    protected void addAmbientLight(float intensity) {
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(intensity));
        rootNode.addLight(ambientLight);
    }
    
    protected void addPointLight() {
        PointLight pointLight = new PointLight();
        pointLight.setRadius(100f);
        pointLight.setColor(ColorRGBA.White);
        pointLight.setPosition(new Vector3f(10f, 5f, 5f));
        rootNode.addLight(pointLight);
    }
    
}

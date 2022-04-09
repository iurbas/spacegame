package assets;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import assets.universe.CelestialTextureDefs;
import assets.universe.CelestialTextures;
import assets.geometries.TexturedSphere;
import assets.gui.GuiFontDefs;
import assets.gui.GuiFonts;
import assets.gui.GuiMaterials;
import assets.gui.GuiTextureDefs;
import assets.gui.GuiTextures;
import assets.materials.ColoredMaterial;
import assets.materials.ShadedMaterial;
import assets.materials.UnshadedMaterial;
import assets.textures.CombinedTexture;
import assets.textures.CombinedTextureDef;
import com.jme3.font.BitmapFont;
import com.jme3.texture.Texture;

public class AssetLoader {
    
    private AssetManager assetManager;
    private Node rootNode;
    private int loadItem;
    private int itemCount;
    private float xPosition;
    private float itemRadius;
    
    private AmbientLight ambientLight;
    private PointLight pointLight;
    
    public AssetLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.rootNode = null;
        this.loadItem = 0;
        this.itemCount = CelestialTextureDefs.Stars.size() + CelestialTextureDefs.Planets.size() + CelestialTextureDefs.Moons.size() + 1;
        this.itemRadius = (6f / itemCount);
        this.xPosition = (0 - (((itemRadius * 2) * (itemCount - 1)) / 2f));
        this.ambientLight = null;
        this.pointLight = null;
    }
    
    public AssetLoader(AssetManager assetManager, Node rootNode) {
        this(assetManager);
        this.rootNode = rootNode;
    }
    
    public void loadAll() {
        while(hasNext()) {
            loadNext();
        }
    }
    
    public boolean hasNext() {
        return loadItem < itemCount;
    }
    
    public void loadNext() {
        int starItemStart = 0;
        int starItemEnd = (starItemStart + (CelestialTextureDefs.Stars.size() - 1));
        int planetItemStart = starItemEnd + 1;
        int planetItemEnd = (planetItemStart + (CelestialTextureDefs.Planets.size() - 1));
        int moonItemStart = planetItemEnd + 1;
        int moonItemEnd = (moonItemStart + (CelestialTextureDefs.Moons.size() - 1));
        if(loadItem == 0) {
            UnitMeshes.initMeshes();
            loadAmbientLight();
            loadPointLight();
        }
        if(loadItem >= starItemStart && loadItem <= starItemEnd) {
            int textureIndex = loadItem - starItemStart;
            loadStar(textureIndex);
        }  else if(loadItem >= planetItemStart && loadItem <= planetItemEnd) {
            int textureIndex = loadItem - planetItemStart;
            loadPlanet(textureIndex);
        } else if(loadItem >= moonItemStart && loadItem <= moonItemEnd) {
            int textureIndex = loadItem - moonItemStart;
            loadMoon(textureIndex);
        } else if(loadItem == moonItemEnd + 1) {
            loadGuiAssets();
        }
        loadItem++;
    }
    
    private void loadAmbientLight() {
        if(rootNode != null) {
            ambientLight = new AmbientLight();
            ambientLight.setColor(ColorRGBA.White.mult(.1f));
            rootNode.addLight(ambientLight);
        }
    }
    
    private void loadPointLight() {
        if(rootNode != null) {
            pointLight = new PointLight();
            pointLight.setRadius(100f);
            pointLight.setColor(ColorRGBA.White);
            pointLight.setPosition(new Vector3f(xPosition, 0f, 0f));
            rootNode.addLight(pointLight);
        }
    }
    
    public CombinedTexture loadStarTexture(CombinedTextureDef textureDef) {
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        CelestialTextures.loadStarTexture(texture);
        return texture;
    }
    
    public CombinedTexture loadPlanetTexture(CombinedTextureDef textureDef) {
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        CelestialTextures.loadPlanetTexture(texture);
        return texture;
    }
    
    public CombinedTexture loadMoonTexture(CombinedTextureDef textureDef) {
        CombinedTexture texture = new CombinedTexture(assetManager, textureDef);
        CelestialTextures.loadMoonTexture(texture);
        return texture;
    }
    
    public void loadGuiAssets() {
        CombinedTexture texture = new CombinedTexture(assetManager, GuiTextureDefs.Button);
        GuiTextures.loadColoredButtonTexture(texture);
        UnshadedMaterial material = new UnshadedMaterial(assetManager);
        material.setTexture(texture);
        material.enableTransparency(true);
        material.setColor(ColorRGBA.White);
        GuiMaterials.loadButtonMaterial(material);
        BitmapFont font = assetManager.loadFont(GuiFontDefs.DefaultFont);
        GuiFonts.loadDefaultFont(font);
    }
    
    private void loadStar(int index) {
        CombinedTexture texture = loadStarTexture(CelestialTextureDefs.Stars.get(index));
        if(rootNode != null) {
            UnshadedMaterial material = new UnshadedMaterial(assetManager, texture);
            loadTexturedSphere(material);
        }
    }
    
    private void loadPlanet(int index) {
        CombinedTexture texture = loadPlanetTexture(CelestialTextureDefs.Planets.get(index));
        if(rootNode != null) {
            ShadedMaterial material = new ShadedMaterial(assetManager, texture);
            loadTexturedSphere(material);
        }
    }
    
    private void loadMoon(int index) {
        CombinedTexture texture = loadMoonTexture(CelestialTextureDefs.Moons.get(index));
        if(rootNode != null) {
            ShadedMaterial material = new ShadedMaterial(assetManager, texture);
            loadTexturedSphere(material);
        }
    }
    
    private void loadTexturedSphere(ColoredMaterial material) {
        TexturedSphere geometry = new TexturedSphere("Load Item", material, itemRadius);
        geometry.setColor(ColorRGBA.White);
        geometry.setLocalTranslation(xPosition, 0f, 0f);
        rootNode.attachChild(geometry);
        xPosition += (itemRadius * 2);
    }
    
    public void clearScene() {
        if(rootNode != null) {
            rootNode.removeLight(ambientLight);
            rootNode.removeLight(pointLight);
            rootNode.detachAllChildren();
        }
    }
    
}

package assets.textures;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;

public class CombinedTexture {
    
    private Texture diffuseTexture;
    private Texture normalTexture;
    private Texture specularTexture;
    
    public CombinedTexture(AssetManager assetManager, CombinedTextureDef definition) {
        setDiffuse(assetManager, definition.getDiffuse());
        setNormal(assetManager, definition.getNormal());
        setSpecular(assetManager, definition.getSpecular());
    }
    
    public final void setDiffuse(AssetManager assetManager, String definition) {
        if(definition == null) {
            diffuseTexture = null;
        } else {
            diffuseTexture = assetManager.loadTexture(definition);
        }
    }
    
    public final void setNormal(AssetManager assetManager, String definition) {
        if(definition == null) {
            normalTexture = null;
        } else {
            normalTexture = assetManager.loadTexture(definition);
        }
    }
    
    public final void setSpecular(AssetManager assetManager, String definition) {
        if(definition == null) {
            specularTexture = null;
        } else {
            specularTexture = assetManager.loadTexture(definition);
        }
    }
    
    public Texture getDiffuse() {
        return diffuseTexture;
    }
    
    public Texture getNormal() {
        return normalTexture;
    }
    
    public Texture getSpecular() {
        return specularTexture;
    }
    
}

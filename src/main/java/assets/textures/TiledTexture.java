package assets.textures;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;

public class TiledTexture {
    
    private Texture texture;
    private int imagesX;
    private int imagesY;
    
    public TiledTexture(AssetManager assetManager, TiledTextureDef definition) {
        texture = assetManager.loadTexture(definition.getTexture());
        imagesX = definition.getImagesX();
        imagesY = definition.getImagesY();
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public int getImagesX() {
        return imagesX;
    }
    
    public int getImagesY() {
        return imagesY;
    }
    
}

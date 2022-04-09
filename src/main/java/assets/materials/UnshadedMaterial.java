package assets.materials;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import assets.textures.CombinedTexture;

public class UnshadedMaterial extends ColoredMaterial {
    
    public UnshadedMaterial(AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    }
    
    public UnshadedMaterial(AssetManager assetManager, CombinedTexture texture) {
        this(assetManager);
        setTexture(texture);
    }
    
    public final void setTexture(CombinedTexture texture) {
        if(texture.getDiffuse()  != null) setTexture("ColorMap",  texture.getDiffuse());
    }
    
    @Override
    public void setColor(ColorRGBA color) {
        setColor("Color", color);
    }
    
    public final void setGlowColor(ColorRGBA color) {
        setColor("GlowColor", color);
    }
    
}

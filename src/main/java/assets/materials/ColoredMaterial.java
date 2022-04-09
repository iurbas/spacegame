package assets.materials;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;

public abstract class ColoredMaterial extends Material {
    
    public ColoredMaterial(AssetManager assetManager, String materialResourcePath) {
        super(assetManager, materialResourcePath);
    }
    
    public final void enableTransparency(boolean enable) {
        getAdditionalRenderState().setBlendMode(enable ? RenderState.BlendMode.Alpha : RenderState.BlendMode.Off);
    }
    
    public abstract void setColor(ColorRGBA color);
    
}

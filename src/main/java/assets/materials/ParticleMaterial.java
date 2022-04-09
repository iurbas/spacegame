package assets.materials;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.texture.Texture;

public class ParticleMaterial extends ColoredMaterial {
    
    public ParticleMaterial(AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Misc/Particle.j3md");
    }
    
    public final void setTexture(Texture texture) {
        setTexture("Texture", texture);
    }
    
    public final void setSoftness(float softness) {
        setFloat("Softness", softness);
    }
    
    @Override
    public final void setColor(ColorRGBA color) {
        setGlowColor(color);
    }
    
    public final void setGlowColor(ColorRGBA color) {
        setColor("GlowColor", color);
    }
    
}

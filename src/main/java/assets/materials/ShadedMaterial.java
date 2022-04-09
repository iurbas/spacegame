package assets.materials;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import math.GameMath;
import assets.textures.CombinedTexture;

public class ShadedMaterial extends ColoredMaterial {
    
    public ShadedMaterial(AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        setBoolean("UseMaterialColors", true);
        setShininess(64f);
    }
    
    public ShadedMaterial(AssetManager assetManager, CombinedTexture texture) {
        this(assetManager);
        setTexture(texture);
    }
    
    public final void setTexture(CombinedTexture texture) {
        if(texture.getDiffuse()  != null) setTexture("DiffuseMap",  texture.getDiffuse());
        if(texture.getNormal()   != null) setTexture("NormalMap",   texture.getNormal());
        if(texture.getSpecular() != null) setTexture("SpecularMap", texture.getSpecular());
    }
    
    public final void setShininess(float shininess) {
        shininess = GameMath.bound(shininess, 0f, 128f);
        setFloat("Shininess", shininess);
    }
    
    @Override
    public void setColor(ColorRGBA color) {
        setColor("Ambient", color);
        setColor("Diffuse", color);
        setColor("Specular", color);
    }
    
    public final void setShineColor(ColorRGBA color) {
        setColor("Specular", color);
    }
    
    public final void setGlowColor(ColorRGBA color) {
        setColor("GlowColor", color);
    }
    
}

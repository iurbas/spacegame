package assets.geometries;

import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import assets.materials.ParticleMaterial;
import assets.textures.TiledTexture;

public class ColoredParticleEmitter extends ParticleEmitter implements ColoredSpatial {
    
    private ParticleMaterial particleMaterial;
    
    public ColoredParticleEmitter(String name, ParticleMesh.Type type, int numParticles, ParticleMaterial material) {
       super(name, type, numParticles);
       setMaterial(material);
       setGravity(0f, 0f, 0f);
       setEnabled(false);
       setInWorldSpace(false);
       particleMaterial = material;
    }
    
    public final void setTexture(TiledTexture texture) {
        particleMaterial.setTexture(texture.getTexture());
        setImagesX(texture.getImagesX());
        setImagesY(texture.getImagesY());
    }
    
    public final void setColor(ColorRGBA startColor, ColorRGBA endColor) {
        setStartColor(startColor);
        setEndColor(endColor);
    }
    
    public void setColor(ColorRGBA color) {
        setColor(color, color);
    }
    
    public Spatial getBase() {
        return this;
    }
    
}

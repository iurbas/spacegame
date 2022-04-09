package assets.geometries;

import assets.materials.ColoredMaterial;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import math.GameMath;

public class ColoredGeometry extends Geometry implements ColoredSpatial {
    
    private ColoredMaterial coloredMaterial;
    private ColorRGBA color;
    private float alpha;
    
    public ColoredGeometry(String name, Mesh mesh, ColoredMaterial material) {
        super(name, mesh);
        setMaterial(material);
        coloredMaterial = material;
        color = new ColorRGBA(1f, 1f, 1f, 1f);
        alpha = 1f;
    }
    
    public final void enableTransparency(boolean enable) {
        setQueueBucket(enable ? RenderQueue.Bucket.Transparent : RenderQueue.Bucket.Inherit);
        coloredMaterial.enableTransparency(enable);
    }
    
    public final void setTransparency(float alpha) {
        enableTransparency(true);
        this.alpha = alpha;
    }
    
    public void setColor(ColorRGBA newColor) {
        color.set(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), alpha);
        coloredMaterial.setColor(color);
    }
    
    public void highlightAlpha(boolean highlight, float alphaIncrease) {
        if(highlight) {
            float highlightAlphaValue = alpha + alphaIncrease;
            coloredMaterial.setColor(new ColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), highlightAlphaValue));
        } else {
            setColor(color);
        }
    }
    
    public void highlight(boolean highlight, ColorRGBA highlightColor) {
        if(highlight) {
            if(highlightColor == null) {
                highlightColor = new ColorRGBA(1f, 1f, 1f, alpha);
            }
            coloredMaterial.setColor(highlightColor);
        } else {
            setColor(color);
        }
    }
    
    public void highlight(boolean highlight) {
        highlight(highlight, GameMath.mixColors(color, new ColorRGBA(1f, 1f, 1f, alpha)));
    }
    
    public void lowlight(boolean lowlight) {
        highlight(lowlight, GameMath.mixColors(color, new ColorRGBA(0f, 0f, 0f, alpha)));
    }
    
    @Override
    public ColoredMaterial getMaterial() {
        return coloredMaterial;
    }
    
    public Spatial getBase() {
        return this;
    }
    
}

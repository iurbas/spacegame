package universe.generation;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import math.GameMath;
import math.Seed;
import universe.Celestial;
import universe.CelestialTransformation;
import assets.UnitMeshes;
import assets.geometries.ColoredGeometry;
import assets.geometries.ColoredSphere;
import assets.materials.UnshadedMaterial;

public abstract class CelestialGenerator {

    protected static final float COLLISION_GEOMETRY_TRANSPARENCY = 0f;
    
    protected AssetManager assetManager;
    
    public CelestialGenerator(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    protected ColoredGeometry generateCollisionGeometry(float radius) {
        float collisionRadius = radius * 1.05f;
        UnshadedMaterial collisionMaterial = new UnshadedMaterial(assetManager);
        ColoredSphere collisionSphere = new ColoredSphere("Collision Sphere", collisionMaterial, collisionRadius);
        collisionSphere.setTransparency(COLLISION_GEOMETRY_TRANSPARENCY);
        collisionSphere.setColor(ColorRGBA.White);
        return collisionSphere;
    }
    
    public ColoredGeometry generateOrbitRing(CelestialTransformation transformation) {
        float radius = transformation.getOrbitRadius();
        UnshadedMaterial material = new UnshadedMaterial(assetManager);
        ColoredGeometry ring = new ColoredGeometry("Orbit Ring", UnitMeshes.UnitCircle, material);
        ring.scale(radius);
        ring.setLocalRotation(transformation.calcTranslationRotation(0f));
        ring.setColor(ColorRGBA.Gray);
        return ring;
    }
    
    public final void setOrbitRpsFromMass(Celestial celestial, float centralMass) {
        float gravity = 10f;
        float orbitRadius = celestial.getTransformation().getOrbitRadius();
        float mps = (float) Math.sqrt((gravity * centralMass) / orbitRadius);
        float orbitRps = GameMath.mpsToRps(mps, orbitRadius);
        celestial.getTransformation().setOrbitRps(orbitRps);
    }
    
    protected void randomizeOrbit(Celestial celestial) {
        celestial.getTransformation().setInclinationReferenceAngle(randInclinationReferenceAngle());
        celestial.getTransformation().setInclination(randInclination());
        celestial.getTransformation().setInitialOrbitRadians(randOrbitRadians());
    }
    
    protected void randomizeRotation(Celestial celestial) {
        celestial.getTransformation().setTiltReferenceAngle(randTiltReferenceAngle());
        celestial.getTransformation().setTilt(randTilt());
        celestial.getTransformation().setInitialSpinRadians(randSpinRadians());
        celestial.getTransformation().setSpinRps(randSpinRps());
    }
    
    protected float randInclinationReferenceAngle() {
        return Seed.randFloat(0f, FastMath.TWO_PI);
    }
    
    protected float randInclination() {
        return Seed.randFloat(-FastMath.QUARTER_PI, FastMath.QUARTER_PI) * .5f;
    }
    
    protected float randOrbitRadians() {
        return Seed.randFloat(0f, FastMath.TWO_PI);
    }
    
    protected float randTiltReferenceAngle() {
        return Seed.randFloat(-FastMath.TWO_PI, FastMath.TWO_PI);
    }
    
    protected float randTilt() {
        return Seed.randFloat(-FastMath.QUARTER_PI, FastMath.QUARTER_PI) * .5f;
    }
    
    protected float randSpinRadians() {
        return Seed.randFloat(0f, FastMath.TWO_PI);
    }
    
    protected float randSpinRps() {
        return Seed.randFloat(1f/120f, 1f/60f);
    }
    
}

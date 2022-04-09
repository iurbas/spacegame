package universe;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import math.GameMath;

public class CelestialTransformation {
    
    private float orbitRadius;
    private float inclinationReferenceAngle;
    private float inclination;
    private float initialOrbitRadians;
    private float orbitRps;
    
    private float tiltReferenceAngle;
    private float tilt;
    private float initialSpinRadians;
    private float spinRps;
    
    private float orbitRadians;
    private float spinRadians;
    
    private Vector3f localTranslation;
    private Quaternion localRotation;
    
    private boolean translationEnabled;
    private boolean rotationEnabled;
    
    private Quaternion finalInclinationRotation;
    private Vector3f orbitVector;
    private Quaternion finalTiltRotation;
    private Vector3f spinVector;
    
    public CelestialTransformation() {
        orbitRadius = 0f;
        inclinationReferenceAngle = 0f;
        inclination = 0f;
        initialOrbitRadians = 0f;
        orbitRps = 0f;
        
        tiltReferenceAngle = 0f;
        tilt = 0f;
        initialSpinRadians = 0f;
        spinRps = 0f;
        
        orbitRadians = 0f;
        spinRadians = 0f;
        
        localTranslation = Vector3f.ZERO;
        localRotation = Quaternion.ZERO;
        
        translationEnabled = true;
        rotationEnabled = true;
        
        initializeTranslationAndRotationHelpers();
    }
    
    public final void setOrbitRadius(float radius) {
        orbitRadius = radius;
    }
    
    public final void setInclination(float radians) {
        inclination = radians;
        initializeTranslationAndRotationHelpers();
    }
    
    public final void setOrbitRps(float rps) {
        orbitRps = rps;
    }
    
    public final void setTilt(float radians) {
        tilt = radians;
        initializeTranslationAndRotationHelpers();
    }
    
    public final void setSpinRps(float rps) {
        spinRps = rps;
    }
    
    public final void setInclinationReferenceAngle(float radians) {
        inclinationReferenceAngle = radians;
        initializeTranslationAndRotationHelpers();
    }
    
    public final void setInitialOrbitRadians(float radians) {
        initialOrbitRadians = radians;
    }
    
    public final void setTiltReferenceAngle(float radians) {
        tiltReferenceAngle = radians;
        initializeTranslationAndRotationHelpers();
    }
    
    public final void setInitialSpinRadians(float radians) {
        initialSpinRadians = radians;
    }
    
    public final void setLocalTranslation(float x, float y, float z) {
        setLocalTranslation(new Vector3f(x, y, z));
    }
    
    public final void setLocalTranslation(Vector3f translation) {
        localTranslation = translation;
    }
    
    public final void setLocalRotation(Quaternion rotation) {
        localRotation = rotation;
    }
    
    public final void enableTranslation(boolean enable) {
        translationEnabled = enable;
    }
    
    public final void enableRotation(boolean enable) {
        rotationEnabled = enable;
    }
    
    public float getOrbitRadius() {
        return orbitRadius;
    }
    
    public float getInclinationReferenceAngle() {
        return inclinationReferenceAngle;
    }
    
    public final float getInitialOrbitRadians() {
        return initialOrbitRadians;
    }
    
    public float getTiltReferenceAngle() {
        return tiltReferenceAngle;
    }
    
    public float getOrbitRadians() {
        return orbitRadians;
    }
    
    public float getSpinRadians() {
        return spinRadians;
    }
    
    public Vector3f getLocalTranslation() {
        return localTranslation;
    }
    
    public Quaternion getLocalRotation() {
        return localRotation;
    }
    
    private void initializeTranslationAndRotationHelpers() {
        Vector3f baseOrbitalVec = Vector3f.UNIT_Y;
        Vector3f translationVec = Vector3f.UNIT_X;

        Quaternion preInclinationRotation = new Quaternion().fromAngleAxis(inclinationReferenceAngle, baseOrbitalVec);
        translationVec = preInclinationRotation.mult(translationVec);

        Vector3f inclinationVec = translationVec.cross(baseOrbitalVec);
        Quaternion inclinationRotation = new Quaternion().fromAngleAxis(inclination, inclinationVec);
        
        finalInclinationRotation = inclinationRotation.mult(preInclinationRotation);
        orbitVector = inclinationRotation.mult(baseOrbitalVec);
        
        translationVec = inclinationRotation.mult(translationVec);
        
        Quaternion preTiltRotation = new Quaternion().fromAngleAxis(tiltReferenceAngle, orbitVector);
        translationVec = preTiltRotation.mult(translationVec);
        
        Vector3f tiltVec = translationVec.cross(orbitVector);
        Quaternion tiltRotation = new Quaternion().fromAngleAxis(tilt, tiltVec);
        
        finalTiltRotation = tiltRotation.mult(preTiltRotation);
        spinVector = tiltRotation.mult(orbitVector);
    }
    
    public void update(float seconds) {
        if(translationEnabled) {
            updateTranslation(seconds);
        }
        if(rotationEnabled) {
            updateRotation(seconds);
        }
    }
    
    protected void updateTranslation(float seconds) {
        if(orbitRadius == 0f) {
            orbitRadians = 0f;
            localTranslation = Vector3f.ZERO;
        } else {
            orbitRadians = calcOrbitRadians(seconds);
            localTranslation = calcTranslation(seconds);
        }
    }
    
    protected void updateRotation(float seconds) {
        spinRadians = calcSpinRadians(seconds);
        localRotation = calcRotation(seconds);
    }
    
    public float calcOrbitRadians(float seconds) {
        return GameMath.cullRadians(initialOrbitRadians + (seconds * orbitRps * FastMath.TWO_PI));
    }
    
    public Vector3f calcTranslation(float seconds) {
        if(translationEnabled) {
            Vector3f translationVec = Vector3f.UNIT_X;
            Quaternion translationRotation = calcTranslationRotation(seconds);
            translationVec = translationRotation.mult(translationVec);
            return translationVec.mult(orbitRadius);
        } else {
            return localTranslation;
        }
    }
   
    public Quaternion calcTranslationRotation(float seconds) {
        Quaternion orbitalRotation = new Quaternion().fromAngleAxis(calcOrbitRadians(seconds), orbitVector);
        return orbitalRotation.mult(finalInclinationRotation);
    }
    
    public float calcSpinRadians(float seconds) {
        return GameMath.cullRadians(initialSpinRadians + (seconds * spinRps * FastMath.TWO_PI));
    }
   
    public Quaternion calcRotation(float seconds) {
        if(rotationEnabled) {
            Quaternion spinRotation = new Quaternion().fromAngleAxis(calcSpinRadians(seconds), spinVector);
            return spinRotation.mult(finalTiltRotation).mult(finalInclinationRotation);
        } else {
            return localRotation;
        }
    }
    
}

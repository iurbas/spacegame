package universe.camera;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import math.GameMath;
import universe.CelestialTransformation;

public class CelestialCameraTransformation extends CelestialTransformation {
    
    private float additionalOrbitRadians;
    private float perpendicularRadians;
    
    public CelestialCameraTransformation() {
        additionalOrbitRadians = 0f;
        perpendicularRadians = 0f;
        enableRotation(false);
    }
    
    public void setAdditionalOrbitRadians(float radians) {
        additionalOrbitRadians = radians;
    }
    
    public void setPerpendicularRadians(float radians) {
        perpendicularRadians = radians;
    }
    
    public void adjustOrbitRadiansMoveInTo(CelestialTransformation moveInTo) {
        float rotationAdjustment = (moveInTo.getSpinRadians() + moveInTo.getTiltReferenceAngle() + moveInTo.getInclinationReferenceAngle());
        float orbitalRadians = GameMath.cullRadians(additionalOrbitRadians - rotationAdjustment);
        setAdditionalOrbitRadians(orbitalRadians);
    }
    
    public void adjustOrbitRadiansMoveOutFrom(CelestialTransformation moveOutTo) {
        float rotationAdjustment = (moveOutTo.getSpinRadians() + moveOutTo.getTiltReferenceAngle() + moveOutTo.getInclinationReferenceAngle());
        float orbitalRadians = GameMath.cullRadians(additionalOrbitRadians + rotationAdjustment);
        setAdditionalOrbitRadians(orbitalRadians);
    }
    
    public float getAdditionalOrbitRadians() {
        return additionalOrbitRadians;
    }
    
    public final float getPerpendicularRadians() {
        return perpendicularRadians;
    }
    
    @Override
    public float calcOrbitRadians(float seconds) {
        return additionalOrbitRadians;
    }
    
    @Override
    public Quaternion calcTranslationRotation(float seconds) {
        Quaternion orbitRotation = super.calcTranslationRotation(seconds);
        Quaternion perpendicularRotation = calcPerpendicularTranslation();
        Quaternion translationRotation = orbitRotation.mult(perpendicularRotation);
        return translationRotation;
    }
    
    private Quaternion calcPerpendicularTranslation() {
        Quaternion perpendicularRotation = new Quaternion().fromAngleAxis(perpendicularRadians, Vector3f.UNIT_Z);
        return perpendicularRotation;
    }
    
}

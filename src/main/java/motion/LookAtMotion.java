package motion;

import com.jme3.math.Vector3f;

public class LookAtMotion extends TimedMotion {
    
    Vector3f currentLookAt;
    Vector3f currentUpVec;
    
    public LookAtMotion() {
        currentLookAt = Vector3f.ZERO;
        currentUpVec = Vector3f.ZERO;
    }
    
    public void play(float seconds, Vector3f startingLookAt, Vector3f startingUp) {
        if(!inMotion) {
            currentLookAt = startingLookAt;
            currentUpVec = startingUp;
        }
        play(seconds);
    }
    
    public void update(float tpf, Vector3f lookAt, Vector3f upVec) {
        if(!inMotion) {
            return;
        }
        if(update(tpf)) {
            float percentOfRemaining = tpf / (timeRemaining * .75f);
            currentLookAt = calcLookAt(lookAt, percentOfRemaining);
            currentUpVec = calcUpVec(upVec, percentOfRemaining);
        } else {
            finish(lookAt, upVec);
        }
    }
    
    public Vector3f getLookAt() {
        return currentLookAt;
    }
    
    public Vector3f getUpVec() {
        return currentUpVec;
    }
    
    private void finish(Vector3f lookAt, Vector3f upVec) {
        currentLookAt = lookAt;
        currentUpVec = upVec;
    }
    
    private Vector3f calcLookAt(Vector3f lookAt, float percent) {
        Vector3f partialLookAt = lookAt.subtract(currentLookAt);
        partialLookAt = currentLookAt.add(partialLookAt.mult(percent));
        return partialLookAt;
    }
    
    private Vector3f calcUpVec(Vector3f upVec, float percent) {
        Vector3f currentUpVecPos = currentUpVec.normalize();
        Vector3f upVecPos = upVec.normalize();
        Vector3f upTranslation = upVecPos.subtract(currentUpVecPos);
        Vector3f partialUpVec = currentUpVecPos.add(upTranslation.mult(percent)).normalize();
        return partialUpVec;
    }
    
}

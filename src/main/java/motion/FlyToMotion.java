package motion;

import com.jme3.math.Vector3f;

public class FlyToMotion {
    
    MoveToMotion moveToMotion;
    LookAtMotion lookAtMotion;
    
    public FlyToMotion() {
        moveToMotion = new MoveToMotion();
        lookAtMotion = new LookAtMotion();
    }
    
    public boolean isInMotion() {
        return moveToMotion.isInMotion() || lookAtMotion.isInMotion();
    }
    
    public void play(float seconds, Vector3f startingLocation, Vector3f startingLookAt, Vector3f startingUp) {
        moveToMotion.play(seconds, startingLocation);
        lookAtMotion.play(seconds, startingLookAt, startingUp);
    }
    
    public void update(float tpf, Vector3f location, Vector3f lookAt, Vector3f upVec) {
        moveToMotion.update(tpf, location);
        lookAtMotion.update(tpf, lookAt, upVec);
    }
    
    public Vector3f getLocation() {
        return moveToMotion.getLocation();
    }
    
    public Vector3f getLookAt() {
        return lookAtMotion.getLookAt();
    }
    
    public Vector3f getUpVec() {
        return lookAtMotion.getUpVec();
    }
    
    public float getTimeRemaining() {
        return Math.max(moveToMotion.getTimeRemaining(), lookAtMotion.getTimeRemaining());
    }
    
}

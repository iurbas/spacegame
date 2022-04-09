package motion;

import com.jme3.math.Vector3f;

public class MoveToMotion extends TimedMotion {
    
    Vector3f currentLocation;
    
    public MoveToMotion() {
        currentLocation = Vector3f.ZERO;
    }
    
    public void play(float seconds, Vector3f startingLocation) {
        if(!inMotion) {
            currentLocation = startingLocation;
        }
        play(seconds);
    }
    
    public void update(float tpf, Vector3f location) {
        if(!inMotion) {
            return;
        }
        if(update(tpf)) {
            float percentOfRemaining = tpf / timeRemaining;
            currentLocation = calcLocation(location, percentOfRemaining);
        } else {
            finish(location);
        }
    }
    
    public Vector3f getLocation() {
        return currentLocation;
    }
    
    private void finish(Vector3f location) {
        currentLocation = location;
    }
    
    private Vector3f calcLocation(Vector3f location, float percent) {
        Vector3f partialLocation = location.subtract(currentLocation);
        partialLocation = currentLocation.add(partialLocation.mult(percent));
        return partialLocation;
    }
    
}

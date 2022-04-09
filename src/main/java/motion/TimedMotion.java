package motion;

public class TimedMotion {
    
    protected boolean inMotion;
    
    protected float timer;
    protected float timeRemaining;
    
    public TimedMotion() {
        inMotion = false;
        timer = 0f;
        timeRemaining = 0f;
    }
    
    public boolean isInMotion() {
        return inMotion;
    }
    
    public float getTimeRemaining() {
        return timeRemaining;
    }
    
    protected void play(float seconds) {
        timer = seconds;
        timeRemaining = seconds;
        inMotion = true;
    }
    
    protected boolean update(float tpf) {
        if(!inMotion) {
            return false;
        }
        timeRemaining -= tpf;
        if(timer <= 0f || timeRemaining <= 0f) {
            finish();
            return false;
        }
        float percentOfRemaining = tpf / timeRemaining;
        if(percentOfRemaining >= 1f) {
            finish();
            return false;
        }
        return true;
    }
    
    private void finish() {
        timer = 0f;
        timeRemaining = 0f;
        inMotion = false;
    }
    
}

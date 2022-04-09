package math;

import java.util.Random;

public class Seed {
    
    public static final Random random = new Random(0);
    
    public static int randInt(int min, int max) {
        if(max < min) {
            int newMin = max;
            max = min;
            min = newMin;
        }
        return min + random.nextInt(max - min);
    }
    
    public static float randFloat(float min, float max) {
        if(max < min) {
            float newMin = max;
            max = min;
            min = newMin;
        }
        return min + (random.nextFloat() * (max - min));
    }
    
}

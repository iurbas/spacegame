package math;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;

public class GameMath {
    
    public static int bound(int value, int min, int max) {
        if(value < min) value = min;
        if(value > max) value = max;
        return value;
    }
    
    public static float bound(float value, float min, float max) {
        if(value < min) value = min;
        if(value > max) value = max;
        return value;
    }
    
    public static float cullRadians(float radians) {
        radians %= FastMath.TWO_PI;
        if(radians < 0) { radians += FastMath.TWO_PI; }
        return radians;
    }
    
    public static float mpsToRps(float mps, float radius) {
        return (mps / (radius * FastMath.TWO_PI));
    }
    
    public static float rpsToMps(float rps, float radius) {
        return (rps * (radius * FastMath.TWO_PI));
    }
    
    public static ColorRGBA mixColors(ColorRGBA color1, ColorRGBA color2, int iterations) {
        ColorRGBA newColor = new ColorRGBA(color1);
        for(int i = 0; i < iterations; i++) {
            float red = (newColor.getRed() + color2.getRed()) / 2;
            float green = (newColor.getGreen() + color2.getGreen()) / 2;
            float blue = (newColor.getBlue() + color2.getBlue()) / 2;
            float alpha = (newColor.getAlpha() + color2.getAlpha()) / 2;
            newColor.set(red, green, blue, alpha);
        }
        return newColor;
    }
    
    public static ColorRGBA mixColors(ColorRGBA color1, ColorRGBA color2) {
        return mixColors(color1, color2, 1);
    }
    
}

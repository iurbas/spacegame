package assets.universe;

import java.util.ArrayList;
import java.util.List;
import assets.textures.CombinedTexture;

public class CelestialTextures {
    
    private static List<CombinedTexture> stars = new ArrayList<CombinedTexture>();
    private static List<CombinedTexture> planets = new ArrayList<CombinedTexture>();
    private static List<CombinedTexture> moons = new ArrayList<CombinedTexture>();
    
    public static void loadStarTexture(CombinedTexture texture) {
        stars.add(texture);
    }
    
    public static void loadPlanetTexture(CombinedTexture texture) {
        planets.add(texture);
    }
    
    public static void loadMoonTexture(CombinedTexture texture) {
        moons.add(texture);
    }
    
    public static CombinedTexture getStarTexture(int index) {
        return stars.get(index);
    }
    
    public static CombinedTexture getPlanetTexture(int index) {
        return planets.get(index);
    }
    
    public static CombinedTexture getMoonTexture(int index) {
        return moons.get(index);
    }
    
    public static int getNumStarTextures() {
        return stars.size();
    }
    
    public static int getNumPlanetTextures() {
        return planets.size();
    }
    
    public static int getNumMoonTextures() {
        return moons.size();
    }
    
}

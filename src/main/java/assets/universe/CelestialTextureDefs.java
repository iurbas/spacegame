package assets.universe;

import java.util.Arrays;
import java.util.List;
import assets.textures.CombinedTextureDef;

public class CelestialTextureDefs {
    
    // Stars
    public static final CombinedTextureDef Sun      = new CombinedTextureDef("Textures_bw/Stars/Sun.jpg");
    public static final List<CombinedTextureDef> Stars = Arrays.asList(Sun);
    
    // Planets
    public static final CombinedTextureDef Mercury  = new CombinedTextureDef("Textures/Planets/Mercury.jpg",   "Textures/Planets/Mercury_normal.jpg");
    public static final CombinedTextureDef Venus    = new CombinedTextureDef("Textures/Planets/Venus.jpg");
    public static final CombinedTextureDef Earth    = new CombinedTextureDef("Textures/Planets/Earth.jpg",     "Textures/Planets/Earth_normal.jpg");
    public static final CombinedTextureDef Mars     = new CombinedTextureDef("Textures/Planets/Mars.jpg",      "Textures/Planets/Mars_normal.jpg");
    public static final CombinedTextureDef Jupiter  = new CombinedTextureDef("Textures/Planets/Jupiter.jpg");
    public static final CombinedTextureDef Saturn   = new CombinedTextureDef("Textures/Planets/Saturn.jpg");
    public static final CombinedTextureDef Uranus   = new CombinedTextureDef("Textures/Planets/Uranus.jpg");
    public static final CombinedTextureDef Neptune  = new CombinedTextureDef("Textures/Planets/Neptune.jpg");
    public static final List<CombinedTextureDef> Planets = Arrays.asList(Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune);
    
    // Moons
    public static final CombinedTextureDef Moon     = new CombinedTextureDef("Textures/Moons/Moon.jpg",        "Textures/Moons/Moon_normal.jpg");
    public static final List<CombinedTextureDef> Moons = Arrays.asList(Moon);
    
}

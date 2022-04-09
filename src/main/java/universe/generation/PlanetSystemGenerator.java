package universe.generation;

import com.jme3.asset.AssetManager;
import math.Seed;
import universe.Moon;
import universe.Planet;
import universe.PlanetSystem;
import assets.geometries.ColoredGeometry;

public class PlanetSystemGenerator extends CelestialGenerator {
    
    public PlanetSystemGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public PlanetSystem generate(float radius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius);
        float planetScale = radius / 10f;
        float minPlanetRadius = planetScale * 1;
        float maxPlanetRadius = planetScale * 5;
        float planetRadius = Seed.randFloat(minPlanetRadius, maxPlanetRadius);
        Planet planet = generatePlanet(planetRadius);
        PlanetSystem planetSystem = new PlanetSystem(radius, collisionSphere, planet);
        generateMoons(planetSystem, planetScale, planetRadius);
        return planetSystem;
    }
    
    private Planet generatePlanet(float radius) {
        PlanetGenerator generator = new PlanetGenerator(assetManager);
        return generator.generate(radius);
    }
    
    private void generateMoons(PlanetSystem planetSystem, float planetScale, float planetRadius) {
        float moonScale = planetScale / 10f;
        float minMoonRadius = moonScale * 4;
        float maxMoonRadius = moonScale * 8;
        
        float moonDensity = .25f;
        float minDistanceBetweenMoons = minMoonRadius;
        float minOrbitRadius = planetRadius * 1.5f;
        
        float currentOrbitRadius = minOrbitRadius;
        while(currentOrbitRadius < planetSystem.getRadius()) {
            boolean placeMoon = (Seed.randFloat(0f, 1f) < moonDensity);
            if(placeMoon) {
                float moonRadius = Seed.randFloat(minMoonRadius, maxMoonRadius);
                currentOrbitRadius += moonRadius;
                if(currentOrbitRadius > planetSystem.getRadius()) break;
                Moon moon = generateMoon(planetSystem, moonRadius, currentOrbitRadius);
                planetSystem.attachMoon(moon);
                ColoredGeometry orbitRing = generateOrbitRing(moon.getTransformation());
                planetSystem.attachSpatial(orbitRing);
                planetSystem.registerDetailSpatial(orbitRing);
                moon.registerColoredSpatial(orbitRing);
                moon.registerDetailSpatial(orbitRing);
                currentOrbitRadius += moonRadius;
            }
            currentOrbitRadius += minDistanceBetweenMoons;
        }
    }
    
    private Moon generateMoon(PlanetSystem planetSystem, float moonRadius, float orbitRadius) {
        MoonGenerator generator = new MoonGenerator(assetManager);
        Moon moon = generator.generate(moonRadius);
        moon.getTransformation().setOrbitRadius(orbitRadius);
        setOrbitRpsFromMass(moon, planetSystem.getRadius() * .01f);
        randomizeOrbit(moon);
        return moon;
    }
    
}

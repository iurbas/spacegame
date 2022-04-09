package universe.generation;

import com.jme3.asset.AssetManager;
import math.Seed;
import universe.PlanetSystem;
import universe.Star;
import universe.StarSystem;
import assets.geometries.ColoredGeometry;

public class StarSystemGenerator extends CelestialGenerator {
    
    public StarSystemGenerator(AssetManager assetManager) {
        super(assetManager);
    }
    
    public StarSystem generate(float radius) {
        ColoredGeometry collisionSphere = generateCollisionGeometry(radius / 3f);
        float starScale = radius / 40f;
        float minStarRadius = starScale * 2f;
        float maxStarRadius = starScale * 4f;
        float starRadius = Seed.randFloat(minStarRadius, maxStarRadius);
        Star star = generateStar(starRadius, radius);
        StarSystem starSystem = new StarSystem(radius, collisionSphere, star);
        generatePlanets(starSystem, starScale, starRadius);
        return starSystem;
    }
    
    private Star generateStar(float radius, float systemRadius) {
        StarGenerator generator = new StarGenerator(assetManager);
        return generator.generate(radius, systemRadius);
    }
    
    private void generatePlanets(StarSystem starSystem, float starScale, float starRadius) {
        float planetSystemScale = starScale / 4f;
        float minPlanetSystemRadius = planetSystemScale * 4;
        float maxPlanetSystemRadius = planetSystemScale * 8;
        
        float planetSystemDensity = .25f;
        float minDistanceBetweenPlanets = minPlanetSystemRadius;
        float minOrbitRadius = starRadius * 2f;
        
        float currentOrbitRadius = minOrbitRadius;
        while(currentOrbitRadius < starSystem.getRadius()) {
            boolean placePlanet = (Seed.randFloat(0f, 1f) < planetSystemDensity);
            if(placePlanet) {
                float planetSystemRadius = Seed.randFloat(minPlanetSystemRadius, maxPlanetSystemRadius);
                currentOrbitRadius += planetSystemRadius;
                if(currentOrbitRadius > starSystem.getRadius()) break;
                PlanetSystem planetSystem = generatePlanetSystem(starSystem, planetSystemRadius, currentOrbitRadius);
                starSystem.attachPlanetSystem(planetSystem);
                ColoredGeometry orbitRing = generateOrbitRing(planetSystem.getTransformation());
                starSystem.attachSpatial(orbitRing);
                starSystem.registerDetailSpatial(orbitRing);
                planetSystem.registerColoredSpatial(orbitRing);
                planetSystem.registerDetailSpatial(orbitRing);
                currentOrbitRadius += planetSystemRadius;
            }
            currentOrbitRadius += minDistanceBetweenPlanets;
        }
    }
    
    private PlanetSystem generatePlanetSystem(StarSystem starSystem, float planetSystemRadius, float orbitRadius) {
        PlanetSystemGenerator generator = new PlanetSystemGenerator(assetManager);
        PlanetSystem planetSystem = generator.generate(planetSystemRadius);
        planetSystem.getTransformation().setOrbitRadius(orbitRadius);
        setOrbitRpsFromMass(planetSystem, starSystem.getRadius() * .1f);
        randomizeOrbit(planetSystem);
        return planetSystem;
    }
    
}

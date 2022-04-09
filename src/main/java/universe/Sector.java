package universe;

import com.jme3.scene.Node;
import java.util.ArrayList;
import assets.geometries.ColoredGeometry;

public class Sector extends Celestial {
    
    public Sector(float width, ColoredGeometry collisionGeometry) {
        super(width, collisionGeometry);
    }
    
    public void attachStarSystem(StarSystem starSystem) {
        attachCelestial(starSystem);
    }
    
    @Override
    public ArrayList<Node> getCollisionNodes() {
        ArrayList<Node> collisionNodes = new ArrayList<Node>();
        collisionNodes.add(getCollisionNode());
        return collisionNodes;
    }
    
}

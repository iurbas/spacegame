package input.handlers;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import navigation.CelestialNavigator;

public class NavigationInputHandler {
    
    private CelestialNavigator navigator;
    
    public NavigationInputHandler(CelestialNavigator navigator) {
        this.navigator = navigator;
    }
    
    public ArrayList<Node> getCollisionNodes() {
        return navigator.getCollisionNodes();
    }
    
    public void handleLeftClick(Geometry selectedNavigationGeometry) {
        navigator.select(selectedNavigationGeometry);
    }
    
    public void handleRightClick(Geometry selectedNavigationGeometry) {
        handleBack();
    }
    
    public void handleMouseMove(Geometry selectedNavigationGeometry) {
        navigator.highlight(selectedNavigationGeometry);
    }
    
    public void handleBack() {
        navigator.back();
    }
    
    public void handleShowDetails() {
        navigator.showDetails();
    }
    
    public void handleShowFullDetails() {
        navigator.showFullDetails();
    }
    
}

package navigation;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import gui.Gui;
import java.util.ArrayList;
import universe.camera.CelestialCamera;
import universe.Celestial;

public class CelestialNavigator {
    
    private Celestial celestial;
    private CelestialCamera camera;
    private Gui gui;
    private ArrayList<Node> collisionNodes;
    private Celestial highlightedCelestial;
    private boolean showDetails;
    private boolean showFullDetails;
    
    public CelestialNavigator(Celestial celestial, CelestialCamera camera, Gui gui) {
        this.celestial = null;
        this.camera = camera;
        this.gui = gui;
        collisionNodes = new ArrayList<Node>();
        highlightedCelestial = null;
        showDetails = true;
        showFullDetails = false;
        select(celestial);
    }
    
    private void updateCollisionNodes() {
        collisionNodes = celestial.getCollisionNodes();
    }
    
    public final ArrayList<Node> getCollisionNodes() {
        return collisionNodes;
    }
    
    public final void select(Geometry selectedNavigationGeometry) {
        Celestial selectedCelestial = findCelestial(selectedNavigationGeometry);
        if(selectedCelestial != null) {
            if(!selectedCelestial.equals(celestial)) {
                select(selectedCelestial);
            }
        }
    }
    
    private void select(Celestial selectedCelestial) {
        if(celestial != null) {
            showCelestialDetails(celestial, false, false);
            if(selectedCelestial.isSiblingOrParentOf(celestial)) {
                celestial.setLOD(Celestial.LOD.LOW);
                celestial.enableCollisionGeometry(true);
            }
        }
        showCelestialDetails(selectedCelestial, showDetails, showFullDetails);
        selectedCelestial.setLOD(Celestial.LOD.HIGH);
        selectedCelestial.enableCollisionGeometry(false);
        camera.orbit(selectedCelestial);
        gui.showCelestialGui(selectedCelestial);
        celestial = selectedCelestial;
        updateCollisionNodes();
    }
    
    public final void highlight(Geometry selectedNavigationGeometry) {
        if(highlightedCelestial != null) {
            highlightedCelestial.highlight(false);
            highlightedCelestial = null;
            showCelestialDetails(celestial, showDetails, showFullDetails);
        }
        if(selectedNavigationGeometry != null) {
            Celestial selectedCelestial = findCelestial(selectedNavigationGeometry);
            if(selectedCelestial != null) {
                if(!selectedCelestial.equals(celestial)) {
                    selectedCelestial.highlight(true);
                    highlightedCelestial = selectedCelestial;
                }
            }
        }
    }
    
    public final void back() {
        if(celestial.hasParent()) {
            select(celestial.getParent());
        }
    }
    
    public final void showDetails() {
        showDetails = !showDetails;
        showCelestialDetails(celestial, showDetails, showFullDetails);
    }
    
    public final void showFullDetails() {
        showFullDetails = !showFullDetails;
        if(!showDetails) {
            showDetails = showFullDetails;
        }
        showCelestialDetails(celestial, showDetails, showFullDetails);
    }
    
    private void showCelestialDetails(Celestial celestial, boolean showDetails, boolean fullDetails) {
        celestial.showDetails(showDetails);
        Celestial currentCelestial = celestial;
        while(currentCelestial.hasParent()) {
            currentCelestial = currentCelestial.getParent();
            currentCelestial.showDetails(showDetails && fullDetails);
        }
    }
    
    private Celestial findCelestial(Geometry selectedNavigationGeometry) {
        if(selectedNavigationGeometry == null) {
            return null;
        }
        Celestial selectedCelestial = celestial.findCelestialFromCollision(selectedNavigationGeometry);
        if(selectedCelestial == null && celestial.hasParent()) {
            selectedCelestial = celestial.getParent().findCelestialFromCollision(selectedNavigationGeometry);
        }
        return selectedCelestial;
    }
    
}

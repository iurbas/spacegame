package input.handlers;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;

public class MouseInputHandler {
    
    private GuiInputHandler guiInputHandler;
    private NavigationInputHandler navigationInputHandler;
    private Camera camera;
    
    private Geometry selectedGuiGeometry;
    private Geometry selectedNavigationGeometry;
    
    public MouseInputHandler(GuiInputHandler guiInputHandler, NavigationInputHandler navigationInputHandler, Camera camera) {
        this.guiInputHandler = guiInputHandler;
        this.navigationInputHandler = navigationInputHandler;
        this.camera = camera;
        selectedGuiGeometry = null;
        selectedNavigationGeometry = null;
    }
    
    public void handleLeftClick(Vector2f cursorPosition, boolean isPressed) {
        handleMouseMove(cursorPosition);
        guiInputHandler.handleLeftClick(selectedGuiGeometry, isPressed);
        if(selectedGuiGeometry != null) {
            return;
        }
        if(!isPressed) {
            return;
        }
        navigationInputHandler.handleLeftClick(selectedNavigationGeometry);
    }
    
    public void handleRightClick(Vector2f cursorPosition, boolean isPressed) {
        handleMouseMove(cursorPosition);
        guiInputHandler.handleRightClick(selectedGuiGeometry, isPressed);
        if(selectedGuiGeometry != null) {
            return;
        }
        if(!isPressed) {
            return;
        }
        navigationInputHandler.handleRightClick(selectedNavigationGeometry);
    }
    
    public void handleMouseMove(Vector2f cursorPosition) {
        updateSelectedGeometries(cursorPosition);
        guiInputHandler.handleMouseMove(selectedGuiGeometry);
        if(selectedGuiGeometry == null) {
            navigationInputHandler.handleMouseMove(selectedNavigationGeometry);
        } else {
            navigationInputHandler.handleMouseMove(null);
        }
    }
    
    private void updateSelectedGeometries(Vector2f cursorPosition) {
        selectedGuiGeometry = getSelectedGuiGeometry(cursorPosition);
        if(selectedGuiGeometry == null) {
            selectedNavigationGeometry = getSelectedNavigationGeometry(cursorPosition);
        }
    }
    
    private Geometry getSelectedGuiGeometry(Vector2f cursorPosition) {
        Vector3f origin = new Vector3f(cursorPosition.getX(), cursorPosition.getY(), 1000000f);
        Vector3f direction = new Vector3f(0f, 0f, -1f);
        Ray ray = new Ray(origin, direction);
        return getSelectedGeometry(guiInputHandler.getCollisionNode(), ray);
    }
    
    private Geometry getSelectedNavigationGeometry(Vector2f cursorPosition) {
        Ray ray = calculateRayFromCursorPosition(cursorPosition);
        ArrayList<Node> collisionNodes = navigationInputHandler.getCollisionNodes();
        Geometry selectedGeometry;
        for(Node node : collisionNodes) {
            selectedGeometry = getSelectedGeometry(node, ray);
            if(selectedGeometry != null) {
                return selectedGeometry;
            }
        }
        return null;
    }
    
    private Geometry getSelectedGeometry(Node node, Ray ray) {
        if(node == null) {
            return null; // no active collision tree
        }
        CollisionResults results = new CollisionResults();
        node.collideWith(ray, results);
        if(results.size() <= 0) {
            return null; // nothing clicked on
        }
        CollisionResult closest = results.getClosestCollision();    // Get closest geometry that was clicked on
        if(closest == null) {
            return null; // should never happen
        }
        return closest.getGeometry();
    }
    
    private Ray calculateRayFromCursorPosition(Vector2f cursorPosition) {
        Vector3f origin = camera.getWorldCoordinates(cursorPosition, 0f);
        Vector3f direction = camera.getWorldCoordinates(cursorPosition, 1f);
        direction.subtractLocal(origin);
        return new Ray(origin, direction);
    }
    
}

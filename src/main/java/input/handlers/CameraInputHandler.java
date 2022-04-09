package input.handlers;

import universe.camera.CelestialCamera;

public class CameraInputHandler {
    
    private CelestialCamera camera;
    
    public CameraInputHandler(CelestialCamera camera) {
        this.camera = camera;
    }
    
    public void handleLeft(float value) {
        camera.moveLeft(value);
    }

    public void handleRight(float value) {
        camera.moveRight(value);
    }

    public void handleUp(float value) {
        camera.moveUp(value);
    }

    public void handleDown(float value) {
        camera.moveDown(value);
    }

    public void handleIn(float value) {
        camera.zoomIn(value);
    }

    public void handleOut(float value) {
        camera.zoomOut(value);
    }

    public void handleWheelIn(float value) {
        camera.zoomIn(.1f * value);
    }

    public void handleWheelOut(float value) {
        camera.zoomOut(.1f * value);
    }
    
    public void handleRotateLeft(float value) {
        //camera.rotateLeft(value);
    }

    public void handleRotateRight(float value) {
        //camera.rotateRight(value);
    }

    public void handleRotateUp(float value) {
        //camera.rotateUp(value);
    }

    public void handleRotateDown(float value) {
        //camera.rotateDown(value);
    }
    
}

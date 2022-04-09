
package universe.camera;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import math.GameMath;
import motion.FlyToMotion;
import universe.Celestial;

public class CelestialCamera {
    
    private Camera camera;
    private Node cameraNode;
    private CelestialCameraTransformation cameraTransformation;
    
    private Celestial celestial;
    
    private float moveSpeedFactor;
    private float moveSpeed;
    private float zoomSpeed;
    
    private FlyToMotion flyToMotion;
    
    public CelestialCamera(Camera cam) {
        camera = cam;
        cameraNode = new Node();
        cameraTransformation = new CelestialCameraTransformation();
        celestial = null;
        moveSpeedFactor = 1f;
        moveSpeed = 1f;
        zoomSpeed = 1f;
        flyToMotion = new FlyToMotion();
    }
    
    public void setFrustum(float near, float far) {
        camera.setFrustumPerspective(45f, (float) camera.getWidth() / camera.getHeight(), near, far);
    }
    
    public void setMoveSpeed(float speed) {
        moveSpeed = speed;
    }
    
    public void setZoomSpeed(float speed) {
        zoomSpeed = speed;
    }
    
    public void moveLeft(float value) {
        moveOrbitally(-value);
    }

    public void moveRight(float value) {
        moveOrbitally(value);
    }

    public void moveUp(float value) {
        movePerpendicularly(value);
    }

    public void moveDown(float value) {
        movePerpendicularly(-value);
    }

    public void zoomIn(float value) {
        zoom(value);
    }

    public void zoomOut(float value) {
        zoom(-value);
    }
    
    public void moveOrbitally(float value) {
        value = moveSpeed * value;
        value = isFlipped() ? -value : value;
        float radians = GameMath.cullRadians(cameraTransformation.getAdditionalOrbitRadians() + value);
        cameraTransformation.setAdditionalOrbitRadians(radians);
    }
    
    public void movePerpendicularly(float value) {
        value = moveSpeed * value;
        float radians = GameMath.cullRadians(cameraTransformation.getPerpendicularRadians() + value);
        cameraTransformation.setPerpendicularRadians(radians);
    }
    
    public void zoom(float value) {
        value = moveSpeedFactor * zoomSpeed * value;
        float orbitalRadius = cameraTransformation.getOrbitRadius() - value;
        if(celestial != null) {
            if(orbitalRadius < calcMinOrbitRadius(celestial) ||
               orbitalRadius > calcMaxOrbitRadius(celestial)) {
                return;
            }
        }
        cameraTransformation.setOrbitRadius(orbitalRadius);
    }
    
    public void orbit(Celestial orbitCelestial) {
        adjustOrbitPosition(orbitCelestial);
        attachTo(orbitCelestial);
        startMotionTo(orbitCelestial);
        celestial = orbitCelestial;
    }
    
    private void adjustOrbitPosition(Celestial orbitCelestial) {
        adjustOrbitRadius(orbitCelestial);
        adjustOrbitRadians(orbitCelestial);
        adjustPerpendicularRadians();
    }
    
    private void adjustOrbitRadius(Celestial orbitCelestial) {
        float orbitRadius = calcMinOrbitRadius(orbitCelestial) + (orbitCelestial.getRadius() /2f);
//        if(celestial != null) {
//            float currentOrbitRadius = cameraTransformation.getOrbitRadius();
//            if(orbitCelestial.isChildOf(celestial) && orbitRadius > currentOrbitRadius ||
//               orbitCelestial.isParentOf(celestial) && orbitRadius < currentOrbitRadius) {
//                if(currentOrbitRadius < calcMinOrbitRadius(orbitCelestial)) {
//                    orbitRadius = calcMinOrbitRadius(orbitCelestial);
//                } else if(currentOrbitRadius > calcMaxOrbitRadius(orbitCelestial)) {
//                    orbitRadius = calcMaxOrbitRadius(orbitCelestial);
//                } else {
//                    orbitRadius = currentOrbitRadius;
//                }
//            }
//        }
        cameraTransformation.setOrbitRadius(orbitRadius);
    }
    
    private void adjustOrbitRadians(Celestial orbitCelestial) {
        if(celestial != null) {
            if(orbitCelestial.isChildOf(celestial)) {
                cameraTransformation.adjustOrbitRadiansMoveInTo(orbitCelestial.getTransformation());
            } else if(orbitCelestial.isSiblingOf(celestial)) {
                cameraTransformation.adjustOrbitRadiansMoveOutFrom(celestial.getTransformation());
                cameraTransformation.adjustOrbitRadiansMoveInTo(orbitCelestial.getTransformation());
            } else if(orbitCelestial.isParentOf(celestial)) {
                cameraTransformation.adjustOrbitRadiansMoveOutFrom(celestial.getTransformation());
            }
        }
    }
    
    private void adjustPerpendicularRadians() {
        float perpendicularRadians = cameraTransformation.getPerpendicularRadians();
        if(perpendicularRadians > FastMath.TWO_PI * .20f && perpendicularRadians < FastMath.TWO_PI * .30f) {
            if(perpendicularRadians < FastMath.TWO_PI * .25f) {
                perpendicularRadians = FastMath.TWO_PI * .20f;
            } else {
                perpendicularRadians = FastMath.TWO_PI * .30f;
            }
        }
        else if(perpendicularRadians > FastMath.TWO_PI * .70f && perpendicularRadians < FastMath.TWO_PI * .80f) {
            if(perpendicularRadians < FastMath.TWO_PI * .75f) {
                perpendicularRadians = FastMath.TWO_PI * .70f;
            } else {
                perpendicularRadians = FastMath.TWO_PI * .80f;
            }
        }
        cameraTransformation.setPerpendicularRadians(perpendicularRadians);
    }
    
    private void attachTo(Celestial orbitCelestial) {
        cameraNode.removeFromParent();
        orbitCelestial.getNode().attachChild(cameraNode);
        moveSpeedFactor = orbitCelestial.getRadius();
    }
    
    private void startMotionTo(Celestial destination) {
        float timer = 2f;
        boolean shortenTimer = !destination.hasChildren();
        if(celestial != null) {
            if(!celestial.hasChildren()) shortenTimer = true;
        }
        if(shortenTimer) timer /= 2f;
        Vector3f startingLocation = camera.getLocation();
        Vector3f startingLookAt = calcLookAtLocation(celestial);
        Vector3f startingUpVec = camera.getUp();
        flyToMotion.play(timer, startingLocation, startingLookAt, startingUpVec);
    }
    
    public Camera getCamera() {
        return camera;
    }
    
    public boolean isFlipped() {
        return (cameraTransformation.getPerpendicularRadians() > FastMath.HALF_PI && cameraTransformation.getPerpendicularRadians() < (3f * FastMath.HALF_PI));
    }
    
    public boolean isInMotion() {
        return flyToMotion.isInMotion();
    }
    
    public void update(float tpf, float seconds) {
        cameraTransformation.update(seconds);
        cameraNode.setLocalTranslation(cameraTransformation.getLocalTranslation());
        cameraNode.setLocalRotation(cameraTransformation.getLocalRotation());
        Vector3f location = cameraNode.getWorldTranslation();
        Vector3f lookAt = calcLookAtLocation(celestial);
        Vector3f upVec = calcUpVector(celestial);
        if(isInMotion()) {
            location = calcCameraWorldLocation(celestial, seconds + flyToMotion.getTimeRemaining());
            flyToMotion.update(tpf, location, lookAt, upVec);
            location = flyToMotion.getLocation();
            lookAt = flyToMotion.getLookAt();
            upVec = flyToMotion.getUpVec();
        }
        camera.setLocation(location);
        camera.lookAt(lookAt, upVec);
    }
    
    public Vector3f calcCameraWorldLocation(Celestial celestial, float seconds) {
        Node fakeCameraNode = new Node();
        fakeCameraNode.setLocalTranslation(cameraTransformation.calcTranslation(seconds));
        fakeCameraNode.setLocalRotation(cameraTransformation.calcRotation(seconds));
        Node currentNode = new Node();
        currentNode.attachChild(fakeCameraNode);
        Celestial currentCelestial = celestial;
        while(currentCelestial != null) {
            currentNode.setLocalTranslation(currentCelestial.getTransformation().calcTranslation(seconds));
            currentNode.setLocalRotation(currentCelestial.getTransformation().calcRotation(seconds));
            if(currentCelestial.hasParent()) {
                Node parentNode = new Node();
                parentNode.attachChild(currentNode);
                currentNode = parentNode;
                currentCelestial = currentCelestial.getParent();
            } else {
                currentCelestial = null;
            }
        }
        return fakeCameraNode.getWorldTranslation();
    }
    
    private Vector3f calcLookAtLocation(Celestial lookAtCelestial) {
        if(lookAtCelestial == null) {
            return Vector3f.ZERO;
        } else {
            return lookAtCelestial.getNode().getWorldTranslation();
        }
    }
    
    private Vector3f calcUpVector(Celestial lookAtCelestial) {
        Vector3f upVec = Vector3f.UNIT_Y;
        if(lookAtCelestial != null) {
            Quaternion quat = lookAtCelestial.getNode().getWorldRotation();
            upVec = quat.mult(upVec);
        }
        float upDirection = isFlipped() ? -1f : 1f;
        return upVec.mult(upDirection);
    }
    
    private float calcMinOrbitRadius(Celestial lookAtCelestial) {
        return lookAtCelestial.getRadius() * (lookAtCelestial.getChildren().size() > 1 ? 2f : 4f);
    }
    
    private float calcMaxOrbitRadius(Celestial lookAtCelestial) {
        return lookAtCelestial.getRadius() * 6f;
    }
    
}

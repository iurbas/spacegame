package input.listeners;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import input.handlers.CameraInputHandler;

public class CameraInputListener  implements AnalogListener {
    
    private InputManager inputManager;
    private CameraInputHandler handler;
    
    public CameraInputListener(InputManager inputManager, CameraInputHandler handler) {
        this.inputManager = inputManager;
        this.handler = handler;
        initKeys();
    }
    
    public final void initKeys() {
        inputManager.addMapping("Camera_Left",          new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Camera_Right",         new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Camera_Up",            new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Camera_Down",          new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Camera_In_Key",        new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("Camera_Out_Key",       new KeyTrigger(KeyInput.KEY_Q), new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("Camera_In_Wheel",      new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping("Camera_Out_Wheel",     new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        
        //inputManager.addMapping("Camera_Rotate_Left",   new MouseAxisTrigger(MouseInput.AXIS_X, true),  new KeyTrigger(KeyInput.KEY_LEFT));
        //inputManager.addMapping("Camera_Rotate_Right",  new MouseAxisTrigger(MouseInput.AXIS_X, false), new KeyTrigger(KeyInput.KEY_RIGHT));
        //inputManager.addMapping("Camera_Rotate_Up",     new MouseAxisTrigger(MouseInput.AXIS_Y, false), new KeyTrigger(KeyInput.KEY_UP));
        //inputManager.addMapping("Camera_Rotate_Down",   new MouseAxisTrigger(MouseInput.AXIS_Y, true),  new KeyTrigger(KeyInput.KEY_DOWN));
        
        inputManager.addListener(this,
                "Camera_Left",
                "Camera_Right",
                "Camera_Up",
                "Camera_Down",
                "Camera_In_Key",
                "Camera_Out_Key",
                "Camera_In_Wheel",
                "Camera_Out_Wheel"
                //"Camera_Rotate_Left",
                //"Camera_Rotate_Right",
                //"Camera_Rotate_Up",
                //"Camera_Rotate_Down"
                );
    }
    
    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("Camera_Left"))         { handler.handleLeft(value);         }
        if (name.equals("Camera_Right"))        { handler.handleRight(value);        }
        if (name.equals("Camera_Up"))           { handler.handleUp(value);           }
        if (name.equals("Camera_Down"))         { handler.handleDown(value);         }
        if (name.equals("Camera_In_Key"))       { handler.handleIn(value);           }
        if (name.equals("Camera_Out_Key"))      { handler.handleOut(value);          }
        if (name.equals("Camera_In_Wheel"))     { handler.handleWheelIn(value);      }
        if (name.equals("Camera_Out_Wheel"))    { handler.handleWheelOut(value);     }
        //if (name.equals("Camera_Rotate_Left"))  { handler.handleRotateLeft(value);   }
        //if (name.equals("Camera_Rotate_Right")) { handler.handleRotateRight(value);  }
        //if (name.equals("Camera_Rotate_Up"))    { handler.handleRotateUp(value);     }
        //if (name.equals("Camera_Rotate_Down"))  { handler.handleRotateDown(value);   }
    }
    
}

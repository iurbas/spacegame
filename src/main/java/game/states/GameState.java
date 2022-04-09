package game.states;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.system.Timer;
import game.Game;

public abstract class GameState extends AbstractAppState {
    
    private GameState nextState;
    
    protected Game app;
    protected AppStateManager stateManager;
    protected AssetManager assetManager;
    protected InputManager inputManager;
    protected Node rootNode;
    protected Node guiNode;
    protected Camera cam;
    protected FlyByCamera flyCam;
    protected AppSettings settings;
    protected Timer timer;
    
    public GameState() {
        this.nextState = null;
    }
    
    public final void setNextState(GameState nextState) {
        this.nextState = nextState;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (Game) app;
        this.stateManager = stateManager;
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.rootNode = this.app.getRootNode();
        this.guiNode = this.app.getGuiNode();
        this.cam = this.app.getCamera();
        this.flyCam = this.app.getFlyByCamera();
        this.settings = this.app.getSettings();
        this.timer = this.app.getTimer();
        init();
    }
    
    public abstract void init();
    
    public void nextState() {
        setEnabled(false);
        stateManager.detach(this);
        if(nextState != null) {
            stateManager.attach(nextState);
        }
    }
    
}

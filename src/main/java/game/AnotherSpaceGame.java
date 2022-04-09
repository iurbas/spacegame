package game;

import com.jme3.system.AppSettings;
import game.states.LoadingState;
import game.states.PlayState;

public class AnotherSpaceGame extends Game {
    
    public static void main(String[] args) {
        AnotherSpaceGame app = new AnotherSpaceGame();
        app.initSettings();
        app.start();
    }
    
    protected void initSettings() {
        setShowSettings(false);
        AppSettings gameSettings = new AppSettings(true);
        gameSettings.setResolution(1600, 1024);
        gameSettings.setFullscreen(false);
        gameSettings.setFrequency(60);
        gameSettings.setVSync(false);
        gameSettings.setTitle("Another Space Game");
        setSettings(gameSettings);
    }

    @Override
    public void initGameStates() {
        // Create states
        LoadingState loadingState = new LoadingState();
        PlayState playState = new PlayState();
        
        // Set state order
        loadingState.setNextState(playState);
        
        // Attach starting state
        stateManager.attach(loadingState);
    }
}

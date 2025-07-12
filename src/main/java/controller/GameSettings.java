package controller;
import javafx.scene.input.KeyCode;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.security.Key;
import java.util.HashMap;

public class GameSettings implements Serializable {
    private int difficulty = 2;
    private boolean blackAndWhite = false;
    private boolean muted = false;
    private HashMap<String, KeyCode> keys = new HashMap<>();

    public GameSettings(){
        this.keys.put("move right", KeyCode.RIGHT);
        this.keys.put("move left", KeyCode.LEFT);
        this.keys.put("move up", KeyCode.UP);
        this.keys.put("move down", KeyCode.DOWN);
        this.keys.put("drop bomb", KeyCode.SPACE);
        this.keys.put("drop R bomb", KeyCode.R);
        this.keys.put("pause", KeyCode.ESCAPE);
    }
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public HashMap<String, KeyCode> getKeys() {
        return keys;
    }

    public void putInKeys(String key, KeyCode value){
        keys.put(key, value);
    }
}

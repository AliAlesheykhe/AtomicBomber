package model;

import javafx.scene.Node;
import view.GameLauncher;

import javafx.scene.Group;

import java.util.ArrayList;

public class Game {
    private GameLauncher gameLauncher;
    private Group targets = new Group();
    private ArrayList<Wave> waves = new ArrayList<>();
    private boolean ended;
    private String result;

    public Game(GameLauncher gameLauncher) {
        this.gameLauncher = gameLauncher;
        this.waves.add(new Wave(4, 0, 7));
    }

    public GameLauncher getGameLauncher() {
        return gameLauncher;
    }

    public Group getTargets() {
        return targets;
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public Wave getCurrentWave() {
        return this.waves.get(waves.size() - 1);
    }

    public void addWave(int numberOfTanks, int numberOfShootingTanks, int numberOfTrucks) {
        this.waves.add(new Wave(numberOfTanks, numberOfShootingTanks, numberOfTrucks));
    }

    public boolean hasEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getTotalAccuracy() {
        double accuracy = 0;
        for (Wave wave: waves){
            if (Double.isNaN(wave.getAccuracy())) continue;
            accuracy += wave.getAccuracy();
        }
        return (accuracy / waves.size());
    }

    public double getKills() {
        double kills = 0;
        for (Wave wave: waves){
            kills += wave.getKills();
        }
        return kills;
    }
    public void renewTargets(){
        targets = new Group();
    }
}

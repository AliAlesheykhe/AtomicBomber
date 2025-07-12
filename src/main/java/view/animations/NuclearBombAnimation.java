package view.animations;

import model.Game;
import model.NuclearBomb;
import view.GameLauncher;

public class NuclearBombAnimation extends ObjectAnimation{
    private final NuclearBomb nuclearBomb;
    private final GameLauncher gameLauncher;
    private final Game game;

    public NuclearBombAnimation(NuclearBomb nuclearBomb, GameLauncher gameLauncher, Game game){
        super(nuclearBomb);
        this.nuclearBomb = nuclearBomb;
        this.gameLauncher = gameLauncher;
        this.game = game;
        hSpeed = gameLauncher.getPlane().getObjectAnimation().hSpeed;
        vSpeed = gameLauncher.getPlane().getObjectAnimation().vSpeed;
    }
    @Override
    protected void interpolate(double v) {
        double x = nuclearBomb.getX() + hSpeed;
        double y = nuclearBomb.getY() + vSpeed;
        vSpeed += 0.02;
        if (y >= gameLauncher.getHEIGHT() - 250){
            this.stop();
            nuclearBomb.explode();
        }
        nuclearBomb.setY(y);
        nuclearBomb.setX(x);
        setOnFinished(actionEvent ->{
            gameLauncher.getAnimations().remove(this);
        });
    }
}

package view.animations;

import model.Game;
import model.NuclearPrize;
import model.Plane;
import view.GameLauncher;

public class NuclearPrizeAnimation extends ObjectAnimation{
    private final NuclearPrize nuclearPrize;
    private final GameLauncher gameLauncher;
    public NuclearPrizeAnimation(NuclearPrize nuclearPrize, GameLauncher gameLauncher) {
        super(nuclearPrize);
        this.nuclearPrize = nuclearPrize;
        this.gameLauncher = gameLauncher;
        hSpeed = 0;
        vSpeed = -0.5;
    }

    @Override
    protected void interpolate(double v) {
        double y = nuclearPrize.getY() + vSpeed;
        if (y <= -50){
            this.stop();
            gameLauncher.getPane().getChildren().remove(nuclearPrize);
        }
        else if (nuclearPrize.getBoundsInParent().intersects(gameLauncher.getPlane().getBoundsInParent())){
            this.stop();
            gameLauncher.getPlane().addNuclearWeapon(1);
            gameLauncher.getPane().getChildren().remove(nuclearPrize);
            gameLauncher.updateInfoScreen();
        }
        nuclearPrize.setY(y);
    }
}

package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import view.GameLauncher;
import view.animations.AirExplosionAnimation;
import view.animations.BombAnimation;
import view.animations.NuclearBombAnimation;

import java.util.Objects;

public class Plane extends GameObject{
    private final Game game;
    private boolean movingRight = true;
    private final ImagePattern rightMovingPlane = new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource
            ("/images/normal/game objects/planeRight.png")).toExternalForm()));
    private final ImagePattern leftMovingPlane = new ImagePattern(new Image(Objects.requireNonNull(Plane.class.getResource
            ("/images/normal/game objects/planeLeft.png")).toExternalForm()));
    private int nuclearWeapons = 0;
    public Plane(Game game){
        super(0, game.getGameLauncher().getHEIGHT() / 2, 30, 30);
        this.game = game;
        setFill(rightMovingPlane);
        this.WIDTH = 30;
        this.HEIGHT = 30;
        this.explosionAnimation = new AirExplosionAnimation(this,
                game.getGameLauncher().getPane(), game.getTargets(), game);
    }

    public void moveRight(){
        movingRight = true;
        setFill(rightMovingPlane);
        this.objectAnimation.sethSpeed(2);
    }
    public void moveLeft(){
        movingRight = false;
        setFill(leftMovingPlane);
        this.objectAnimation.sethSpeed(-2);
    }
    public void moveUp(){
        if (this.objectAnimation.getvSpeed() > -2)
            this.objectAnimation.setvSpeed(this.objectAnimation.getvSpeed() - 2);
    }
    public void moveDown(){
        if (this.objectAnimation.getvSpeed() < 2)
            this.objectAnimation.setvSpeed(this.objectAnimation.getvSpeed() + 2);
    }
    public void shoot(Pane pane, Game game, GameLauncher gameLauncher){
        Bomb bomb = new Bomb(this, gameLauncher);
        int planeIndex = pane.getChildren().indexOf(this);
        pane.getChildren().add(planeIndex, bomb);
        BombAnimation bombAnimation = new BombAnimation(game, this, bomb, pane, gameLauncher);
        bomb.setBombAnimation(bombAnimation);
        gameLauncher.getAnimations().add(bombAnimation);
        bombAnimation.play();
        game.getCurrentWave().addShoots();
        User.getLoggedInUser().addShoot();
    }
    public void dropNuclearBomb(Game game, GameLauncher gameLauncher) {
        if (gameLauncher.getPlane().getNuclearWeapons() > 0){
            NuclearBomb nuclearBomb = new NuclearBomb(this.getX(), this.getY(), 50, 50, game);
            int planeIndex = gameLauncher.getPane().getChildren().indexOf(this);
            gameLauncher.getPane().getChildren().add(planeIndex, nuclearBomb);
            NuclearBombAnimation nuclearBombAnimation = new NuclearBombAnimation(nuclearBomb, gameLauncher, game);
            nuclearBomb.setObjectAnimation(nuclearBombAnimation);
            gameLauncher.getAnimations().add(nuclearBombAnimation);
            nuclearBombAnimation.play();
            game.getCurrentWave().addShoots();
            User.getLoggedInUser().addShoot();
            gameLauncher.updateInfoScreen();
            gameLauncher.getPlane().addNuclearWeapon(-1);
        }
    }
    public void addNuclearWeapon(int add){
        this.nuclearWeapons += add;
    }
    public int getNuclearWeapons(){
        return this.nuclearWeapons;
    }

    public void setNuclearWeapons(int nuclearBombs) {
        this.nuclearWeapons = nuclearBombs;
    }

    public void setCorrectFill() {
        if(movingRight)
            this.setFill(rightMovingPlane);
        else this.setFill(leftMovingPlane);
    }
}

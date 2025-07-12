package view;

import controller.ApplicationController;
import controller.GameController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.animations.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class GameLauncher extends Application {
    private final double WIDTH = 1000;
    private final double HEIGHT = 700;
    private Label currentWaveNum = new Label();
    private Label migWarning = new Label();
    private Label killLabel = new Label();
    private Label remainingNuclearBombs = new Label();
    private Label accuracyLabelWave1 = new Label();
    private Label accuracyLabelWave2 = new Label();
    private MediaPlayer mediaPlayer;
    private ArrayList<Transition> animations = new ArrayList<>();
    private Timeline createTanks;
    private Timeline createShootingTanks;
    private ArrayList<Timeline> timelines = new ArrayList<>();
    Timeline createTrucks;
    private String trackName;
    private Scene scene;
    private Pane pane;
    private Plane plane;
    private Wave currentWave;
    private Timeline makeRockets;
    private Timeline createMigs;
    private final Game game;

    public GameLauncher(){
        game = new Game(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationController.gameLauncher = this;
        if (pane == null || currentWave != game.getCurrentWave())
            continueToNextWave(primaryStage);
        else resumeCurrentWave();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        plane.requestFocus();
    }
    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public Pane getPane() {
        return pane;
    }

    public Game getGame() {
        return game;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public ArrayList<Transition> getAnimations() {
        return this.animations;
    }
    public Plane getPlane() {
        return this.plane;
    }
    public void setPane(Pane pane) {
        this.pane = pane;
    }
    public ArrayList<Timeline> getTimeLines() {
        return timelines;
    }
    private void setSize(Stage primaryStage) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT );
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }
    private void resumeCurrentWave() {
        plane.setCorrectFill();
        plane.setExploded(false);
        pane.getChildren().add(plane);
        for (Timeline timeline: timelines)
            timeline.play();
        for (Transition animation: animations){
            if (animation instanceof ObjectAnimation){
                ObjectAnimation objectAnimation = (ObjectAnimation) animation;
                GameObject gameObject = objectAnimation.getGameObject();
                if (gameObject.hasExploded() && !(gameObject instanceof Plane))
                    continue;
                objectAnimation.play();
            }
        }
    }

    private void continueToNextWave(Stage primaryStage) {
        deleteAnimations();
        if (currentWave != game.getCurrentWave() && currentWave != null)
            resetPreviousPane();
        currentWave = game.getCurrentWave();
        pane = new Pane();
        pane.setBackground(new Background(createBackgroundImage()));
        setSize(primaryStage);
        createPlane();
        createInfoScreen();
        pane.getChildren().add(game.getTargets());
        if (!User.getLoggedInUser().getGameSettings().isMuted())
            playTrack();
        game.getTargets().getChildren().add(new Building(this));
        createTrees();
        makeTimelines();
        for (Timeline timeline: timelines){
            timeline.play();
        }
        scene = new Scene(pane);
    }

    private void createInfoScreen() {
        VBox info = new VBox();
        currentWaveNum.setText("current wave: " + game.getWaves().size());
        killLabel.setText("total kills: " + game.getKills());
        accuracyLabelWave1.setText("wave1 accuracy: ");
        if (game.getWaves().size() > 1)
            accuracyLabelWave1.setText("wave1 accuracy : " + game.getWaves().get(0).getAccuracy());
        accuracyLabelWave2.setText("wave2 accuracy: ");
        if (game.getWaves().size() > 2)
            accuracyLabelWave2.setText("wave2 accuracy : " + game.getWaves().get(1).getAccuracy());
        remainingNuclearBombs.setText("nuclear bombs remaining: " + plane.getNuclearWeapons());

        info.getChildren().add(currentWaveNum);
        info.getChildren().add(killLabel);
        info.getChildren().add(accuracyLabelWave1);
        info.getChildren().add(accuracyLabelWave2);
        info.getChildren().add(remainingNuclearBombs);
        info.getChildren().add(migWarning);

        pane.getChildren().add(info);
    }

    public void updateInfoScreen(){
        killLabel.setText("total kills: " + game.getKills());
        remainingNuclearBombs.setText("nuclear bombs remaining: " + plane.getNuclearWeapons());
    }

    private void makeTimelines() {
        makeRocketTimelines();
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(5),
                actionEvent -> createTruck()));
        createTrucks.setCycleCount(currentWave.getNumberOfTrucks());
        timelines.add(createTrucks);
        createTrucks.setOnFinished(event -> timelines.remove(createTrucks));

        createTanks = new Timeline(new KeyFrame(Duration.seconds(5),
                actionEvent -> createTank()));
        createTanks.setCycleCount(currentWave.getNumberofTanks());
        timelines.add(createTanks);
        createTanks.setOnFinished(event -> timelines.remove(createTanks));

        if (game.getWaves().size() > 1){
            createShootingTanks = new Timeline(new KeyFrame(Duration.seconds(7),
                    actionEvent -> createShootingTank(false)));
            createShootingTanks.setCycleCount(currentWave.getNumberOfShootingTanks());
            timelines.add(createShootingTanks);
            createShootingTanks.setOnFinished(event -> timelines.remove(createShootingTanks));
        }
        if (game.getWaves().size() == 3){
            double difficulty = User.getLoggedInUser().getGameSettings().getDifficulty();
            double interval = (5 - (difficulty)) / 4 * 60;
            createMigs = new Timeline(new KeyFrame(Duration.seconds(interval - 5),
                    actionEvent -> warn()),
                    new KeyFrame(Duration.seconds(interval),
                    actionEvent -> createMig()));
            createMigs.setCycleCount(Animation.INDEFINITE);
            timelines.add(createMigs);
            createMigs.setOnFinished(event -> timelines.remove(createMigs));
        }
    }

    private void warn() {
        migWarning.setText("Mig coming!!");
        migWarning.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 30px;");
    }

    private void createMig() {
        Mig mig = new Mig(this);
        pane.getChildren().add(mig);
        animations.add(mig.getObjectAnimation());
        mig.getObjectAnimation().play();
        migWarning.setText("");
    }

    private void makeRocketTimelines() {
        for (int i = 0; i < game.getWaves().size(); i++){
            makeRockets = new Timeline(new KeyFrame(Duration.seconds(8), e -> createRocket()));
            makeRockets.setCycleCount(Timeline.INDEFINITE);
            timelines.add(makeRockets);
            makeRockets.play();
        }
    }
    private void createRocket() {
        Rocket rocket = new Rocket(this);
        RocketAnimation rocketAnimation = new RocketAnimation(rocket, plane, this);
        rocket.setObjectAnimation(rocketAnimation);
        animations.add(rocketAnimation);
        pane.getChildren().add(rocket);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> rocketAnimation.play()));
        timelines.add(timeline);
        timeline.play();
    }
    private void resetPreviousPane() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
        for (Timeline timeline: timelines){
            timeline.stop();
        }
        pane.getChildren().remove(plane);
        pane.getChildren().remove(game.getTargets());
        game.renewTargets();
        timelines = new ArrayList<>();
        deleteAnimations();
    }
    public void playTrack() {
        int trackNum = ThreadLocalRandom.current().nextInt(1, 4);
        if (trackName == null)
            trackName = "track" + trackNum;
        URL mediaUrl = GameLauncher.class.getResource("/tracks/" + trackName + ".mp3");
        Media sound = new Media(mediaUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }
    private void createTank() {
        double hSpeed = User.getLoggedInUser().getGameSettings().getDifficulty() * -0.5;
        Tank tank = new Tank(this.pane, this);
        EnemyAnimation tankAnimation = new EnemyAnimation(game, tank, hSpeed, 0);
        tank.setObjectAnimation(tankAnimation);
        animations.add(tankAnimation);
        tankAnimation.play();
        game.getTargets().getChildren().add(tank);
    }
    private void createShootingTank(boolean randomLocation) {
        double hSpeed = User.getLoggedInUser().getGameSettings().getDifficulty() * 0.7;
        ShootingTank tank = new ShootingTank(pane, this, randomLocation);
        EnemyAnimation tankAnimation = new EnemyAnimation(game, tank, hSpeed, 0);
        tank.setObjectAnimation(tankAnimation);
        animations.add(tankAnimation);
        tankAnimation.play();
        game.getTargets().getChildren().add(tank);
        game.getCurrentWave().addPotentialKills(7);
    }

    private void createTruck(){
        Truck truck = new Truck(pane, this);
        EnemyAnimation truckAnimation = new EnemyAnimation(game, truck, 0.7, 0);
        truck.setObjectAnimation(truckAnimation);
        animations.add(truckAnimation);
        truckAnimation.play();
        game.getTargets().getChildren().add(truck);
    }

    private void createPlane() {
        HashMap <String, KeyCode> keys = User.getLoggedInUser().getGameSettings().getKeys();
        int nuclearBombs = 0;
        if (this.plane != null){
            animations.add(plane.getObjectAnimation());
            plane.getObjectAnimation().play();
            pane.getChildren().add(plane);
            return;
        }
        this.plane = new Plane(game);
        this.plane.setNuclearWeapons(nuclearBombs);
        this.plane.setObjectAnimation(new PlaneAnimation(this.plane, this.game));
        animations.add(plane.getObjectAnimation());
        this.plane.setOnKeyPressed(keyEvent ->{
            if (keyEvent.getCode() == keys.get("move right"))
                plane.moveRight();
            else if (keyEvent.getCode() == keys.get("move left"))
                plane.moveLeft();
            else if (keyEvent.getCode() == keys.get("move down"))
                plane.moveDown();
            else if (keyEvent.getCode() == keys.get("move up"))
                plane.moveUp();
            else if (keyEvent.getCode() == keys.get("drop bomb"))
                plane.shoot(this.pane, this.game, this);
            else if (keyEvent.getCode() == keys.get("drop R bomb"))
                plane.dropNuclearBomb(game, this);
            else if (keyEvent.getCode() == keys.get("pause")){
                PauseMenuController.setGameLauncher(this);
                goToPauseMenu();
            }
            else if (keyEvent.getCode() == KeyCode.P){
                GameController.goToNextWave(game);
                updateInfoScreen();
            }
            else if (keyEvent.getCode() == KeyCode.G){
                plane.addNuclearWeapon(1);
                updateInfoScreen();
            }
            else if (keyEvent.getCode() == KeyCode.T){
                createShootingTank(true);
            }

        });
        this.plane.getObjectAnimation().play();
        pane.getChildren().add(plane);
    }
    private void goToPauseMenu() {
        pane.getChildren().remove(plane);
        for (Timeline timeline: timelines)
            timeline.pause();
        for (Transition animation: animations){
            animation.pause();
        }
        try{
            if (ApplicationController.pauseMenu == null)
                ApplicationController.pauseMenu = new PauseMenu();
            ApplicationController.pauseMenu.start(ApplicationController.primaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private BackgroundImage createBackgroundImage(){
        Image image = new Image(Objects.requireNonNull(GameLauncher.class.
                        getResource("/images/normal/backgrounds/blue-sky-clouds.jpg"))
                        .toExternalForm(),
                        WIDTH, HEIGHT, false, false);
        return new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }
    private void createTrees(){
        int numberOfTrees = ThreadLocalRandom.current().nextInt(3, 10);
        for (int i = 0; i < numberOfTrees; i++){
            Tree tree = new Tree(this);
            game.getTargets().getChildren().add(tree);
        }
    }
    public void deleteAnimations() {
        for (Transition animation: animations)
            animation.stop();
        animations = new ArrayList<>();
    }
    public void deleteTimelines() {
        for (Timeline timeline: timelines)
            timeline.stop();
        timelines = new ArrayList<>();
    }

    public void stopAnimations() {
        for (Transition animation: animations)
            animation.stop();
    }

    public void stopTimelines() {
        for (Timeline timeline: timelines)
            timeline.stop();
    }
}

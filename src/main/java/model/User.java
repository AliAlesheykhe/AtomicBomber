package model;

import controller.GameSettings;
import javafx.scene.image.Image;
import view.GameLauncher;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class User implements Serializable{
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static User loggedInUser;
    private String username;
    private String password;
    private double shoots = 0;
    private double successfulShoots = 0;
    private double kills = 0;
    private int score = 0;
    private int totalDifficulty = 0;
    private GameSettings gameSettings;
    private GameLauncher savedGame;

    private int difficulty = 0;

    transient Image avatar;

    public User(String username, String password, GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.username = username;
        this.gameSettings = new GameSettings();
        setPassword(password);
        allUsers.add(this);
    }

    public static User getUserByUsername(String username){
        for (User user: allUsers){
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getSortedByScore() {
        allUsers.sort(Comparator.comparingInt(User::getScore).thenComparing(User::getUsername));
        return allUsers;
    }

    public static ArrayList<User> getSortedByKills() {
        allUsers.sort(Comparator.comparingDouble(User::getNumberOfKills).reversed());
        return allUsers;
    }

    public static ArrayList<User> getSortedByDifficulty() {
        allUsers.sort(Comparator.comparingInt(User::getTotalDifficulty));
        return allUsers;
    }

    public static ArrayList<User> getSortedByAccuracy() {
        allUsers.sort(Comparator.comparingDouble(User::getAccuracy).thenComparing(User::getUsername));
        return allUsers;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void remove() {
        allUsers.remove(this);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getAccuracy(){
        return successfulShoots /shoots;
    }

    public double getNumberOfKills(){
        return kills;
    }

    public int getTotalDifficulty(){
        return totalDifficulty;
    }

    public String getRelevantMetric(String metric) {
        switch (metric){
            case "score":
                return Double.toString(this.score);
            case "kills":
                return Double.toString(this.kills);
            case "difficulty":
                return Double.toString(this.getTotalDifficulty());
            case "accuracy":
                return Double.toString(this.getAccuracy());
        }
        return null;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void addDifficulty(int add){
        totalDifficulty += add;
    }

    public GameLauncher getSavedGame() {
        return savedGame;
    }
    public void setSavedGame(GameLauncher savedGame) {
        this.savedGame = savedGame;
    }
    public void addShoot(){
        shoots++;
    }
    public void addSuccessfulShoot(){
        successfulShoots++;
    }
    public void addKills(int kills) {
        this.kills += kills;
    }
}



package model;

public class Wave {
    private final int numberOfTanks;
    private final int numberOfShootingTanks;
    private final int NumberOfTrucks;
    private double kills = 0;
    private double accuracy;
    private int totalPossibleKills;
    private double shoots = 0;
    private double successfulShoots = 0;

    public Wave(int numberOfTanks, int numberOfShootingTanks, int numberOfTrucks) {
        this.numberOfTanks = numberOfTanks;
        this.numberOfShootingTanks = numberOfShootingTanks;
        NumberOfTrucks = numberOfTrucks;
        totalPossibleKills = numberOfShootingTanks * 7 + numberOfTanks * 5 + numberOfTrucks * 3 + 10;
    }

    public double getKills() {
        return kills;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public double getAccuracy() {
        return successfulShoots / shoots;
    }

    public void addShoots(){
        shoots++;
    }
    public void addSuccessfulShoots(){
        successfulShoots++;
    }

    public int getTotalPossibleKills() {
        return totalPossibleKills;
    }

    public int getNumberofTanks() {
        return numberOfTanks;
    }

    public int getNumberOfShootingTanks() {
        return numberOfShootingTanks;
    }

    public int getNumberOfTrucks() {
        return NumberOfTrucks;
    }

    public void addPotentialKills(int kills) {
        totalPossibleKills += kills;
    }
}

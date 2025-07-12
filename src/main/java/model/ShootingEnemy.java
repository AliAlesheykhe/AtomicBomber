package model;

public abstract class ShootingEnemy extends GameObject{
    protected boolean shot = false;
    public ShootingEnemy(double x, double y, int width, int height) {
        super(x, y, width, height);
    }
    public boolean hasShot(){
        return shot;
    }
    public void setShot(boolean shot){
        this.shot = shot;
    }
    public abstract void shoot(double planeX, double planeY);
}

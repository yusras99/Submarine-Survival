import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private double x, y;
    private double velY = 0;

    private BufferedImage player;

    public static int HEIGHT = 125;

    /**
     * Player method will define the x, y, and player variables
     *
     * @param x      double--left, right location
     * @param y      double--up, down location
     * @param player BufferedImage--submarine sprite
     */
    public Player(double x, double y, Game game, BufferedImage player) {
        this.x = x;
        this.y = y;
        this.player = player;

    }

    public void tick() {

        // how fast the submarine moves up and down
        y += velY;

        // setting bounds for player - have sub remain on screen
        if(y <= -20)
            y = -20;
        if(y >= Game.height*Game.scale - HEIGHT)
            y = Game.height*Game.scale - HEIGHT;

    }

    public void render(Graphics g) {
        g.drawImage(player, (int)x, (int)y, 125, 125, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // for smoother movement
    public void setVelY(double velY) {
        this.velY = velY;
    }

    // private Lives lives; to call the lives class?
    // public void lifeTracker (){
    //lives = new Lives();
    //if hit by enemy{ }
    //call the life class with lostLife method, lives(lostLife);
    //elif hit a bonus object
    //call life class with addLife method
    //elif currentLife == 0;
    //game over screen
    //return to main menu
    //}
}

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Torpedo will be drawing the image of the player's torpedo and implementing
 * movement so the player can hit the enemy
 */
public class Torpedo {

    private double x, y;
    BufferedImage missile;
    public static int level = 1;
    
    //Height and width of the torpedo image
    public static int HEIGHT = 10;
    public static int WIDTH = 35;

    /**
     * Torpedo method will contain the submarines torpedo's that will be used to
     * attack the enemies - torpedo will move from left to right
     * 
     * @param x    // x-axis
     * @param y    // y-axis
     * @param game // initialize game class
     */
    public Torpedo(double x, double y, Game game, BufferedImage missile) {
        this.x = x;
        this.y = y;
        this.missile = missile;

    }
    
    /**
     * tick will have the submarine's torpedo moving along the x-axis with a speed
     * of 7 from left to right
     */
    public void tick() {
        // torpedo shooting from L to R at 7 speed
        x += 7;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, missile.getWidth(), missile.getHeight());
    }

    /**
     * render will draw the image of the torpedo that the player's submmarine will
     * use
     * 
     * @param g // implementing image
     */
    public void render(Graphics g) {
        g.drawImage(missile, ((int) x + 125), ((int) y + 75), WIDTH, HEIGHT, null);

    }

    /**
     * Returning the x position of the torpedo
     */
    public double getX() {
        return x;
    }

    /**
     * Returning the y position of the torpedo
     * Used in a loop
     */
    public double getY() {
        return y;
    }

    /**
     * Returning the width of the torpedo image
     */
    public double getWidth() {
        return WIDTH;
    }

    /**
     * Returning the height of the torpedo image
     */
    public double getHeight() {
        return HEIGHT;
    }
}

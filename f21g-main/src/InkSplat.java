import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Ink Splat will be drawing the image of the kraken's missile and implementing
 * movement so the kraken can hit the player
 */
public class InkSplat {

    private double x, y;
    BufferedImage ink;

    //Height and width of the ink image
    public static int HEIGHT = 30;
    public static int WIDTH = 30;

    /**
     * InkSplat method will contain the kraken's ink splats that will be used to
     * attack the player - ink splats will move from right to left
     * 
     * @param x    // x-axis
     * @param y    // y-axis
     * @param game // initialize game class
     */
    public InkSplat(double x, double y, Game game, BufferedImage ink) {
        this.x = x;
        this.y = y;
        this.ink = ink;

    }
    
    /**
     * tick will have the kraken's ink splats moving along the x-axis with a speed
     * of 5 from right to left
     */
    public void tick() {
        // ink splat shooting from R to L at 5 speed
        x -= 5;
    }

    /**
     * render will draw the image of the ink splat that the kraken will
     * shoot
     * 
     * @param g // implmenting image
     */
    public void render(Graphics g) {
        g.drawImage(ink, ((int) x + 40), ((int) y + 60), WIDTH, HEIGHT, null);

    }

    /**
     * Returning the x position of the ink splat
     */
    public double getX() {
        return x;
    }

    /**
     * Returning the y position of the ink splat
     */
    public double getY() {
        return y;
    }

    /**
     * Returning the width of the ink splat image
     */
    public double getWidth() {
        return WIDTH;
    }

    /**
     * Returning the height of the ink splat image
     */
    public double getHeight() {
        return HEIGHT;
    }
}
//for level 3

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SharkEnemy {
    private double x, y;
    Random r = new Random();
    private int sharkSpeed = r.nextInt(3) + 2;
    private BufferedImage sharkEnemy;

    // public int sharkLeft = Controller.getSharkCount();
    public static int HEIGHT = 50;
    public static int WIDTH = 90;

    public SharkEnemy(double x, double y, BufferedImage sharkEnemy) {
        this.x = x;
        this.y = y;
        this.sharkEnemy = sharkEnemy;
    }

    public void tick() {
        // enemy from R to L at 3 speed
        x -= sharkSpeed;

        // if the squid enemy sprite moves beyond the submarine,
        // it will go back to the right side of the screen.
        if (x < -70) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale);
        }

        if (y < -70) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale) + 100;
        }

        if (y > 600) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale) - 40;
        }
    }

    public void render(Graphics g) {
        // class, x location, y location, image width, image height
        g.drawImage(sharkEnemy, (int) x, (int) y, WIDTH, HEIGHT, null);
        // Font gameFont= new Font("Kristen ITC", Font.BOLD,20);
        // g.setFont(gameFont);
        // g.drawString("Total Sharks left = "+sharkLeft, 540,40);
        // g.drawImage(sharkEnemy, (int) 980, (int) 30, WIDTH, HEIGHT, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return x;
    }

    public double getWidth() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
}

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;

public class Enemy {

    private double x, y;
    Random r = new Random();
    private int speed = r.nextInt(3) + 2;

    private final BufferedImage squidEnemy;
    public static int HEIGHT = 50;
    public static int WIDTH = 70;

    public Enemy(double x, double y, BufferedImage squidEnemy) {
        this.x = x;
        this.y = y;
        this.squidEnemy = squidEnemy;
    }

    public void tick() {
        // enemy from R to L at 3 speed
        x -= speed;
        // if the squid enemy sprite moves beyond the submarine,
        // it will go back to the right side of the screen.
        if (x < -70) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale);
        }

        if (y < -70) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale) + 60;
        }

        if (y > 600) {
            x = (Game.width * Game.scale);
            y = r.nextInt(Game.height * Game.scale) - 40;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, squidEnemy.getWidth(null), squidEnemy.getHeight(null));
    }

    public void render(Graphics g) {
        // class, x location, y location, image width, image height
        g.drawImage(squidEnemy, (int) x, (int) y, WIDTH, HEIGHT, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return HEIGHT;
    }

    public double getWidth() {
        return WIDTH;
    }

    public void setX(double x) {
        this.x = x;
    }

}
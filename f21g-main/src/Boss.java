import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boss {

    private double x, y;
    //private double velY = 0;

    private BufferedImage kraken;

    public static int HEIGHT = 175;
    public int speed = 3;

    /**
     * Kraken method will define the x, y, and kraken variables
     *
     * @param x  double--left, right location
     * @param y  double--up, down location
     * @param kraken BufferedImage--kraken sprite
     */
    public Boss(double x, double y, Game game, BufferedImage kraken) {
        this.x = x;
        this.y = y;
        this.kraken = kraken;
    }

    public void tick() {
        //how fast the kraken moves up and down
        y += speed;

        //setting bounds for kraken and changing the speed diection
        if(y > Game.height*Game.scale - HEIGHT){
            speed *= -1;
        }
        if(y <= -8){
            speed *= -1;
        }
    }

    public void render(Graphics g) {
        g.drawImage(kraken, (int)x, (int)y, 175, 175, null);
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
}

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;

public class Controller {

    public static LinkedList<Torpedo> torp = new LinkedList<Torpedo>();
    private LinkedList<InkSplat> splat = new LinkedList<InkSplat>();
    public static LinkedList<Enemy> enemy = new LinkedList<Enemy>();
    public static LinkedList<SharkEnemy> sharky = new LinkedList<SharkEnemy>();

    public int counter = 0;

    // random enemy location set up
    Random r = new Random();

    // bring in instances of the other classes
    Torpedo TempTorpedo;
    InkSplat TempSplat;
    Enemy TempEnemy;
    SharkEnemy TempSharky;
    Game game;
    Graphics g;
    BufferedImage squidEnemy;
    BufferedImage sharkEnemy;
    Boss kraken;

    public int enemyCount = r.nextInt(5);
    public int level = 1;

    private int sharkCount, speed, sharkSpeed;
    // private int sharkKilled;

    /**
     * Controller method will add new enemies in the game.
     * 
     * @param game
     */
    public Controller(Game game, BufferedImage squidEnemy, BufferedImage sharkEnemy, Boss kraken) {
        this.game = game;
        this.squidEnemy = squidEnemy;
        this.sharkEnemy = sharkEnemy;
        this.kraken = kraken;

        // defines a new enemy from the enemy class to the game at a random "y" location
        Enemy Enemy = new Enemy(Game.width, Game.height * Game.scale, squidEnemy);
        SharkEnemy Shark = new SharkEnemy(Game.width, Game.height * Game.scale, sharkEnemy);

        // adds the new enemy to the game and to the linked list
        for (int i = 0; i < enemy.size(); i++) {
            speed = r.nextInt(speed);
        }
        addEnemy(Enemy);
        if (level == 2 || level == 3) {
            for (int i = 0; i < sharky.size(); i++) {
                sharkSpeed = r.nextInt(sharkSpeed);
            }
            addSharkEnemy(Shark);
        }
        startGame();
    }

    public int getSharkCount() {
        return sharkCount;
    }

    public void setSharkCount(int sharkCount) {
        this.sharkCount = sharkCount;
    }

    public void tick() {

        // every time a torpedo is created through pushing the space bar, we will check
        // for collision
        for (int i = 0; i < torp.size(); i++) {

            // seeing which torpedo from the linked list we are looking at
            TempTorpedo = torp.get(i);

            // // If the torpedo goes past the screen, we remove it
            if (TempTorpedo.getX() > 1150) {
                removeTorpedo(TempTorpedo);
            }

            // for every squid enemy in the enemy linked list
            for (int j = 0; j < enemy.size(); j++) {

                // seeing which squid enemy from the linked list we are looking at
                TempEnemy = enemy.get(j);

                // torpedo squid collision
                if (TempTorpedo.getY() >= (TempEnemy.getY() - 80) && (TempTorpedo.getY() <= (TempEnemy.getY() - 30))
                        && (TempTorpedo.getX() >= (TempEnemy.getX() - 155))) {
                    removeTorpedo(TempTorpedo);
                    removeEnemy(TempEnemy);
                }
            }

            // for every shark in the linked list...
            for (int s = 0; s < sharky.size(); s++) {

                // seeing which shark enemy from the linked list we are looking at
                TempSharky = sharky.get(s);

                // torpedo shark collision
                if (TempTorpedo.getY() >= (TempSharky.getY() - 80) && (TempTorpedo.getY() <= (TempSharky.getY() - 27))
                        && (TempTorpedo.getX() >= (TempSharky.getX() - 155))) {
                    removeTorpedo(TempTorpedo);
                    removeSharkEnemy(TempSharky);
                    // sharkKilled++;
                    // System.out.println(sharkKilled);
                }
            }

            // torpedo kraken collision
            if (TempTorpedo.getY() >= (kraken.getY() - 80) && (TempTorpedo.getY() <= (kraken.getY() + 90))
                    && (TempTorpedo.getX() >= (kraken.getX() - 155))) {
                removeTorpedo(TempTorpedo);
                counter += 1;
                if (counter == 10 && enemy.size() == 0 && sharky.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Game over! You win!");
                    Game.State = Game.State.MENU;
                    Game.resetGame();
                    // game ends after enemies gone but not necessarily the boss, can have more
                    // counters since
                    // enemies would be on game screen still
                }
            }
            TempTorpedo.tick();
        }

        for (int i = 0; i < splat.size(); i++) {
            // seeing which ink splat from the linked list we are looking at
            TempSplat = splat.get(i);

            // If the ink splat goes past the screen, we remove it
            if (TempSplat.getX() < -45) {
                removeInk(TempSplat);
            }
            // inksplat player collision
            if (TempSplat.getY() >= (Game.p.getY() - 80) && (TempSplat.getY() <= (Game.p.getY() + 40))
                    && (TempSplat.getX() <= (Game.p.getX() + 95))) {
                removeInk(TempSplat);
                // System.out.println("it hit");
                // counter += 1;
                // if (counter == 1) {
                // // lives - 1;
                // }
            }
            TempSplat.tick();
        }

        // makes squid move
        for (int i = 0; i < enemy.size(); i++) {
            TempEnemy = enemy.get(i);
            // enemy player collision
            if (TempEnemy.getY() >= (Game.p.getY() - 100) && (TempEnemy.getY() <= (Game.p.getY() + 50))
                    && (TempEnemy.getX() <= (Game.p.getX() + 125))) {
               
                removeEnemy(TempEnemy);
                // System.out.println("it hit");
                // counter += 1;
                // if (counter == 1) {
                // // lives - 1;
                // }
            }
            TempEnemy.tick();
        }

        // makes shark move
        for (int i = 0; i < sharky.size(); i++) {
            TempSharky = sharky.get(i);
            // sharkEnemy player collision
            if (TempSharky.getY() >= (Game.p.getY() - 100) && (TempSharky.getY() <= (Game.p.getY() + 50))
                    && (TempSharky.getX() <= (Game.p.getX() + 125))) {

                removeSharkEnemy(TempSharky);
                // System.out.println("it hit");
                // counter += 1;
                // if (counter == 1) {
                // // lives - 1;
                // }
            }
            TempSharky.tick();
        }
        checkEnd();
    }

    public void render(Graphics g) {
        // makes torpedo appear
        for (int i = 0; i < torp.size(); i++) {
            TempTorpedo = torp.get(i);
            TempTorpedo.render(g);
        }

        // makes splat appear
        for (int i = 0; i < splat.size(); i++) {
            TempSplat = splat.get(i);
            if (level == 3) {
                TempSplat.render(g);
            }
        }

        // makes squid enemy appear
        for (int i = 0; i < enemy.size(); i++) {
            TempEnemy = enemy.get(i);
            TempEnemy.render(g);
        }

        // makes shark enemy appear
        for (int i = 0; i < sharky.size(); i++) {
            TempSharky = sharky.get(i);
            TempSharky.render(g);
        }
        if (level == 3) {
            kraken.render(g);
        }
    }

    public void addTorpedo(Torpedo block) {
        torp.add(block);
    }

    public static void removeTorpedo(Torpedo block) {
        torp.remove(block);
    }

    public void addInk(InkSplat block) {
        splat.add(block);
    }

    public void removeInk(InkSplat block) {
        splat.remove(block);
    }

    public void addEnemy(Enemy block) {
        enemy.add(block);
    }

    public static void removeEnemy(Enemy block) {
        enemy.remove(block);
    }

    public void addSharkEnemy(SharkEnemy block) {
        sharky.add(block);
    }

    public static void removeSharkEnemy(SharkEnemy block) {
        sharky.remove(block);
    }

    public void startGame() {
        enemyCount = level * 3;

        for (int i = 0; i < enemyCount; i++) {
            addEnemy(new Enemy(Game.width, Game.height * Game.scale, squidEnemy));
            if (level == 2 || level == 3) {
                sharkCount = level * 5;
                addSharkEnemy(new SharkEnemy(Game.width, Game.height * Game.scale, sharkEnemy));
            }
        }
    }

    // check if there are no enemies left
    public void checkEnd() {
        if (enemy.size() == 0 && sharky.size() == 0) {
            level++;
            enemy.clear();
            sharky.clear();
            torp.clear();
            JOptionPane.showMessageDialog(null, "Level " + (level - 1) + " Complete");
            if (level > 3) {
                JOptionPane.showMessageDialog(null, "Game Complete!");
                Game.State = Game.State.MENU;
                Game.resetGame();
            }
            startGame();
        }
    }

}

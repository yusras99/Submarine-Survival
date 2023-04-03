import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.KeyEvent;

// canvas just a library brings everything into here without downloading
// Runnable is access by a thread
// threads are used to make applications faster by doing multiple things at the same time
// runnable - when a thread is called or started it will take that thread and call that run method
public class Game extends Canvas implements Runnable {
    // this is for when we launch the game how big it's going to be
    // this is the aspect ratio
    public static int width;
    public static int height;
    public static int scale = 2;
    public final String title = "Submarine Survival";

    private boolean running = false; // initializing running thread as false
    private Thread thread;

    // buffer loads image before its displayed and renders it
    // buffered the whole window meaning loading in the image that's why we need
    // width and height and rgb
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private BufferedImage background = null; // initialize our image as empty
    private BufferedImage levelOne = null; // first level
    public BufferedImage levelTwo = null; // second level
    public BufferedImage levelThree = null; // third level

    private BufferedImage subSprite = null; // submarine sprite
    private BufferedImage missile = null; // torpedo sprite
    private BufferedImage squidEnemy = null; // squid enemy sprite
    private BufferedImage sharkEnemy = null; // shark enemy sprite
    private BufferedImage kraken = null; // kraken enemy sprite
    private BufferedImage ink = null; // ink sprite
    // canvas just a library brings everything into here without downloading

    private boolean is_shooting = false;

    public static Player p;
    public static Controller c;
    private Enemy e;
    private SharkEnemy sharky;
    private Boss b;
    private Menu menu;
    private help helpObj;
    public int sharkCount = 10;
    public static int noGamesPlayed;

    // Enum enables for a variable to be a set of predefined constants
    // we use enums types any time you need to represent a fixed set of constants
    // in order to open a new window, add the enum of it
    public enum STATE {
        MENU, GAME, HELP
    }

    // creating a variable to be able to use STATE
    // check which state we are in for the game
    // we always start state in menu because here is where we will be deciding what
    // to do
    public static STATE State = STATE.MENU;

    public static int MINHEIGHT = 846;
    public static int MINWIDTH = 1150;
    public static int TITLEBAR = 16;

    public static void setScreenDimensions() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        if ((size.height - TITLEBAR) >= MINHEIGHT) {
            height = MINHEIGHT / scale;
        } else {
            height = (size.height - TITLEBAR) / scale;
        }
        if (size.width >= MINWIDTH) {
            width = MINWIDTH / scale;
        } else {
            width = size.width / scale;
        }
    }

    // init() is an initializing method
    public void init() {
        // makes image focus better
        requestFocus();
        // this is calling in the loadImage file to load in an image
        loadImage load = new loadImage();

        // try doing this if it does not work catch the error
        // loading in image
        try {
            // image backgrounds
            background = load.getBufferedImage("/menu_background.jpg");
            levelOne = load.getBufferedImage("/level_1.png");
            levelTwo = load.getBufferedImage("/level_2.png");
            levelThree = load.getBufferedImage("/level_3.png");

            // moveable images
            subSprite = load.getBufferedImage("/submarine.png");
            missile = load.getBufferedImage("/bitBullet.png");
            squidEnemy = load.getBufferedImage("/octo.png");
            sharkEnemy = load.getBufferedImage("/sharkEnemy.png");
            ink = load.getBufferedImage("/splat.png");
            kraken = load.getBufferedImage("/kraken.png");
            // livesSprite = load.getBufferedImage("/life.png");
            // sharkSprite = load.getBufferedImage("/shark.png");
            // bombSprite = load.getBufferedImage("/bomb.png");
            // smallBombSprite = load.getBufferedImage("/small_bomb.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
        // calling in the Menu file
        menu = new Menu();
        // calling in the mouseInput file
        this.addMouseListener(new mouseInput());
        helpObj = new help();

        addKeyListener(new KeyInput(this));

        p = new Player(20, 100, this, subSprite);
        b = new Boss(975, 100, this, kraken);
        c = new Controller(this, squidEnemy, sharkEnemy, b);
        e = new Enemy(1000, (Game.height + 25 * Game.scale), squidEnemy);
        sharky = new SharkEnemy(1000, (Game.height * Game.scale), sharkEnemy);
        // c.sharksToKill(sharkCount);
    }

    // synchronize deals with threads
    // starts thread
    private synchronized void start() {
        // start method
        if (running)
            return;
        // if its already running get out of the method
        // if false we make it true and create a thread
        running = true;
        thread = new Thread(this); // this run method
        thread.start();
    }

    // stops thread
    private synchronized void stop() { // dealing with threads
        if (!running)
            return;
        // if thread is not running get out of method
        // if method is running make it false and stop it
        running = false;
        try {
            thread.join(); // joins all threads until it dies
        } catch (InterruptedException e) { // if this fails then we have a catch error
            e.printStackTrace();
        }
        System.exit(1);
    }

    // game loop - the heart of the game - *this is necessary*
    public void run() {
        // calling in our function init()
        init();
        // The whole purpose for this is to work with almost all computers old and new
        // and get tha game started
        long time = System.nanoTime();
        final double time_ticks = 60.0; // frame per second
        double sec = 1000000000 / time_ticks;
        double delta = 0; // calculates time passed and catch up if fps is backed up

        // this is for me
        long timer = System.currentTimeMillis();

        while (running) {
            // game loop
            long now = System.nanoTime(); // difference from time to now
            delta += (now - time) / sec;
            time = now;
            if (delta >= 1) {
                tick();
                delta--;
            }
            render();
            // down below, I could delete later
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    private void tick() {
        p.tick();
        c.tick();
        e.tick();
        b.tick();
        sharky.tick();
        inkMissile();
    }

    // render is creating a buffer strategy
    // A buffer strategy is handling all the buffering behind the scene
    private void render() {

        // returns buffer strategy here we just initialize it (just once)
        BufferStrategy buff = this.getBufferStrategy();
        // getBufferStrategy always return null because we haven't created it
        if (buff == null) {
            createBufferStrategy(3);
            return;
        }

        // creates a graphics context for drawing buffers
        Graphics g = buff.getDrawGraphics();

        // gets height and width of image
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        // outputs background for menu
        g.drawImage(background, 0, 0, null);

        // click play button -> load a different image in background, state switches
        // to game prompting this if statement to be true and launching the new
        // background
        if (State == STATE.GAME) {
            g.drawImage(levelOne, 0, 0, null);
            if (c.level == 2) {
                g.drawImage(levelTwo, 0, 0, null);
            }
            if (c.level == 3) {
                g.drawImage(levelThree, 0, 0, null);
            }
            // b.render(g);
            p.render(g);
            c.render(g);

        } else if (State == STATE.MENU) {
            menu.render(g);
        }
        if (State == STATE.HELP) {
            g.drawImage(background, 0, 0, null);
            helpObj.render(g);
        }

        g.dispose(); // gets rid of everything because next time it loops it will make
                     // creteBufferStrategy null again
        buff.show(); // shows buffer strategy
    }

    public void inkMissile() {
        if ((b.getY() % 65) == 0) {
            c.addInk(new InkSplat(b.getX(), b.getY(), this, ink));
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            p.setVelY(-6);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(6);
        }
        if (key == KeyEvent.VK_SPACE && !is_shooting) {
            // space bar launches torpedo
            is_shooting = true;
            c.addTorpedo(new Torpedo(p.getX(), p.getY(), this, missile));
            // c.addInk(new InkSplat(b.getX(), b.getY(), this, ink));
        }
        if (key == KeyEvent.VK_Q) {
            // Q quits game
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            is_shooting = false;
        }
    }

    public static void updateGamesPlayed() {
        noGamesPlayed++;
    }

    public static void resetGame() {
        Game game = new Game();
        JFrame frame = new JFrame(game.title);
        frame.setVisible(false);
        frame.dispose();
        updateGamesPlayed();
        c.level = 1;
        c.startGame();
        new Game();
    }

    public static void main(String[] args) {

        Game.setScreenDimensions();
        Game game = new Game();
        game.setPreferredSize(new Dimension(width * scale, height * scale));
        game.setMaximumSize(new Dimension(width * scale, height * scale));
        game.setMinimumSize(new Dimension(width * scale, height * scale));

        JFrame frame = new JFrame(game.title);
        frame.add(game); // takes dimensions above
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // if we want it to be resizable or not
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // starts game
        game.start();

    }
}
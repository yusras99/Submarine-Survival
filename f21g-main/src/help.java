import java.awt.*;

public class help {
    //Create a help box to put text inside it.
    public Rectangle helpBox = new Rectangle(Game.width/5,150,935,500);
    //create rectangles to put buttons on the help page
    public Rectangle play = new Rectangle(Game.width/6+237,700,100,50);
    public Rectangle menuButt = new Rectangle(Game.width/2+270,700,125,50);
    public Rectangle quit = new Rectangle(Game.width/1+237,700,100,50);

    //Anything we draw inside Menu is what will be rendered
    public void render(Graphics g){
        //this will allow us to draw out our rectangle
        Graphics2D twoD = (Graphics2D) g;
        //this is just the font we will be using it can be change whenever
        Font fnt0 = new Font("Kristen ITC", Font.BOLD,50);
        //setting font
        g.setFont(fnt0);
        //color of font
        g.setColor(Color.white);
        //Give the headline of the page
        g.drawString("Submarine Survival Instructions", Game.width/7+100,100);
        //Select a bugger font for heading
        Font fnt2 = new Font("Kristen ITC", Font.BOLD,20);
        //setting font
        g.setFont(fnt2);
        //Write instructions in the help box.
        g.drawString("Welcome to Submarine Survival!", helpBox.x+12, helpBox.y+35);
        g.drawString("In this game, your goal is to defeat all the enemies and clean up the ocean.", helpBox.x+12, helpBox.y+75);
        g.drawString("The submarine can move up and down by pressing the up and down arrow keys.", helpBox.x+12, helpBox.y+115);
        g.drawString("The submarine can shoot a missile by pressing the space bar.", helpBox.x+12, helpBox.y+155);
        g.drawString("There are 7 levels, and 7th level is the boss level", helpBox.x+12, helpBox.y+195);
        g.drawString("You can complete each one by eliminating all the enemies on the screen.", helpBox.x+12, helpBox.y+235);
        g.drawString("You have 3 lives in total and that is the maximum you can have.", helpBox.x+12, helpBox.y+275);
        g.drawString("You can lose your lives by getting hit by the enemies or incoming trash and ink.",helpBox.x+12, helpBox.y+315);
        g.drawString("However, you can boost your lives by hitting some special enemies",helpBox.x+12, helpBox.y+355);
        g.drawString("If you lose all 3 lives it is Game Over and you can try again.", helpBox.x+12, helpBox.y+395);
        //Draw the help box in which there will be all the instructions.
        twoD.draw(helpBox);
        //what font we will be using
        Font fnt1 = new Font("Kristen ITC",Font.BOLD,30);
        //setting font
        g.setFont(fnt1);
        //writing what will be inside the rectangle
        g.drawString("Play",play.x +19, play.y +35);
        //drawing out the play button
        twoD.draw(play);
        //writing what will be in the rectangle
        g.drawString("Menu",menuButt.x +15, menuButt.y +35);
        //drawing out the menu button that will take you to the menu
        twoD.draw(menuButt);
        //writing what will be in the rectangle
        g.drawString("Quit",quit.x +15, quit.y +35);
        //drawing out the quit button
        twoD.draw(quit);
    }
}


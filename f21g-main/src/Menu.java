import java.awt.*;

public class Menu {
    //This creates the rectangles on the screen  x and y is where we positioned it and height is big we want the box
    public Rectangle play = new Rectangle(Game.width/2+237,250,100,50);
    public Rectangle help = new Rectangle(Game.width/2+237,350,100,50);
    public Rectangle quit = new Rectangle(Game.width/2+237,450,100,50);


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
        //what we are going to display
        g.drawString("Submarine Survival", Game.width/2+50,200);


        //what font we will be using
        Font fnt1 = new Font("Kristen ITC",Font.BOLD,30);
        //setting font
        g.setFont(fnt1);
        //writing what will be inside the rectangle
        g.drawString("Play",play.x +19, play.y +35);
        //drawing out the play button
        twoD.draw(play);
        //writing what will be inside the rectangle
        g.drawString("Help",help.x +15, help.y +35);
        //drawing out the help button
        twoD.draw(help);
        //writing what will be in the rectangle
        g.drawString("Quit",quit.x +15, quit.y +35);
        //drawing out the quit button
        twoD.draw(quit);
    }
}

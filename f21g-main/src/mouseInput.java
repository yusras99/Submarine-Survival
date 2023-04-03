import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    // we want this one because we are clicking with our mouse
    public void mousePressed(MouseEvent e) {
        // takes value of e and return x value of our mouse
        int mx = e.getX();
        // takes value of e and return y value of our mouse
        int my = e.getY();

        // public Rectangle play = new Rectangle(Game.width/2+237,250,100,50);
        // public Rectangle help = new Rectangle(Game.width/2+237,350,100,50);
        // public Rectangle quit = new Rectangle(Game.width/2+237,450,100,50);


        // quit button - this if statement is just saying the coordinates to where to click
        // the info to input here are the rectangles we did, so I copy the info from Menu file to here and commented them out
        // to look as a reference and above we can tell which button we are pressing or what we want to press
        if(mx >= Game.width/2 + 237 && mx <= Game.width/2+350){
            //the clicking range inside the box
            if(my >=250 && my <=300 ){
                //ends game
                Game.State = Game.STATE.GAME;
            }
        }
        if(mx >= Game.width/2 + 237 && mx <= Game.width/2+350){
            // the clicking range inside the box
            if(my >=450 && my <=500 ){
                // ends game
                System.exit(1);
            }
        }
        if(mx >= Game.width/2 + 237 && mx <= Game.width/2+350){
            if(my >=350 && my <=400 ){
                // help button
                // still not done
                Game.State = Game.STATE.HELP;
            }
        } else if(Game.State == Game.STATE.HELP) {
            //The play button on the help page that will take us to the game
            if(mx >= Game.width/6 + 237 && mx <= Game.width/6 + 338){
                if(my >=700 && my <=750 ){
                    //Goes back to menu screen
                    Game.State = Game.STATE.GAME;
                }
            }
            //Deal with the Menu button on the help page
            if(mx >= Game.width/2 + 237 && mx <= Game.width/2 + 363){
                if(my >=700 && my <=750 ){
                    //Brings us back to the menu page
                    Game.State = Game.STATE.MENU;
                }
            }
            //The quit button on the help page
            if(mx >= Game.width/1 + 237 && mx <= Game.width/1 + 338){
                if(my >=700 && my <=750 ){
                    //ends game
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
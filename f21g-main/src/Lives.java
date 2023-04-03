
public class Lives {
    //variable to keep track of the lives that we have
    public int currentLife = 3;
    public void addLife (int currentLife){
        //increment one to the current lives
        currentLife += 1;
    }
    public void lostLife (int currentLife){
        //Decrement a life from current lives and update it
        currentLife -= 1;
    }


    //maybe do this here instead of player class?
    //private Player player; to call the player class?
    // public void lifeTracker (Player player){
        //player = new Player();
        //if player hit by enemy{ }
            //call the life class with lostLife method;
        //elif player hit by bonus object
            //call life class with addLife method
        //elif currentLife == 0;
            //game over screen
            //return to main menu
    //}


}

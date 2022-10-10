/**
 * First level
 **/

public class SecretBase extends StoryLevel{
    private String status;
    private final int INC_HP = 10;

    //unique storyline for first level
    @Override
    public String storyline()
    {
        status = "It's time to leave your Secret Base and start your adventure!\nBut oh no! You can't find your keys and the door is locked!\n";
        status += "\nAnswer this question right to get the keys to get out!";

        return this.status;
    }


    //change player's hp by final int declared above
    @Override
    public void changeHp(Player ply){
        ply.setHp(INC_HP);
    }

}


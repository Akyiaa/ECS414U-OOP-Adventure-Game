/**
 * Third level of storyline
 **/

public class Trickster extends StoryLevel{
    private String status;
    private final int INC_HP = 30;
    private static int track = 0; //tracks the number of times user has been on ths level

    //returns string of unique storyline at this level
    @Override
    public String storyline()
    {
        status = "On your way out of the village you meet a Trickster!\nHe's trying to steal your map which leads to the gold!\n";
        status += "\nHe's challenging you to a fight! Answer this question correctly to defeat him!!!";
        return this.status;
    }

    //changes player's hp depending on final int declared above
    @Override
    public void changeHp(Player ply){
        if(track < 2)
            ply.setHp(INC_HP);

        track++;
    }

}

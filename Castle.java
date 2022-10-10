/**
 * Last/Fifth level
 **/

public class Castle extends StoryLevel{
    private String status;
    private final int INC_HP = 30;
    private static int track = 0; //tracks the number of times user has been on ths level

    //unique storyline for last level
    @Override
    public String storyline()
    {
        status = "You've reached the final step!\nGet this last question right and if you're lucky, you can grab the gold!!\n";
        return this.status;
    }

    //change player's hp by final int declared above
    @Override
    public void changeHp(Player ply){
        if(track < 2)
            ply.setHp(INC_HP);

        track++;
    }
}

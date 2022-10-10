import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fourth level
 **/

public class DragonLair extends StoryLevel{
    private String status;
    private final int INC_HP = 35;
    private static int track = 0; //tracks the number of times user has been on ths level

    //storyline to display
    @Override
    public String storyline()
    {
        status = "You escaped the Trickster!\nBut you now find yourself in a dragon's liar????\n";
        status += "\nAnswer correctly to defeat the dragon!!!";

        return this.status;
    }

    //change player hp by above declared int
    @Override
    public void changeHp(Player ply){
        if(track < 2)
            ply.setHp(INC_HP);

        track++;
    }

    //ask user to choose whether to slay a dragon
    public void slayDragon(Player ply)
    {
        final Player PLAYER = ply;

        Possessions player_possessions = PLAYER.getPossessions();//accessing players possessions - outer class
        Possessions.Weapons weapon_item = player_possessions.new Weapons();//accessing Weapons class in Possession - inner class

        PopUp slay = new PopUp();

        Label l = new Label("Weapon obtained: " + weapon_item.dragonWeapon() + ":-     Slay the dragon when you defeat it?");
        final TextField TXT = new TextField(20);
        slay.add(l);
        slay.add(TXT);

        //letting the user know of how many possessions they have with the addition of
        System.out.println("You have obtained " + player_possessions.getItemsNum() + " item(s) so far. Check your items by clicking the 'Player STATUS' button");

        slay.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = TXT.getText();
                if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
                    PLAYER.killsDragon("yes");
                else
                    PLAYER.killsDragon("no");
            }
        });
        slay.activate();
    }

}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Second level
 **/

public class Village extends StoryLevel{
    private String status;
    private final int INC_HP = 20;
    private static int track = 0; //tracks the number of times user has been on this level

    //return string of current level's storyline
    @Override
    public String storyline()
    {
        status = "You made it out and into the village!\nBut it seems to have been attacked by a plague, there is no-one around:(\n";
        status += "\nAnswer the next question to leave before you catch something nasty!";

        return this.status;
    }

    //changes hp by final int above
    @Override
    public void changeHp(Player ply){
        if(track < 2)
            ply.setHp(INC_HP);

        track++;
    }

    //asks the player if they want to keep a cat which later affects their HP
    public void keepCat(Player ply)
    {
        final Player PLAYER = ply;
        PopUp cat = new PopUp();

        Label l = new Label("Wait look!  An abandoned cat!:-  Do you want to keep it?");
        final TextField TXT = new TextField(20);
        cat.add(l);
        cat.add(TXT);

        cat.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = TXT.getText();
                if(ans.equals("y") || ans.equals("yes") || ans.equals("Yes") || ans.equals("Y"))
                    PLAYER.takesCat("yes");
                else
                    PLAYER.takesCat("no");
            }
        });

        cat.activate();
    }
}

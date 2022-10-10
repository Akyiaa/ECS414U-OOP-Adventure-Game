import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatusPage {

    //displays the total information about current player
    public static String displayPlayerStatus(Player current_player)
    {
        String info = "";

        //accumulating all player info
        info += "Name: \t\t\t\t" + current_player.getName() + "\n";
        info += "\nLevel: \t\t\t\t" + current_player.getLevel() + "\n";
        info += "\nHealth Points: \t\t\t" + current_player.getHp() + "\n";
        info += "\nTaken the cat: \t\t\t" + catPossession(current_player) + "\n";
        info += "\nKilled the dragon: \t\t" + dragonSlayed(current_player) + "\n";
        info += "\nAll Questions Correct: \t\t" + allQuestionsCorrect(current_player) + "\n";
        info += "\nGold Adventurer: \t\t\t" + goldAdventurer(current_player) + "\n";

        //accessing player possessions
        info +="\nTotal Possessions: \t\t" ;

        //get player's possession arraylist
        Possessions ply_possession = current_player.getPossessions();//accessing outerclass
        ArrayList<String> possession_list = ply_possession.getPossessionList();//accessing arraylist

        //prevent duplicates in arraylist
        possession_list = removeDuplicates(possession_list);

        //print information in arraylist
        for (String s : possession_list) {
            info += s + ", ";
        }

        return info;
    }


    //Java Collection Framework
    //remove duplicates in the possessions arraylist
    public static ArrayList<String> removeDuplicates(ArrayList<String> poss_list)
    {
        Set<String> s = new HashSet<>();//Sets do not accept duplicates
        ArrayList<String> poss_list_1 = new ArrayList<>();

        for(String j : poss_list) {
            if(s.add(j))//if p.add(j) returns true (i.e set accepts it because it is not a duplicate)
                poss_list_1.add(j);//add it to arraylist
        }

        return poss_list_1;
    }


    //returns a string value showing if user kept a cat in the game
    public static String catPossession(Player player_x)
    {
        if (player_x.tookCat())
            return "Yes";
        else
            return "No";
    }

    //returns a string value showing if user killed a dragon in the game
    public static String dragonSlayed(Player player_x)
    {
        if (player_x.killedDragon())
            return "Yes";
        else
            return "No";
    }

    //returns a string value showing if user got all questions correct in the game
    public static String allQuestionsCorrect(Player player_x)
    {
        if(player_x.checkIfAllCorrect())
            return "Yes";
        else
            return "No";
    }

    //returns a string value showing if user gets gold in the game
    public static String goldAdventurer(Player player_x)
    {
        if(player_x.getLevel() >= 4 && player_x.getHp() > 100)
            return "Yes";
        else
            return "No";
    }

}

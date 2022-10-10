import java.util.*;

public class LeaderBoardPage{
    private static final int MAX_POINTS = 100;


    //display's information about all players in current game
    public static String displayLeaderboard(ArrayList<Player> playerList)
    {
        String note = "\n\nNOTE: You must reach level 4 and have an HP of OVER " + MAX_POINTS + " to be able to get the gold";
        String players_board = listOfPlayers(playerList);
        String gold_list = listOfGoldPlayers(playerList);

        return players_board + gold_list + note;
    }

    //display all players' name, level and hp
    public static String listOfPlayers(ArrayList<Player> playerList)
    {
        String players_info = "\t\t\t***LEADERBOARD***\n\n";

        for(Player current_player : playerList)
            players_info+= "Player: " + current_player.getName() + " \tLevel: " + current_player.getLevel() + " \t\tHP: " + current_player.getHp() + "\n";

        return players_info;
    }

    //display the names of players who have got Gold
    public static String listOfGoldPlayers(ArrayList<Player> playerList)
    {
        String goldList = "\n\nAdventurers who got the gold!: ";

        for(Player current_player : playerList)
        {
            if(current_player.getHp() > MAX_POINTS && current_player.getLevel() >= 4)
                goldList += current_player.getName() + ",\t";
        }

        return goldList;
    }

}

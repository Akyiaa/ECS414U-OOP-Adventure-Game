import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LeavePage{

    //message to display when user clicks on LEAVE page button
    public static String welcomeMessage(String name)
    {
        String text = "Hi " + name + ",\n";
        text+= "This is the LEAVE page";
        return text;
    }

    //takes player's response to if they want to leave the game and calls relevant subsequent methods.
    public static void exitGame(String exit, ArrayList<Player> ply_list)
    {
        if(exit.equalsIgnoreCase("y") || exit.equalsIgnoreCase("yes")) {
            saveThisGame(ply_list);
        }
    }

    //if user decides to leave, prompt asks if they want to save the game
    public static void saveThisGame(ArrayList<Player> ply_list)
    {
        final ArrayList<Player> PLAYER_LIST = ply_list;
        PopUp save_game = new PopUp();

        Label l = new Label("Save this game before you leave?");
        final TextField TXT = new TextField();
        save_game.add(l);
        save_game.add(TXT);

        save_game.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = TXT.getText();
                if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
                    System.exit(0);
                else
                    getFileNameToSaveGame("What file name should I save this game under?", PLAYER_LIST);
            }
        });
        save_game.activate();
    }


    //asks user for file name to save game under and passes it to the method that writes the objects to file
    public static void getFileNameToSaveGame(String q, ArrayList<Player> ply_list)
    {
        final ArrayList<Player> PLAYER_LIST = ply_list;
        PopUp save_file = new PopUp();

        Label l = new Label(q);
        final TextField TXT = new TextField();
        save_file.add(l);
        save_file.add(TXT);

        save_file.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = TXT.getText();
                writeObjToFile(ans, PLAYER_LIST);
            }
        });

        save_file.activate();
    }


    //File Input
    //write all player objects in arraylist to file, then exit
    //if file cannot be saved, catch exception, notify the user, then exit
    public static void writeObjToFile(String fileName, ArrayList<Player> ply_list)
    {
        try{
            FileOutputStream write = new FileOutputStream(fileName);
            ObjectOutputStream writeIn = new ObjectOutputStream(write);

            for(Player p : ply_list) {
                writeIn.writeObject(p);
            }

            writeIn.close();
            System.out.println("File successfully saved");

        }catch (Exception exc){
            System.out.println("System Error. File not saved");
        }

        System.exit(0);
    }

}

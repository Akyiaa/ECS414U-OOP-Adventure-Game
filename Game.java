import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends Frame{

    //Display text
    private static final String DISPLAY = "\t\t**************READ ME!!!**************\n\nWelcome to Adventure Time!\n\nYou have a set of questions to answer to pass each level of the story\n\nAnswer each question correctly, until the final level: CASTLE!\n\nTo start a new game as a new Player, click the 'Start New Game' button!\nYou will be prompted to enter your name in the terminal\n\nGood Luck!";
    private static TextArea mainArea = new TextArea(DISPLAY, 15, 40);

    //Keep track of Player in arraylist
    private static ArrayList<Player> playerList = new ArrayList<>();


    /********METHODS*********/
    //print to display page
    public static void print(String text){
        mainArea.setText(text);
    }

    //Standard I/O
    //Keyboard input and printing to terminal
    public static String getPlayerName()
    {
        return inputString("\nWhat is your name?");
    }
    /*********************************/



    /***********CONSTRUCTOR*********************/
    public Game()
    {
        this.setLayout(new FlowLayout());


        /***********************BUTTONS************************/

        //START ------------------------------------------------------
        Button start_btn = new Button("START New Game");
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show instructions each time button is clicked
                print(DISPLAY);
                PopUp intro_start = new PopUp();

                //create label and textfield
                Label l = new Label("Do you want to load previous game (y/n)?");
                final TextField TXT = new TextField(10);
                intro_start.add(l);
                intro_start.add(TXT);

                //add new player to list
                playerList.add(new Player(getPlayerName()));

                intro_start.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String loadGame = TXT.getText();
                        StartPage.gameStart(loadGame, playerList);
                    }
                });
                //create new frame for each player to play game
                new StartPage(playerList);

                intro_start.activate();
            }
        });
        this.add(start_btn);


        //STATUS -------------------------------------------------
        Button status_btn = new Button("Player STATUS");
        status_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    print(StatusPage.displayPlayerStatus(playerList.get(playerList.size() - 1)));
                }
                catch(IndexOutOfBoundsException i) {//if a player has not been added to the game yet
                    print("When a New Player starts the game, you can view their status here.");
                }
            }
        });
        this.add(status_btn);


        //LEADERBOARD ------------------------------------------------------
        Button leadBoard_btn = new Button("See LEADERBOARD");
        leadBoard_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print(LeaderBoardPage.displayLeaderboard(playerList));
            }
        });
        this.add(leadBoard_btn);


        //LEAVE ------------------------------------------------------
        Button leave_btn = new Button("LEAVE Game");
        leave_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PopUp intro_leave = new PopUp();

                //Display personalised message to player on leave page
                //if no player added yet, display as 'Player 1'
                try {
                    print(LeavePage.welcomeMessage("Player " + (playerList.get(playerList.size() - 1).getName())));
                }
                catch (IndexOutOfBoundsException ignored){}//if player had not been added to game yet; just make popup appear

                //create textfield and label
                Label l = new Label("Do you want to leave the game? (y/n)?");
                final TextField TXT = new TextField(10);
                intro_leave.add(l);
                intro_leave.add(TXT);

                intro_leave.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String leaveGame = TXT.getText();
                        LeavePage.exitGame(leaveGame, playerList);
                    }
                });
                intro_leave.activate();
            }
        });
        this.add(leave_btn);

        /***************************************************************************************/



        // Output console
        mainArea.setEditable(false);
        this.add(mainArea);

        //Close window
        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);

        this.setSize(600,400);
        this.setLocationRelativeTo(null);//center
        this.setVisible(true);

    }


    //Create Game object
    public static void main(String[] args) {
        new Game();
    }


    //Prints a String value to terminal and reads keyboard input
    public static String inputString(String msg)
    {
        System.out.println(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}


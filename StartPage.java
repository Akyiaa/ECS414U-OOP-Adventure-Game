import java.awt.*;
import java.awt.event.*;
import java.awt.print.Book;
import java.util.*;
import java.io.*;


public class StartPage{
    //Display area for Questions
    private static final String DISPLAY = "***TOP TIP:***\n\nMove this frame slightly to the side\n\nREMEMBER to read this page first before answering your questions!";
    private static TextArea main_area = new TextArea(DISPLAY, 10, 35);
    private static final int LAST_LEVEL = 4;

    //print to text area
    public static void print(String text){
        main_area.setText(text);
    }

    /*****CONSTRUCTOR********/
    public StartPage(ArrayList<Player> ply_list)
    {
        Frame f = new Frame("GAME SESSION: " + ply_list.size());
        f.setLayout(new FlowLayout());

        final ArrayList<Player> PLAYER_LIST = ply_list;
        print(DISPLAY);

        /***************START GAME BUTTON*************/
        Button start = new Button("Next Question");
        start.addActionListener(new ActionListener() {
            //Quiz set chosen for each player
            QuizSet quiz = selectSet();
            //used to track questions
            int qn_count = 0;

            //when 'NEXT Question' button is clicked;
            @Override
            public void actionPerformed(ActionEvent e) {
                //current player is the last player added to array list
                Player current_player = PLAYER_LIST.get(PLAYER_LIST.size()-1);

                //track number of questions with user's level to monitor when user reaches end of the game
                if(qn_count == quiz.listLength() && current_player.getLevel() != LAST_LEVEL) {//if max num of questions reached and player is not at last level
                    print("Oh no, you answered too many questions incorrectly :(\nYou will not be able to reach the castle\n\nYou can close this window!");
                }
                else if (current_player.getLevel() >= LAST_LEVEL) {//player has reached the last level
                    print("You made it to the end of the game!\nSee the leaderboard to see if you got the gold!\n\nYou can close this window!");
                }
                else{
                    //Presents each question one at a time with backstory for each level
                    askQuestion(quiz, qn_count, current_player); //make questions appear
                    print(narrateStory(current_player)); //display backstory
                    qn_count++;
                }
            }
        });
        f.add(start);
        /*******************************************/



        /************************************/
        // Output console
        main_area.setEditable(false);
        f.add(main_area, BorderLayout.SOUTH);

        //close little window
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((Frame)(e.getSource())).dispose();
            }
        });

        f.setSize(500,250);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }



    /********************METHODS********************************/

    //Pick a random quizset and return to 'Next Question' button
    public static QuizSet selectSet()
    {
        final int LIMIT = 3;
        int randomNum =  generateRand(LIMIT);

        String qnPath = "";
        String ansPath = "";

        switch (randomNum){
            case 1:
                qnPath = "svt_questions.txt";
                ansPath = "svt_answers.txt";
                break;
            case 2:
                qnPath = "nct_questions.txt";
                ansPath = "nct_answers.txt";
                break;
            case 3:
                qnPath = "twice_questions.txt";
                ansPath = "twice_answers.txt";
                break;
        }

        QuizSet quiz = new QuizSet(qnPath, ansPath);
        return quiz;
    }

    //Generate random number and return it
    public static int generateRand(int num)
    {
        Random rand = new Random();
        return rand.nextInt(num) + 1;
    }

    //For each level of the player, display different storyline and change player HP
    //call specific methods that brings up pop-up boxes for specific storylines
    /**POLYMORPHISM**/
    public String narrateStory(Player player_x)
    {
        int level = player_x.getLevel();
        String return_msg = "****READ ME FIRST****\n\n";

        StoryLevel story;//declaration of abstract class

        switch (level){
            case 0:
                story = new SecretBase();//assignment to subclasses
                story.changeHp(player_x);
                return_msg += story.storyline();
                break;
            case 1:
                story = new Village();
                story.changeHp(player_x);
                return_msg += story.storyline();
                //keepCat() is unique to class Village only, so cast story to type Village
                ((Village)story).keepCat(player_x);
                break;
            case 2:
                story = new Trickster();
                story.changeHp(player_x);
                return_msg += story.storyline();
                break;
            case 3:
                story = new DragonLair();
                story.changeHp(player_x);
                return_msg += story.storyline();
                //slayDragon() is unique to class DragonLair only, so cast story to type DragonLair
                ((DragonLair)story).slayDragon(player_x);
                break;
            case 4:
                story = new Castle();
                story.changeHp(player_x);
                return_msg += story.storyline();
                break;
        }

        return return_msg;
    }


    //ask question in pop up boxes then check answer on submit
    public static void askQuestion(QuizSet quiz, int count, Player player_x)
    {
        final QuizSet QZ_SET = quiz;
        final int INDEX = count;
        final Player PLAYER = player_x;

        PopUp qna = new PopUp();
        //get question from questions array
        String nextQn = QZ_SET.getQuestion(INDEX);

        Label l = new Label(nextQn);
        final TextField TXT = new TextField();
        qna.add(l);
        qna.add(TXT);

        qna.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = TXT.getText();
                checkUserAns(ans, QZ_SET, INDEX, PLAYER);
            }

        });
        qna.activate();
    }

    //check user answer with answer read from file.
    //also present different story lines based on user's input
    public static void checkUserAns(String user_ans, QuizSet q, int i, Player player_x) {
        //get correct answer from answers array
        String correct_ans = q.getAnswer(i);
        String obtained_item = "";
        String alternate_storyline = "";

        if (user_ans.equalsIgnoreCase(correct_ans)) {
            player_x.levelUp();//add one to player level

            obtained_item += possessionObtained(player_x);

            //if at the right storylevel, get diff storyline depending on whether the user;
            if(player_x.getLevel()==3)
                alternate_storyline = tookTheCat(player_x);//took the cat
            if(player_x.getLevel() == LAST_LEVEL)
                alternate_storyline = slewTheDragon(player_x);//slew the dragon

            print("Correct!\nCurrent Level: " + player_x.getLevel() + obtained_item + alternate_storyline + "\nYour scores are being calculated...\nLook at the leaderboard, ('See LEADERBOARD' button) to see if you got the gold!");
        }
        else {
            print("Wrong answer!" + "\nBut let's keep trying!");
            player_x.didNotGetAllCorrect();
        }
    }

    //add to the possessions the player obtains depending on their level and choices
    //displays the names of items got in string format on the results page
    public static String possessionObtained(Player ply)
    {
        //access player's possessions
        Possessions player_possessions = ply.getPossessions();//accessing players possessions - outer class
        Possessions.Bonus bonus_item = player_possessions.new Bonus();//accessing Bonus class in Possession - inner class

        String item_obtained = "\n\n***";
        int x = 0;//used in if statement to print a statement at specified times.

        //If player passed level 1 i.e. got out of secret base -> key obtained
        if(ply.getLevel() == 1) {
            item_obtained += bonus_item.keyBonus();
        }
        else if(ply.getLevel() == 2 && ply.tookCat()){//if player took the cat -> cat obtained
            item_obtained += bonus_item.catBonus();
        }
        else if(ply.getLevel() == 3 && ply.killedDragon()){//now player is facing trickster. win and go to level 3
            item_obtained += bonus_item.mapBonus();
        }
        else if(ply.getLevel() == LAST_LEVEL && ply.checkIfAllCorrect()){//if the player got no question wrong
            item_obtained += bonus_item.allCorrect();
            ply.setHp(25);
        }
        else {
            x = 1;
            item_obtained = "\n\n";
        }

        if(x == 0)
            System.out.println("You have obtained " + player_possessions.getItemsNum() + " item(s) so far. Check your items by clicking the 'Player STATUS' button");

        return item_obtained + "***\n";
    }

    //return string about storyline and change HP depending on if user is on correct storylevel(i.e 3) and took the cat
    public static String tookTheCat(Player ply)
    {
        String catChoice = "";

        //is user is on level 3
        if(ply.tookCat()) {
            //and the user took the cat
            catChoice += "Yay! The trickster noticed your cat from the abandoned village and took that instead of fighting for your map!\nYour hp has increased by 15!\n";
            ply.setHp(15);
        }
        else{
            //user did not take cat
            catChoice += "Oh no! You won the fight but got a serious injury!\nYour hp has reduced by 15!\n";
            ply.setHp(-15);
        }

        return catChoice;
    }

    //return string about storyline and change HP if user slew the dragon
    public static String slewTheDragon(Player ply)
    {
        String dragonChoice = "\n";

        if(ply.killedDragon()) {
            dragonChoice += "You found the castle but you have to dangerously climb up the tower!\nYou got in, but your hp dropped a lot\n";
            ply.setHp(-30);
        }
        else {
            dragonChoice += "The dragon flew you up and straight into the castle!\nYou saved a lot of energy and increased your HP!\nGood going!\n";
            ply.setHp(20);
        }

        return dragonChoice;
    }


    //First pop-up upon clicking 'START NEW GAME' button
    //if user decides to start new game, calls method to load game from specified file
    public static void gameStart(String loadGame, ArrayList<Player> playerList) {

        if (loadGame.equalsIgnoreCase("y") || loadGame.equalsIgnoreCase("yes")) {
            String question = "Which file should I reload the game from?";
            getFileNameToReloadGame(question, playerList);
        }
    }

    //prompt user for name of file to reload game from
    //pass it on to method to read objects saved in file
    public static void getFileNameToReloadGame(String qn, ArrayList<Player> playerList)
    {
        final ArrayList<Player> PLAYER_LIST = playerList;
        PopUp qn_ans = new PopUp();

        Label l = new Label(qn);
        final TextField TXT = new TextField();
        qn_ans.add(l);
        qn_ans.add(TXT);

        qn_ans.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = TXT.getText();
                readObjFromFile(ans, PLAYER_LIST);
            }
        });
        qn_ans.activate();
    }


    //File Output
    //READ Player objects from file (file name given by previous method call)
    public static ArrayList<Player> readObjFromFile(String fileName, ArrayList<Player> playerList)
    {
        int i = 0;//keep track of number of players added to arraylist

        try {
            FileInputStream readFile = new FileInputStream(fileName);
            ObjectInputStream readIn = new ObjectInputStream(readFile);

            System.out.println("\nPrevious Game being loaded...\nPrevious Players added: \n");

            Object obj;
            //read file until null is reached
            while((obj = readIn.readObject()) != null)
            {
                Player previous_player = (Player) obj;

                //adding the player objects to the arraylist from the beginning;
                try{
                    //set loaded players from top of arraylist
                    playerList.set(i, previous_player);}
                catch(IndexOutOfBoundsException evt) {
                    //if all arraylist spaces filled, add to the end of the list
                    playerList.add(previous_player);}

                System.out.println("Player " +  (i+1) + "\tName: " + playerList.get(i).getName() + "\t\tLevel: " + playerList.get(i).getLevel());
                i++;
            }

            readIn.close();

        }
        catch (FileNotFoundException e) {
            print("File not found. A new game has been started.\n\nClick 'Next Question' to continue playing as Player " + playerList.get(playerList.size()-1).getName());
        }
        catch (EOFException e) {//when the end of the file is reached
            //delete from end of newly added players to end of arraylist
            for(int j = i; j<playerList.size(); j++)
                playerList.remove(j--);

        }
        catch (IOException | ClassNotFoundException e){}
        finally {
            System.out.println("\nTo continue as a NEW Player please press the Start New Game button.\nOr Press the 'Next Question' button on the smaller pop-up box to continue the old game.\n");
        }

        return playerList;
    }

}

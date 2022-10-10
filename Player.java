import java.io.Serializable;

//implements serializable so objects can be saved in file
public class Player implements Serializable {
    //default serialVersion id
    private static final long serialVersionUID = 1L;
    private final String NAME;
    private int level;
    private int hp;
    private boolean hasCat;
    private boolean killedDragon;
    private boolean allCorrect;
    private Possessions player_possession;


    /**CONSTRUCTORS**/
    public Player(String name) {
        this.NAME = name;
        this.level = 0;
        this.hp = 0;
        this.hasCat = false;
        this.killedDragon = false;
        this.allCorrect = true;
        this.player_possession = new Possessions();
    }


    /*********METHODS**********/
    //get Player name
    public String getName() {
        return this.NAME;
    }

    //return Player's level
    public int getLevel() {
        return this.level;
    }

    //increase Player's level by 1
    public void levelUp() {
        this.level++;
    }

    //set Player's hp to specific int
    public void setHp(int n)
    {
        this.hp += n;
    }

    //return player's hp
    public int getHp()
    {
        return this.hp;
    }

    //if player decided to keep abandoned cat, change boolean value
    public void takesCat(String choice)
    {
        this.hasCat = choice.equals("yes");
    }

    //returns a boolean value which shows whether the Player has a cat or not
    public boolean tookCat()
    {
        return this.hasCat;
    }

    //if player decided to slay the dragon, change boolean value
    public void killsDragon(String choice)
    {
        this.killedDragon = choice.equals("yes");
    }

    //returns whether the player killed the dragon or not
    public boolean killedDragon()
    {
        return this.killedDragon;
    }

    //returns player's Possession obj
    public Possessions getPossessions()
    {
        return player_possession;
    }

    //if the player gets a question wrong program keeps note
    public void didNotGetAllCorrect()
    {
        this.allCorrect = false;
    }

    //this method returns a value that would check if the user got all answers correct
    public boolean checkIfAllCorrect()
    {
        return this.allCorrect;
    }
}


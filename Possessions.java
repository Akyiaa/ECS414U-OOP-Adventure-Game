import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

/**
 * EXTRA LEVEL - NESTED CLASSES
 * **/

public class Possessions implements Serializable{
    private int num_of_items = 0;
    private int item_counter;
    private ArrayList<String> possession_list = new ArrayList<>();


    /******** INNER CLASS - WEAPONS ************/
    class Weapons{
        //randomly choose weapon to slay dragon
        public String dragonWeapon()
        {
            int num = randomNumberGenerator(2);

            String weaponName = "";

            switch(num)
            {
                case 0:
                    weaponName = "Sword";
                    break;
                case 1:
                    weaponName = "Bomb";
                    break;
                case 2:
                    weaponName = "Fish";
                    break;
            }

            possession_list.add(weaponName);
            addItem();
            return weaponName;
        }

        //return a random number
        public int randomNumberGenerator(int n)
        {
            Random r = new Random();
            int num = r.nextInt(n);

            return num;
        }

    }

    /******** INNER CLASS - BONUS ************/
    //at different stages of the game, the user obtains bonuses/items
    class Bonus{
        //obtain a key to leave base when player gets to level 1
        public String keyBonus()
        {
            possession_list.add("key");
            addItem();
            return "You passed the first level: Key obtained!";
        }

        //get cat at level 2
        public String catBonus()
        {
            possession_list.add("cat");
            addItem();
            return "You kept the cat from the abandoned village: Cat obtained";
        }

        //get map on passing to level 3
        public String mapBonus()
        {
            possession_list.add("map");
            addItem();
            return "You won against the trickster!: Map obtained!";
        }

        //if the Player got all questions correct
        public String allCorrect()
        {
            possession_list.add("Genius Status");
            addItem();
            return "All questions answered on first go! Genius Status obtained.";
        }

    }

    /********METHODS OF OUTER CLASS(POSSESSIONS)************/
    //keeps count of num of items added to list
    public void addItem()
    {
        this.item_counter++;
        this.num_of_items = item_counter;
    }

    //return arraylist for player
    public ArrayList<String> getPossessionList()
    {
        return this.possession_list;
    }

    public int getItemsNum()
    {
        return this.num_of_items;
    }

}

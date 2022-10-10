/**sets out the basic requirements for all storylevel files**/
abstract class StoryLevel {

    //each storylevel has a unique storyline
    public abstract String storyline();

    //each storylevel has a unique increase in player hp
    public abstract void changeHp(Player ply);

}

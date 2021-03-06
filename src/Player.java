public class Player {
    /*
    This class defines a player variable who has a name and its personal score.
    */
    private final String name;
    private int  score;

    public Player(){
        name = "";
        score = 0;
    }

    public Player(String n){
        name = n;
        score = 0;
    }

    public String get_name(){
        return this.name;
    }

    public void add_score(){
        score += 1;
    }

    public int get_score(){
        return this.score;
    }
}

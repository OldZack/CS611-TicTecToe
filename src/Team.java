public class Team {
    /*
    This class defines a team that consists multiple players. Each team has a variable that keeps track of
    the score that the whole team wins.
    Function of each method is explained above it.
    */

    private Player [] players;
    private int score;
    private int order;

    Team(){
        players = new Player[1];
        players[0] = new Player("Player1");
        score = 0;
        order = 0;
    }

    Team(int n, int x){
        players = new Player[n];
        for (int i = 0; i < n; i++){
            String s = "Player"+ x + (i + 1);
            players[i] = new Player(s);
        }
        score = 0;
        order = 0;
    }

    /*
    Returns the number of players in the team.
    */
    public int num_players(){
        return players.length;
    }

    /*
    Returns the player who is playing.
    */
    public Player get_curr_player(){
        return players[order];
    }

    /*
    Returns the player who is playing.
    */
    public String get_curr_player_name(){
        return players[order].get_name();
    }

    /*
    Return the name of the player who is playing.
    */
    public void change_order(){
        if(order == players.length-1){
            order = 0;
        }
        else{
            order += 1;
        }
    }

    /*
    Add one to the team's score.
    */
    public void add_score(){
        score += 1;
        this.players[order].add_score();
    }

    /*
    Return the score of the team.
    */
    public int get_score(){
        return this.score;
    }

    /*
    Return the name of the player given its position in the team.
    */
    public String get_player_name(int n){
        return players[n].get_name();
    }

    /*
    Return the score of the player given its position in the team.
    */
    public int get_player_score(int n){
        return players[n].get_score();
    }

    /*
    Regenerate team that contains new set of players.
    */
    public void change_team_size(int n, int x){
        players = new Player[n];
        for (int i = 0; i < n; i++){
            String s = "Player"+ x + (i + 1);
            players[i] = new Player(s);
        }
        order = 0;
        score = 0;
    }

}

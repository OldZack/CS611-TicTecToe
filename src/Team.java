public class Team {

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

    public int num_players(){
        return players.length;
    }

    public Player get_curr_player(){
        return players[order];
    }

    public String get_curr_player_name(){
        return players[order].get_name();
    }

    public void change_order(){
        if(order == players.length-1){
            order = 0;
        }
        else{
            order += 1;
        }
    }

    public void add_score(){
        score += 1;
        this.players[order].add_score();
    }

    public int get_score(){
        return this.score;
    }

    public String get_player_name(int n){
        return players[n].get_name();
    }

    public int get_player_score(int n){
        return players[n].get_score();
    }

    public void change_team_size(int n, int x){
        players = new Player[n];
        for (int i = 0; i < n; i++){
            String s = "Player"+ x + (i + 1);
            players[i] = new Player(s);
        }
        order = 0;
    }

}

public class Team {

    private Player [] players;

    Team(){
        players = new Player[1];
        players[0] = new Player("Player1", "");
    }

    Team(int n){
        players = new Player[n];
        for (int i = 0; i < n; i++){
            String s = "Player"+ (i + 1);
            players[i] = new Player(s, "");
        }
    }

}

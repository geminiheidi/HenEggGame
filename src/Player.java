public class Player {
    private static Player instance = null;
    private String name;
    private int score;
    private int livesRemaining;
    private Player(){

    }

    public static Player getInstance(){
        if (instance == null){
            instance = new Player();
        }
        return instance;
    }

    public void initialize(String name){
        this.name = name;
        this.score = 0;
        this.livesRemaining = 3;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public void setLivesRemaining(int livesRemaining) {
        this.livesRemaining = livesRemaining;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperation {
    private String highScoreUserName;
    private int highScore;
    private BufferedReader scoreBufferReader;
    private BufferedWriter scoreBufferWriter;


    public FileOperation(){
        try{
            String scorePath = "./data/highestScore.txt";
            File scoreFile = new File(scorePath);

            //read data from file
            scoreBufferReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(scoreFile))
            );

            scoreBufferWriter = new BufferedWriter(
                    new FileWriter(scoreFile, true)
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        String highestInfor = getScore(scoreBufferReader);
        if (highestInfor == ""){
            highScoreUserName = null;
            highScore = 0;
        }else {
            String[] scoreArr = highestInfor.split(",");
            highScoreUserName = scoreArr[0];
            highScore = Integer.valueOf(scoreArr[1]);
        }
    }

    public String getHighScoreUserName() {
        return highScoreUserName;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getScore(BufferedReader in) {
        List<String> input = new ArrayList<>();

        Thread mtr1 = new Thread(new MultiThreadRunnable(input, in));
        mtr1.start();

        Thread mtr2 = new Thread(new MultiThreadRunnable(input, in));
        mtr2.start();

        try{
            mtr1.join();
            mtr2.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (input.size() < 1){
            return "";
        }
        return input.get(input.size() - 1);
    }

    public void updateHighestInformation(){
        try{
            scoreBufferWriter.newLine();
            scoreBufferWriter.write(Player.getInstance().getName() + "," + Player.getInstance().getScore());
            scoreBufferWriter.flush();
            scoreBufferWriter.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

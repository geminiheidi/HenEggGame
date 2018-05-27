import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
    private MyButton newGameBtn;
    private MyButton exitBtn;
    private MyButton scoreBtn;
    private JLabel welcomeLabel;


    private String userName;
    private String highScoreUserName;
    private int highScore;

    JLayeredPane layeredPane;
    JPanel backgroundPanel;

    MainFrame(){
        super("Welcome to Egg Game");
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - Constant.WINDOWWIDTH) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - Constant.WINDOWHEIGHT) / 2);

        layeredPane = new JLayeredPane();
        backgroundPanel = new BackgroundPanel(new ImageIcon("./data/background.jpeg").getImage());
        backgroundPanel.setBounds(0, 0, Constant.WINDOWWIDTH,Constant.WINDOWHEIGHT);

        this.setLayout(null);
        userName = getUserName();

        List<String> score = getScore();
        if (score.size() == 0){
            highScoreUserName = null;
            highScore = 0;
        }else {
            highScoreUserName = score.get(0);
            highScore = Integer.valueOf(score.get(1));
        }

        welcomeLabel = new JLabel("Welcome " + userName + " !");
        Font labelFont = new Font("Chalkboard SE", Font.BOLD, 35);
        welcomeLabel.setFont(labelFont);
        welcomeLabel.setForeground(new Color(225, 143,0, 255));
        welcomeLabel.setBounds(Constant.XOFFSET - 100, Constant.YOFFSET - 40, 400, 45);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        newGameBtn = new MyButton("New Game");
        newGameBtn.setBounds(Constant.XOFFSET, Constant.YOFFSET + 50, 200, 25);

        scoreBtn = new MyButton("Highest Score");
        scoreBtn.setBounds(Constant.XOFFSET, Constant.YOFFSET + 100, 200, 25);

        exitBtn = new MyButton("Exit");
        exitBtn.setBounds(Constant.XOFFSET, Constant.YOFFSET + 150, 200, 25);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(welcomeLabel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(newGameBtn, JLayeredPane.MODAL_LAYER);
        layeredPane.add(scoreBtn, JLayeredPane.MODAL_LAYER);
        layeredPane.add(exitBtn, JLayeredPane.MODAL_LAYER);

        this.setLayeredPane(layeredPane);

        Player curPlayer = new Player(userName);//Player??
        newGameBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        scoreBtn.addActionListener(this);

        this.setSize(Constant.WINDOWWIDTH, Constant.WINDOWHEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public List<String> getScore() {
        List<String> res = new ArrayList<>();
        List<String> input = new ArrayList<>();
        try{
            String pathname = "./data/highestScore.txt";
            File filename = new File(pathname);
            //read data from file
            @SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename))
            );
            String s;
            while((s = in.readLine()) != null) {
                if (s.equals("")) {
                    //Step over the line with all whitespace
                    continue;
                }
                //Omit the leading and trailing whitespace of s
                s = s.trim();
                input.add(s);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (input.size() < 2){
            return res;
        }else {
            res.add(input.get(input.size() - 2));
            res.add(input.get(input.size() - 1));
            return res;
        }
    }

    public String getUserName() {
        List<String> input = new ArrayList<>();
        try{
            String pathname = "./data/userName.txt";
            File filename = new File(pathname);
            //read data from file
            @SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename))
            );
            String s;
            while((s = in.readLine()) != null) {
                if (s.equals("")) {
                    //Step over the line with all whitespace
                    continue;
                }
                //Omit the leading and trailing whitespace of s
                s = s.trim();
                input.add(s);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return input.get(input.size() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameBtn){
            this.dispose();
            new GameFrame();
        }else if (e.getSource() == exitBtn){
            System.out.println("exitBtn");
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }else if (e.getSource() == scoreBtn){
            JOptionPane.showMessageDialog(this,"Highest Score Name is: " + highScoreUserName + "\n" + "Highest Score is: " + highScore, "Show Highest Score", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

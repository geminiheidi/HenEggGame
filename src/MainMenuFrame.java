import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenuFrame extends JFrame {
    private MyButton newGameBtn;
    private MyButton exitBtn;
    private MyButton scoreBtn;
    private JLabel welcomeLabel;
    private FileOperation myFile;

    private JLayeredPane layeredPane;
    private JPanel backgroundPanel;

    MainMenuFrame(){
        this.setTitle("Welcome to Egg Game");
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - ConstantDesignSize.WINDOWWIDTH) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - ConstantDesignSize.WINDOWHEIGHT) / 2);

        myFile = new FileOperation();
        layeredPane = new JLayeredPane();
        this.setLayout(null);
        Font labelFont = new Font("Chalkboard SE", Font.BOLD, 40);

        backgroundPanel = new MainMenuBackgroundPanel(new ImageIcon("./data/background.jpg").getImage());
        backgroundPanel.setBounds(0, 0, ConstantDesignSize.WINDOWWIDTH, ConstantDesignSize.WINDOWHEIGHT);

        welcomeLabel = new JLabel("Welcome " + Player.getInstance().getName() + " !");
        welcomeLabel.setFont(labelFont);
        welcomeLabel.setForeground(new Color(225, 143,0, 255));
        welcomeLabel.setBounds(0, ConstantDesignSize.YOFFSET - 50, 600, 55);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        newGameBtn = new MyButton("New Game");
        newGameBtn.setBounds(ConstantDesignSize.XOFFSET, ConstantDesignSize.YOFFSET + 50, 200, ConstantDesignSize.BUTTONHEIGHT);

        scoreBtn = new MyButton("Highest Score");
        scoreBtn.setBounds(ConstantDesignSize.XOFFSET, ConstantDesignSize.YOFFSET + 120, 200, ConstantDesignSize.BUTTONHEIGHT);

        exitBtn = new MyButton("Exit");
        exitBtn.setBounds(ConstantDesignSize.XOFFSET, ConstantDesignSize.YOFFSET + 190, 200, ConstantDesignSize.BUTTONHEIGHT);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(welcomeLabel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(newGameBtn, JLayeredPane.MODAL_LAYER);
        layeredPane.add(scoreBtn, JLayeredPane.MODAL_LAYER);
        layeredPane.add(exitBtn, JLayeredPane.MODAL_LAYER);

        this.setLayeredPane(layeredPane);

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame.this.dispose();
                new GameWindowFrame();
            }
        });
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame.this.setVisible(false);
                MainMenuFrame.this.dispose();
            }
        });
        scoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this,"Highest Score Name is: " + myFile.getHighScoreUserName() + "\n" + "Highest Score is: " + myFile.getHighScore(), "Show Highest Score", JOptionPane.PLAIN_MESSAGE, ConstantDesignSize.eggIcon);
            }
        });

        this.setSize(ConstantDesignSize.WINDOWWIDTH, ConstantDesignSize.WINDOWHEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}

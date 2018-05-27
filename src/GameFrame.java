import static javax.swing.JOptionPane.PLAIN_MESSAGE;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.tools.Tool;

public class GameFrame extends JFrame implements ActionListener{


  private final int framewidth = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
  private final int frameHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 20;
  private MyButton returnButton;
  Dimension frameSize = new Dimension(framewidth,frameHeight);
  GamePanel gamePanel;
  Timer frameTimer;


  public GameFrame(){

    this.setBounds(0,0, frameSize.width, frameSize.height);
    this.addButton();

    BoardStatus boardStatus = new BoardStatus();
    gamePanel = new GamePanel(boardStatus);
    gamePanel.setBounds(0,0, frameSize.width, frameSize.height);
    gamePanel.paintBackground();
    this.getContentPane().add(gamePanel);

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);

    frameTimer = new Timer(1,this);
    frameTimer.start();

  }

  public void addButton(){
    returnButton = new MyButton("Return");
    returnButton.setBounds(frameSize.width - 300, frameSize.height - 100, 200, 50);
    returnButton.setFont(new Font("Courier New", Font.BOLD, 30));
    returnButton.addActionListener(this);
    this.add(returnButton);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    Object src = e.getSource();
    if(src == returnButton){
      this.dispose();
      new MainFrame();
    }
    if(src == frameTimer){
      checkGameEnd();
    }

  }

  void checkGameEnd(){
    if(gamePanel.boardStatus.player.livesRemaining == 0){
      gamePanel.tm.stop();
      ImageIcon icon = new ImageIcon("./data/rottenegg.png");
      Image smallRottenEgg = icon.getImage().getScaledInstance(50,50, Image.SCALE_FAST);
      ImageIcon eggIcon = new ImageIcon(smallRottenEgg);
      int input = JOptionPane
          .showOptionDialog(null,"You failed.","Message",JOptionPane.DEFAULT_OPTION,PLAIN_MESSAGE, eggIcon,null,null);
      if(input == JOptionPane.OK_OPTION){
        this.dispose();
        new MainFrame();
        frameTimer.stop();
      }
    }
  }


//    public static void main(String[] args) {
//      new GameFrame();
//    }

  }


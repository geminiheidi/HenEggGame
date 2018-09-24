import static javax.swing.JOptionPane.PLAIN_MESSAGE;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class GameWindowFrame extends JFrame {
  private final int framewidth = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
  private final int frameHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 20;
  private MyButton returnButton;
  private MyButton pauseButton;
  boolean flag = true;
  private FileOperation myFile;
  Dimension frameSize = new Dimension(framewidth,frameHeight);
  GameWindowPanel gamePanel;
  Timer frameTimer;

  public GameWindowFrame(){

    this.setBounds(0,0, frameSize.width, frameSize.height);
    this.addButton();

    GameStatus boardStatus = new GameStatus();
    gamePanel = new GameWindowPanel(boardStatus);
    gamePanel.setBounds(0,0, frameSize.width, frameSize.height);
    gamePanel.paintBackground();
    this.getContentPane().add(gamePanel);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);

    frameTimer = new Timer((int) (boardStatus.time * 1000), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateFrame();
      }
    });
    frameTimer.start();
    myFile = new FileOperation();
  }

  public void addButton(){
    returnButton = new MyButton("Return");
    returnButton.setBounds(frameSize.width - 300, frameSize.height - 100, 250, 50);
    returnButton.setFont(new Font("Courier New", Font.BOLD, 30));
    returnButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frameTimer.stop();
        Integer input = null;
        if (Player.getInstance().getScore() >= myFile.getHighScore()) {
          myFile.updateHighestInformation();
          input = JOptionPane
                  .showOptionDialog(null, "Congratulation, you got the highest score!" + "\n"+"Your score is " + Player.getInstance().getScore() + ".", "Message", JOptionPane.DEFAULT_OPTION, PLAIN_MESSAGE, ConstantDesignSize.eggIcon, null, null);
        }else {
          input = JOptionPane
                  .showOptionDialog(null, "You exited the game. Your score is " + Player.getInstance().getScore() + ".", "Message", JOptionPane.DEFAULT_OPTION, PLAIN_MESSAGE, ConstantDesignSize.rottenIcon, null, null);
        }
        if (input == JOptionPane.OK_OPTION) {
          Player.getInstance().setScore(0);
          Player.getInstance().setLivesRemaining(3);
          GameWindowFrame.this.dispose();
          new MainMenuFrame();
        }
      }
    });
    this.add(returnButton);

    pauseButton = new MyButton("Pause");
    pauseButton.setBounds(frameSize.width - 600, frameSize.height - 100, 250, 50);
    pauseButton.setFont(new Font("Courier New", Font.BOLD, 30));
    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (flag){
          frameTimer.stop();
          GameWindowFrame.this.gamePanel.pauseButton.setToolTipText("Restart");
          GameWindowFrame.this.pauseButton.setText("Restart");
          flag = !flag;
        }else {
          frameTimer.start();
          GameWindowFrame.this.gamePanel.pauseButton.setToolTipText("Pause");
          GameWindowFrame.this.pauseButton.setText("Pause");
          GameWindowFrame.this.gamePanel.addNotify();
          flag = !flag;
        }
      }
    });
    this.add(pauseButton);
  }

  void updateFrame(){
    gamePanel.updatePanel();
    if(Player.getInstance().getLivesRemaining() == 0){
      SoundEffect.GAMEOVER.play();
      frameTimer.stop();
      Integer input = null;
      if (Player.getInstance().getScore() >= myFile.getHighScore()) {
        myFile.updateHighestInformation();
        input = JOptionPane
                .showOptionDialog(null, "Congratulation, you got the highest score!" + "\n"+"Your score is " + Player.getInstance().getScore() + ".", "Message", JOptionPane.DEFAULT_OPTION, PLAIN_MESSAGE, ConstantDesignSize.eggIcon, null, null);
      }else {
        input = JOptionPane
                .showOptionDialog(null, "You failed. Your score is " + Player.getInstance().getScore() + ".", "Message", JOptionPane.DEFAULT_OPTION, PLAIN_MESSAGE, ConstantDesignSize.rottenIcon, null, null);
      }

      if(input == JOptionPane.OK_OPTION){
        Player.getInstance().setScore(0);
        Player.getInstance().setLivesRemaining(3);
        this.dispose();
        new MainMenuFrame();
      }
    } else {
      frameTimer.start();
    }
  }
}


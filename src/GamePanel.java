import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
  private ImageIcon farm;
  private ImageIcon hen;
  BoardStatus boardStatus;
  Timer tm;
  int henY = 50;
  double basketY;
  int keyLeftRight;
  MyButton scoreButton;
  MyButton lifeButton;
  
  GamePanel(BoardStatus boardStatus){
	  
	  this.boardStatus = boardStatus;
	  tm = new Timer((int)(boardStatus.time * 1000),this);
	  addKeyListener(this);
	  keyLeftRight = 0;
	  

  }
  
  public void addLabel() {
	  double width = this.getSize().getWidth();
	  double height = this.getSize().getHeight();
	  
	  scoreButton = new MyButton("Score: " + boardStatus.player.score);
	  scoreButton.setBounds((int)width - 600, (int)height - 100, 250, 50);
	  scoreButton.setFont(new Font("Courier New", Font.BOLD, 30));
	  this.add(scoreButton);
	  
	  lifeButton = new MyButton("Life: " + boardStatus.player.livesRemaining);
	  lifeButton.setBounds((int)width - 900, (int)height - 100, 250, 50);
	  lifeButton.setFont(new Font("Courier New", Font.BOLD, 30));
	  this.add(lifeButton);
	  
	  MyButton returnButton = new MyButton("Return" );
	  returnButton.setBounds((int)width - 300, (int)height - 100, 250, 50);
	  returnButton.setFont(new Font("Courier New", Font.BOLD, 30));
	  this.add(returnButton);
  }
  
  public void addNotify() {
      super.addNotify();
      requestFocus();
  }
  
  public void keyPressed(KeyEvent e) {
		
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
	    	keyLeftRight = 1;
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
	    	keyLeftRight = -1;
	            //Left arrow key code
	    } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
	            //Up arrow key code
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
	            //Down arrow key code
	    }
//	    repaint();
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

  public void paintBackground(){
	addLabel();

    this.setLayout(null);
    double width = this.getSize().getWidth();
    double height = this.getSize().getHeight();
    basketY = height - 250;
    // draw hen
    hen = new ImageIcon("./data/hen.png");
    Image henImage = hen.getImage().getScaledInstance(120, 160, Image.SCALE_FAST);
    ImageIcon henIcon = new ImageIcon(henImage);

    JLabel henLabel1 = new JLabel(henIcon);
    henLabel1.setBounds((int)(width * 10/100), henY, 120, 160);

    this.add(henLabel1);

    JLabel henLabel2 = new JLabel(henIcon);
    henLabel2.setBounds((int)(width * 45/100), henY, 120, 160);
    this.add(henLabel2);

    JLabel henLabel3 = new JLabel(henIcon);
    henLabel3.setBounds((int)(width * 80/100), henY, 120, 160);
    this.add(henLabel3);


    // draw farm
    farm = new ImageIcon("./data/farm3.jpg");
    Image farmImage = farm.getImage().getScaledInstance((int)width, (int)height, Image.SCALE_FAST);
    ImageIcon farmIcon = new ImageIcon(farmImage);
    JLabel farmLabel = new JLabel(farmIcon);
    farmLabel.setBounds(0,0,(int)width, (int)height);
    this.add(farmLabel);

  }

  public void paint(Graphics g){
    super.paint(g);

    BufferedImage basket;
    BufferedImage egg;
    BufferedImage rottenEgg;
    double width = this.getSize().getWidth();
    double height = this.getSize().getHeight();


    try{//draw basket
      basket = ImageIO.read(getClass().getResourceAsStream("/basket.png"));
      Image smallBasket = basket.getScaledInstance(200, 160, Image.SCALE_FAST);
      g.drawImage(smallBasket, (int)((width - 200) * (boardStatus.basket.basketPos)/100), (int)basketY, 200,160,this);
      // don't change "- 30", it makes the basket and hen aligned.
    } catch(IOException basketImageError){
      System.out.println(basketImageError);
    }


    try{//draw eggs
      egg = ImageIO.read(getClass().getResourceAsStream("/egg.png"));
      Image smallEgg = egg.getScaledInstance(60, 75, Image.SCALE_FAST);
      rottenEgg = ImageIO.read(getClass().getResourceAsStream("/rottenegg.png"));
      Image smallRottenEgg= rottenEgg.getScaledInstance(100, 100, Image.SCALE_FAST);

      for(EggData a: boardStatus.eggs.get(0)) {
    	  if (a.eggType == true) {
    	      g.drawImage(smallEgg, (int) (width * 10 / 100) + 20,(int) ((henY + 150 + ((basketY - (henY + 150)) * a.eggPos / 100))), 60, 75,this);
    	    }
    	    else{
    	    	g.drawImage(smallRottenEgg, (int) (width * 10 / 100) + 20,(int) ((henY + 150 + ((basketY - (henY + 150)) * a.eggPos / 100))),100,100,this);
    	    }
      }
      for(EggData a: boardStatus.eggs.get(1)) {
    	  if(a.eggType == true) {
			g.drawImage(smallEgg, (int)(width * 45/100) + 20, (int)(henY + 150 + ((basketY - (henY + 150)) * a.eggPos/100)), 60,75,this);
    	  }
    	  else {
    		  g.drawImage(smallRottenEgg, (int)(width * 45/100) + 20, (int)(henY + 150 + ((basketY - (henY + 150)) * a.eggPos/100)), 60,75,this);
    	  }
      }
      for(EggData a: boardStatus.eggs.get(2)) {
    	  if(a.eggType == true) {
    		  g.drawImage(smallEgg, (int)(width * 80/100) + 20, (int)(henY + 150 + ((basketY - (henY + 150)) * a.eggPos/100)), 60,75,this);
    	  }
    	  else {
    		  g.drawImage(rottenEgg, (int)(width * 80/100) + 20, (int)(henY + 150 + ((basketY - (henY + 150)) * a.eggPos/100)), 60,75,this);
    	  }
			
      }
      
    } catch(IOException eggImageError){
      System.out.println(eggImageError);
    }
    tm.start();
  }

	@Override
	public void actionPerformed(ActionEvent e) {
		boardStatus.updateBoardStatus(keyLeftRight);
		scoreButton.setText("Score: " + boardStatus.player.score);
		lifeButton.setText("Life: " + boardStatus.player.livesRemaining);
		repaint();
		keyLeftRight = 0;
//		System.out.println(boardStatus.player.score);
		
	}
	
	


}


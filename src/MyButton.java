import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class MyButton extends JButton{
    public MyButton(String text){
        super(text);
        Font f = new Font("Chalkboard SE", Font.BOLD, 20);
        this.setFont(f);
        this.setBackground(new Color(225, 143,0, 255));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
    }

}

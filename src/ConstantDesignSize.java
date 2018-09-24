import javax.swing.*;
import java.awt.*;

public class ConstantDesignSize {
    public final static int WINDOWWIDTH = 600;
    public final static int WINDOWHEIGHT = 600;
    public final static int XOFFSET = 200;
    public final static int YOFFSET = 150;
    public final static int BUTTONHEIGHT = 30;
    public final static ImageIcon eggIcon = new ImageIcon(new ImageIcon("./data/egg.png").getImage().getScaledInstance(50,50, Image.SCALE_FAST));
    public final static ImageIcon rottenIcon = new ImageIcon(new ImageIcon("./data/rottenegg.png").getImage().getScaledInstance(50,50, Image.SCALE_FAST));

    public ConstantDesignSize(){

    }
}

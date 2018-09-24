import javax.swing.*;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Main{
    public static void main(String[] args){
        String userName = null;
        try{
            userName = (String) JOptionPane.showInputDialog(null,"Please enter your name!", "Welcome to Egg Game", PLAIN_MESSAGE, ConstantDesignSize.eggIcon, null,null);
            while(userName == null || userName.equals("")) {
            	if (userName.equals("")) {
            		JOptionPane.showConfirmDialog(null, "Must enter an user name!", "Warning!", PLAIN_MESSAGE, JOptionPane.OK_OPTION, ConstantDesignSize.eggIcon);
            		userName = (String) JOptionPane.showInputDialog(null, "Please enter your name!", "Welcome to Egg Game", PLAIN_MESSAGE, ConstantDesignSize.eggIcon, null, null);
            	}
            }
            Player.getInstance().initialize(userName);
            new MainMenuFrame();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

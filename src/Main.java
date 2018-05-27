import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args){
        try{
            String userName = JOptionPane.showInputDialog("Please enter your name!");
            String namePath = "./data/userName.txt";
            
            while(userName == null || userName.equals("")) {
            	if (userName.equals("")) {
            		JOptionPane.showConfirmDialog(null, "Must enter an user name!");
            		userName = JOptionPane.showInputDialog("Please enter your name!");
            	}
            }
            if (userName != null) {
            	File nameFile = new File(namePath);
                FileWriter nameWriter = new FileWriter(nameFile, true);
                BufferedWriter bw = new BufferedWriter(nameWriter);
                bw.newLine();
                bw.write(userName);
                bw.flush();
                bw.close();
                new MainFrame();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
}

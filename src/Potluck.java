import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Potluck {
    public static void main(String[] args) {
        int n;
        Dimension d = new Dimension(500,900);

        System.out.println( "Please enter the grid size: " );
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        JFrame frame = new PotluckFrame(n);

        frame.setTitle("Potluck Game ");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.green);
        frame.setVisible(true);

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static java.awt.BorderLayout.*;
import static java.awt.GridBagConstraints.*;
import static javax.swing.text.StyleConstants.Size;

public class PotluckFrame extends JFrame {
    static int n;
    private JLabel outLabel;
    private JPanel buttonPanel;
    private JPanel gamePanel;
    private JPanel outPanel;
    private JButton[] buttons;
    static int trials = 1;

    public PotluckFrame(int n){
        this.n = n;
        this.setLayout( new BorderLayout());
        createComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.green);
        this.setVisible(true);
        this.pack();
    }

    public void createComponents() {
        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(150, 200));
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(150, 200));
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(this.getWidth(), 200));


        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout(10, 10));
        buttonPanel = new JPanel();
        outPanel = new JPanel();
        outLabel = new JLabel("0");
        outPanel.add(outLabel);

        //Adding panels to gamepanel
        gamePanel.add(outPanel, BorderLayout.NORTH);
        gamePanel.add(empty1, BorderLayout.WEST);
        gamePanel.add(empty2, BorderLayout.EAST);
        gamePanel.add(empty3, BorderLayout.SOUTH);

        outPanel.setBackground(Color.orange);
        empty1.setBackground(Color.blue);
        empty2.setBackground(Color.blue);
        empty3.setBackground(Color.blue);


        //ButtonPanel
        buttons = new JButton[n * n];
        buttonPanel.setLayout(new GridLayout(n, n, 5, 10));

        //Listeners
        ActionListener clickCount = new AllListener();

        //Creating buttons and adding the common listener
        for (int i = 0; i < n * n; i++) {
            buttons[i] = new JButton("Pot" + (i + 1));
            buttons[i].addActionListener(clickCount);
            buttonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
            buttonPanel.add(buttons[i]);
        }

        //Adding prize and bomb listeners
        ActionListener bomb = new bombListener();
        ActionListener prize = new prizeListener();

        int randomBomb1 = (int) Math.floor( ( Math.random() * Math.pow(n,2) ) + 1 );
        int randomBomb2 = (int) Math.floor( ( Math.random() * Math.pow(n,2) ) + 1 );
        int randomPrize = (int) Math.floor( ( Math.random() * Math.pow(n,2) ) + 1 );

        //randomising the buttons and making sure they are not in the same button
        while (randomBomb1 == randomBomb2 && randomBomb1 == randomPrize && randomBomb2 == randomPrize){
            randomBomb1 = (int) (Math.random() * n * n);
            randomBomb2 = (int) (Math.random() * n * n);
            randomPrize = (int) (Math.random() * n * n);
        }

        buttons[randomBomb1].addActionListener(bomb);
        buttons[randomBomb2].addActionListener(bomb);
        buttons[randomPrize].addActionListener(prize);

        //adding the buttonPanel to the main gamepanel
        gamePanel.add(buttonPanel, BorderLayout.CENTER);

        gamePanel.setBackground( Color.blue);
        //adding the gamePanel to the center of the frame
        this.add(gamePanel, BorderLayout.CENTER);
    }

    //Listener implementations.

    private class AllListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            trials++;
            JButton b = (JButton) e.getSource();
            b.setEnabled(false);
        }
    }

    private class bombListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            outLabel.setText( "Sorry! You are blown at attempt: " + trials);
            for(int i = 0; i < n * n; i++){
                buttons[i].setEnabled(false);
            }
        }
    }

    private class prizeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            outLabel.setText( "Well done! you found the prize in "+ trials + " tries");
            for(int i = 0; i < n * n; i++){
                buttons[i].setEnabled(false);
            }
        }
    }
}


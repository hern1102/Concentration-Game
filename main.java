/*
Description: The program below is a memory game that uses a JFrame, a JPanel with 
a grid layout, multideminsional arrays of integer type that are created and shuffled 
around using methods. Essentially, once you click on one box to check if a certain
number is there, depending on if you got it right or not, it will use a sleep method 
to delay the flipping of the numbers by 250 miliseconds. This program was pretty cool
because I learned what a try catch block was for the first time, and how to incorporate 
one at a very basic level. 
 */

package concentration;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;


public class Concentration extends JFrame implements ActionListener, MouseListener {
    
    JLabel[] lblBoard = new JLabel[16];
    
    int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
    int firstChoice = -1;
    int tries = 0;
    JPanel pnlBoard;
    JPanel pnlControls;
    JLabel lblFirst; 
    JButton btnGame = new JButton("New Game");
    JLabel lblTries = new JLabel("0");
    
    Container content = this.getContentPane();
    
    
    public Concentration(){
        
        pnlBoard = new JPanel();
        createLabels();
        content.add(pnlBoard);
        shuffle();
        pnlControls = new JPanel();
        pnlControls.setLayout(new FlowLayout());
        pnlControls.add(btnGame);
        
        JLabel numTries = new JLabel("Number of Tries: ");
        pnlControls.add(numTries, BorderLayout.EAST);
        pnlBoard.setLayout(new GridLayout(4,4,5,5));
        pnlControls.add(lblTries);
        content.add(pnlControls, BorderLayout.SOUTH);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        btnGame.addActionListener(this);
        
    }
    
    public void createLabels(){
        
        for(int i = 0; i < lblBoard.length; i++){
            
            lblBoard[i] = new JLabel("", JLabel.CENTER);
            lblBoard[i].setOpaque(true);
            lblBoard[i].setBackground(Color.MAGENTA);
            lblBoard[i].setForeground(Color.white);
            lblBoard[i].setFont(new Font("Helvetica", Font.BOLD,24));
            lblBoard[i].addMouseListener(this);
            lblBoard[i].setName("" + i);
            pnlBoard.add(lblBoard[i]);
            
        }
        
    }
    
    public void shuffle(){
        
        int num1;
        int num2;
        int temp;
        Random r = new Random();
        
        for(int i = 0; i < 500; i++){
            
            num1 = r.nextInt(nums.length);
            num2 = r.nextInt(nums.length);
            temp = nums[num1];
            nums[num1] = nums[num2];
            nums[num2] = temp;
              
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        shuffle();
        firstChoice = -1;
        
        for(int i = 0; i < lblBoard.length; i++){
            lblBoard[i].setText("");
        }
        tries = 0;
        lblTries.setText("" + tries); 
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        
        JLabel l = (JLabel) arg0.getSource();
        
        int theNumber = Integer.parseInt(l.getName());
        
        if(firstChoice == -1){
            
            l.setText("" + nums[theNumber]);
            lblFirst = l;
            firstChoice = theNumber;

        } else if(nums[theNumber] != nums[firstChoice]){
            
            l.setText("" + nums[theNumber]);
            pnlBoard.paintImmediately(0, 0, pnlBoard.getWidth(), pnlBoard.getHeight());
            
            try{
                Thread.sleep(250);
            } catch(InterruptedException e){
                
            }
            lblFirst.setText("");
            l.setText("");
            lblFirst = null;
            firstChoice = -1;
            tries++;
        } else {
            
            l.setText("" + nums[theNumber]);
            firstChoice = -1;
            tries++;
            
        }
        
        lblTries.setText("" + tries);
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    
    public static void main(String[] args) {
        
        Concentration GUI = new Concentration();
        
    }
    
}

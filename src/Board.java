import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * Class to initialize the board
 * @author Joshua Arrazola
  */
public class Board {
    private JFrame board = new JFrame();
    private JButton buttons[] = new JButton[9];
    private JPanel titleFrame = new JPanel();
    JLabel title = new JLabel("Tik Tak Toe");

    /**
     * Board constructor of GUI
     */
    public Board(){
        // Set board config
        board.setSize(1000, 800);
        board.setTitle("Tik Tak Toe Game");
        board.setLayout(new BorderLayout());
        
        // Configure title stuff
        title.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
        title.setForeground(Color.white);
        
        titleFrame.add(title);
        titleFrame.setBackground(Color.BLACK);

        // Configure grid
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,3, 3, 3));
        grid.setBackground(Color.black);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            grid.add(buttons[i]);
        }

        
        
        // Add stuff to board
        board.add(titleFrame, BorderLayout.NORTH);
        board.add(grid, BorderLayout.CENTER);

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(true);
        board.setLocationRelativeTo(null);
        board.setVisible(true);
    }

    /**
     * Return board 
     * @return Board
      */
    public JFrame getBoard() {
        return board;
    }

    /**
     * Returns Buttons Array
     * @return Buttons[]
      */
    public JButton[] getButtons() {
        return buttons;
    }

    public JLabel getTitle() {
        return title;
    }
}

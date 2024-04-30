import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Game implements ActionListener{
    private Board board = new Board();
    private boolean turnX = true;
    private boolean winner = false;
    private JButton buttons[][] = board.getButtons();
    private boolean validMove = false;
    private String playerToken;
    private boolean full = false;

    private String isInTurn(boolean turn){
        return "Turn of " + ((turn) ? "X" : "O");
    }

    private void setupButtons(){
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                buttons[i][j].addActionListener(this);
            
    }

    public Game(){
        setupButtons();
        
        // Set first player

        JLabel title = board.getTitle();
        
        while (!winner&&!full) {
            title.setText(isInTurn(turnX));
            playerToken = ((turnX) ? "X" : "O");
        }

        if(winner){
            JOptionPane.showMessageDialog(null, "The player " + playerToken + " has won!");
            board.closeBoard();
        }
    }

    private boolean makeMove(JButton btn){
        if(btn.getText().equals("")){
            // Put playerToken
            btn.setText(playerToken);

            // Color of playerToken
            if(turnX) btn.setForeground(Color.RED);
            else btn.setForeground(Color.blue);

            btn.setFont(new Font("SansSerif", Font.BOLD, 30));
            
            return true;
        } else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        validMove = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(e.getSource() == buttons[i][j]){
                    if(makeMove(buttons[i][j])){
                        validMove = true;
                        
                        if(validateWinner())
                            return;

                        verifyFullness();

                        turnX = ((turnX) ? false : true);
                        return;
                    }
                }
            }
        }
    }

    private boolean isInBoard(int i, int j){
        return i>=0&&j>=0&&i<3&&j<3;
    }

    private boolean validateWinner(){
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if(buttons[i][j].getText().equals(playerToken)){
                    if(isWinner(1, i, j, 1, 0)||
                    isWinner(1, i, j, 0, 1)||
                    isWinner(1, i, j, 1, 1)||
                    isWinner(1, i, j, 1, -1)){
                        winner = true;
                        return true;
                    }
                }
            
        return false;
    }

    private void verifyFullness(){
        int ctr = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!buttons[i][j].getText().equals(""))
                    ctr++;
            }
        }

        if(ctr == 9){
            full = true;
            JOptionPane.showMessageDialog(null, "The board is full");
            board.closeBoard();
        }
    }

    private boolean isWinner(int ctr, int i, int j, int dirR, int dirC){
        if(!isInBoard(i, j)||!buttons[i][j].getText().equals(playerToken))
            return false;

        if(ctr == 3)
            return true;
        
        return isWinner(++ctr, i + dirR, j + dirC, dirR, dirC);
    }
}

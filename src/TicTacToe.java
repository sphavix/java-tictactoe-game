import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; // 50px for the title bar indicating game turns

    JFrame frame = new JFrame("Tic-Tac-Toe");

    JLabel textJLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; // Set the dimension of the game.
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textJLabel.setBackground(Color.darkGray);
        textJLabel.setForeground(Color.white);
        textJLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textJLabel.setHorizontalAlignment(JLabel.CENTER);
        textJLabel.setText("Tic Tac Toe");
        textJLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textJLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Create a 3x3 grid layout for the board
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);
                
                tile.setBackground(Color.gray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return; //if the game is over, players are not allowed to change the state of the game.

                        JButton tile = (JButton)e.getSource();
                        if (tile.getText() == "")
                        {
                            tile.setText(currentPlayer);
                            checkWinner();

                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textJLabel.setText(currentPlayer + "'s turn!");
                            }
                        }
                    }
                });
            }
        }

    }

    void checkWinner(){
        // horizantal
        for(int row = 0; row < 3; row++){
            if(board[row][0].getText() == ""){
               continue;
            }

            if(board[row][0].getText() == board[row][1].getText() && board[row][1].getText() == board[row][2].getText()){
                gameOver = true;
                break;
            }
        }
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

            if(board[row][0].getText() == board[row][1].getText() && 
                board[row][1].getText() == board[row][2].getText()){
                for (int i = 0; i < 3; i++) {
                    setWinner(board[row][i]);
                }
                gameOver = true;
                break;
            }
        }

        // vertical
        for(int col = 0; col < 3; col++){
            if(board[0][col].getText() == ""){
                continue;
            }

            if(board[0][col].getText() == board[1][col].getText() &&
                board[1][col].getText() == board[2][col].getText()){
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][col]);
                }
                gameOver = true;
                break;
            }
        }

        // diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != ""){
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        // anti-diagonal
        if(board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != ""){
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][2-i]);
            }
            gameOver = true;
            return;
        }

        // check if there are any empty tiles left
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board[row][col].getText() == ""){
                    return;
                }
            }
        }
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textJLabel.setText(currentPlayer + " is the winner!");

    }
}

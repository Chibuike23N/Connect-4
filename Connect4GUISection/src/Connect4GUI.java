/** ASSIGNMENT #2
 * @Username: (Chibuike Nnolim)
 * @Student#: (7644941)
 * @Version: 1.0(08 04 24)
 *
 * This class contains the GUI version of the connect 4 game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.net.Socket;

public class Connect4GUI extends JFrame {
    private JButton[][] gameBoard;
    private JButton[] dropButtons;
    private char tempBoard[][];
    private Game connect4;
    PrintWriter writer;
    BufferedReader receiver;
    String incomingInput;
    int player;

    public Connect4GUI() {
        setTitle("Connect 4");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        connect4 = new Game();
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(7, 7);
        panel.setLayout(gridLayout);
        gameBoard = new JButton[6][7];
        dropButtons = new JButton[7];
        tempBoard = new char[6][7];



        for (int j = 0; j < gameBoard[0].length; j++)
        {
            int col = j;
            dropButtons[j] = new JButton("Drop piece");
            dropButtons[j].setBackground(Color.RED);
            dropButtons[j].setForeground(Color.yellow);
            dropButtons[j].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int row = connect4.placeCounter(col);
                    if (row != -1)
                    {

                        String f = String.valueOf(connect4.currentPlayer);
                        tempBoard[row][col] = f.charAt(0);
                        for (int i = 0; i < gameBoard.length; i++)
                        {
                            for (int j = 0; j < gameBoard[i].length; j++)
                            {
                                if(tempBoard[i][j] == '0')
                                {
                                    gameBoard[i][j].setBackground(Color.WHITE);
                                }
                                else if(tempBoard[i][j] == '1')
                                {
                                    gameBoard[i][j].setBackground(Color.YELLOW);
                                }
                                else if(tempBoard[i][j] == '2')
                                {
                                    gameBoard[i][j].setBackground(Color.BLUE);
                                }
                            }
                        }
                        writer.println(col);
                        if (connect4.winCon(row, col))
                        {
                            JOptionPane.showMessageDialog(null, "Game over. Player " + connect4.currentPlayer + " wins.");
                            System.exit(0);
                        }
                        else if (connect4.isBoardFull())
                        {
                            JOptionPane.showMessageDialog(null,"Game over. Game ended in a draw.");
                            System.exit(0);
                        }
                        else
                        {
                            connect4.switchPlayer();
                            for(int j = 0; j < gameBoard[0].length; j++)
                            {
                                dropButtons[j].setEnabled(false);
                            }
                        }
                    }
                }
            });
            panel.add(dropButtons[j]);
        }

        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
            {
                gameBoard[i][j] = new JButton();
                gameBoard[i][j].setEnabled(false);
                gameBoard[i][j].setBackground(Color.pink);
                panel.add(gameBoard[i][j]);
            }
        }
        add(panel);


        try
        {
            Socket client = new Socket("localhost", 1024);
            OutputStream o = client.getOutputStream();
            receiver = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(o,true);

            setVisible(true);
            for( ; ; )
            {
                incomingInput = receiver.readLine();
                System.out.println(incomingInput);
                if(incomingInput.contains("Choose"))
                {
                    connect4.currentPlayer = connect4.YELLOW;
                    for(int i = 0; i < dropButtons.length; i++)
                    {
                        dropButtons[i].setEnabled(true);
                    }
                }
                else
                {
                    connect4.currentPlayer = connect4.BLUE;
                    for(int j = 0; j < dropButtons.length; j++)
                    {
                        dropButtons[j].setEnabled(false);
                    }
                }
                if(incomingInput.equals(";"))
                {
                    for(int i = 0; i < gameBoard.length; i++)
                    {
                        tempBoard[i] = receiver.readLine().toCharArray();
                    }
                    for (int i = 0; i < gameBoard.length; i++)
                    {
                        for (int j = 0; j < gameBoard[i].length; j++)
                        {
                            if(tempBoard[i][j] == '0')
                            {
                                gameBoard[i][j].setBackground(Color.WHITE);
                            }
                            else if(tempBoard[i][j] == '1')
                            {
                                gameBoard[i][j].setBackground(Color.YELLOW);
                            }
                            else if(tempBoard[i][j] == '2')
                            {
                                gameBoard[i][j].setBackground(Color.BLUE);
                            }
                        }
                    }
                    connect4.switchPlayer();
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Exception");
        }
    }

    public void updateBoard(int row, int col)
    {
        if (connect4.currentPlayer == Game.YELLOW) gameBoard[row][col].setBackground(Color.YELLOW);
        else gameBoard[row][col].setBackground(Color.BLUE);
    }

    public static void main(String[] args)
    {
        Connect4GUI g = new Connect4GUI();
        g.setVisible(true);
    }
}



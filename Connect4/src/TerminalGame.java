/** ASSIGNMENT #2
 * @Username: (Chibuike Nnolim)
 * @Student#: (7644941)
 * @Version: 1.0(08 04 24)
 *
 * This class contains the game logic for the terminal client of connect 4.
 */

import java.io.PrintWriter;

public class TerminalGame {
    public int board[][] = new int[6][7];
    public TerminalGame()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = 0;
            }
        }
    }

    public void displayBoard(PrintWriter p1, PrintWriter p2)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(Connection.turn)
                {
                    p2.print("[ " + board[i][j] + " ]");
                    p1.print(board[i][j]);
                }
                else
                {
                    p1.print("[ " + board[i][j] + " ]");
                    p2.print(board[i][j]);
                }
            }
            p1.println("");
            p2.println("");
        }
    }

    public boolean placeCounter(int player, int col)
    {
        if(!(col > -1 && col < 7))return false;
        else
        {
            if((board[0][col] == 0))
            {
                for(int i = board.length - 1; i < board.length; i--)
                {
                    if(board[i][col] == 0)
                    {
                        if(player == 1)board[i][col] = 1;
                        else board[i][col] = 2;
                        break;
                    }
                }
                return true;
            }
            else
            {
                return false;
            }
        }
    }



    public void winCon(int player)
    {

    }

}

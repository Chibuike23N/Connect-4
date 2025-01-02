/** ASSIGNMENT #2
 * @Username: (Chibuike Nnolim)
 * @Student#: (7644941)
 * @Version: 1.0(08 04 24)
 *
 * This class is responsible for the game logic of the GUI version of the game.
 */

public class Game {
    public static final int YELLOW = 1;
    public static final int BLUE = 2;

    private int[][] gameBoard;
    public int currentPlayer;

    public Game() {
        gameBoard = new int[6][7];
        currentPlayer = YELLOW;
    }

    /**
     * Method placeCounter searches the passed column to see where the piece will be dropped.
     * @param col the chosen column
     * @return the row at which the piece will land
     */
    public int placeCounter(int col)
    {
        for (int i = gameBoard.length - 1; i >= 0; i--)
        {
            if (gameBoard[i][col] == 0)
            {
                gameBoard[i][col] = currentPlayer;
                return i;
            }
        }
        return -1;
    }

    /**
     * Method winCon checks the win condition for the piece that was just dropped.
     * @param row
     * @param col
     * @return true if game is over, false otherwise
     */
    public boolean winCon(int row, int col)
    {
        int player = gameBoard[row][col];

        // Check horizontally
        int count = 0;
        for (int j = 0; j < 7; j++)
        {
            if (gameBoard[row][j] == player)
            {
                count++;
                if (count >= 4) return true;
            }
            else
            {
                count = 0;
            }
        }

        // Check vertically
        count = 0;
        for (int i = 0; i < gameBoard.length; i++)
        {
            if (gameBoard[i][col] == player)
            {
                count++;
                if (count >= 4) return true;
            }
            else
            {
                count = 0;
            }
        }
        // Check diagonally
        count = 0;
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
            {
                if (gameBoard[i][j] == player)
                {
                    count++;
                    if (count >= 4) return true;
                }
                else
                {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Method checks if the board filled up with no winner, thus ending in a draw.
     * @return true if the board is full, false otherwise
     */
    public boolean isBoardFull() {
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
            {
                if (gameBoard[i][j] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method switches the current player
     */
    public void switchPlayer() {
        if(currentPlayer == BLUE)
        {
            currentPlayer = YELLOW;
        }
        else
        {
            currentPlayer = BLUE;
        }
    }
}
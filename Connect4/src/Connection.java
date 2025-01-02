/** ASSIGNMENT #2
 * @Username: (Chibuike Nnolim)
 * @Student#: (7644941)
 * @Version: 1.0(08 04 24)
 *
 * This class accepts a connection between 2 clients to start the game.
 */

import java.net.*;
import java.io.*;

public class Connection implements Runnable {
    private Socket client1;
    private Socket client2;
    public static boolean turn = true;
    public TerminalGame game = new TerminalGame();
    public Connection(Socket socket1, Socket socket2)
    {
        client1 = socket1;
        client2 = socket2;
    }


    public void run()
    {
        try(PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            )
        {
            for(;;)
            {
                if(turn)
                {
                    out1.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                    out2.println("<><><><<<><><><>><>><>>><>><<><><><<><><><><>><><>>");
                    out1.println("");
                    out2.println("");
                    out1.println(";");
                    game.displayBoard(out1,out2);
                    out1.println("");
                    out2.println("");
                    out1.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                    out2.println("<><><><<<><><><>><>><>>><>><<><><><<><><><><>><><>>");
                    out2.println("Player 1's Turn. Waiting for opponent to choose...");
                    for(;;)
                    {
                        try
                        {
                            out1.println("============================================");
                            out1.println("Player 1's Turn. Choose a column from 0 to 6");
                            String column = in1.readLine();
                            if(game.placeCounter(1, Integer.valueOf(column)) == false)
                            {
                                out1.println("Invalid entry, try again.");
                            }
                            else
                            {
                                out2.println(column);
                                turn = !turn;
                                game.winCon(1);
                                break;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            out1.println("Invalid entry, try again.");
                        }
                    }
                }
                else
                {
                    out1.println("<><>><><<><>><<>><><<>><><<><>><><<><><><>><><<><>");
                    out2.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                    out1.println("");
                    out2.println("");
                    out2.println(";");
                    game.displayBoard(out1,out2);
                    out1.println("");
                    out2.println("");
                    out1.println("<><>><><<><>><<>><><<>><><<><>><><<><><><>><><<><>");
                    out2.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                    out1.println("Player 2's Turn. Waiting for opponent to choose...");
                    for(;;)
                    {
                        try
                        {
                            out2.println("============================================");
                            out2.println("Player 2's Turn. Choose a column from 0 to 6");
                            String column = in2.readLine();
                            if(game.placeCounter(2, Integer.valueOf(column)) == false)
                            {
                                out2.println("Invalid entry, try again.");
                            }
                            else
                            {
                                out1.println(column);
                                turn = !turn;
                                game.winCon(2);
                                break;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            out2.println("Invalid entry, try again.");
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Exception ");
        }

    }
}

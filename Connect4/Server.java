/** ASSIGNMENT #2
 * @Username: (Chibuike Nnolim)
 * @Student#: (7644941)
 * @Version: 1.0(08 04 24)
 *
 * This class is responsible for starting the server for the players to connect to for the game.
 */

import java.net.*;
import java.io.*;

public class Server
{
    boolean turn;
    public int port = 1024;
    public Server()
    {
        System.out.println("Connect-4 Multiplayer by Chibuike Nnolim. Waiting for Players...");
        System.out.println("");
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            for(;;)
            {
                Socket player1 = serverSocket.accept();
                System.out.println("Player 1 connected. Waiting for Player 2...");
                Socket player2 = serverSocket.accept();
                System.out.println("Player 2 connected. Game starting.");
                Thread clientThr = new Thread(new Connection(player1, player2));
                clientThr.start();

            }
        } catch (IOException e) {
            System.out.println("Exception occurred when listening on port " + port + " or listening for a connection");
        }
    }


}

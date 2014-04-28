import entities.ComputerPlayer;
import entities.Game;
import entities.HumanPlayer;
import entities.Player;

import java.util.Scanner;

public class HumanGameRunner
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Number of computer players:");
        String playerCountString = scan.nextLine();
        int playerCount = Integer.parseInt(playerCountString);
        System.out.println("Blind:");
        String blindString = scan.nextLine();
        int blind = Integer.parseInt(blindString);
        Game game = new Game(blind);
        System.out.println("Players' money:");
        String gameMoneyString = scan.nextLine();
        int gameMoney = Integer.parseInt(gameMoneyString);
        Player human = new HumanPlayer(gameMoney);
        game.addPlayer(human);
        for (int i = 0; i < playerCount; i++)
        {
            double riskIndex = Math.random() / 2;
            double bluffIndex = Math.random() / 10;
            Player newPlayer = new ComputerPlayer(gameMoney, riskIndex, bluffIndex);
            game.addPlayer(newPlayer);
        }
        game.play();
    }
}

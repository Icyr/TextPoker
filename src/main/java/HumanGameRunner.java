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
        System.out.println("Your money:");
        String humanGameMoneyString = scan.nextLine();
        int humanGameMoney = Integer.parseInt(humanGameMoneyString);
        Player human = new HumanPlayer(humanGameMoney);
        game.addPlayer(human);
        for (int i = 0; i < playerCount; i++)
        {
            System.out.println("Money of player number " + (i + 1) + ":");
            String gameMoneyString = scan.nextLine();
            int gameMoney = Integer.parseInt(gameMoneyString);
            System.out.println("Risk index of player number " + (i + 1) + ":");
            String riskIndexString = scan.nextLine();
            double riskIndex = Double.parseDouble(riskIndexString);
            System.out.println("Bluff index of player number " + (i + 1) + ":");
            String bluffIndexString = scan.nextLine();
            double bluffIndex = Double.parseDouble(bluffIndexString);
            Player newPlayer = new ComputerPlayer(gameMoney, riskIndex, bluffIndex);
            game.addPlayer(newPlayer);
        }
        game.play();
    }
}

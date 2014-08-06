package entities.players;

import entities.players.ComputerPlayer;
import util.Utils;

public class ComputerPlayerFactory
{
    private static int playerCount = 1;

    public static ComputerPlayer createComputerPlayer(int money)
    {
        double generatedRaiseIndex = Utils.getRandomInt(0, 20) * 0.01;
        double generatedBluffIndex = Utils.getRandomInt(0, 10) * 0.01;
        ComputerPlayer res = new ComputerPlayer(money, generatedRaiseIndex, generatedBluffIndex, 10000);
        res.setId(Integer.toString(playerCount++) + generatedRaiseIndex + generatedBluffIndex);
        return res;
    }
}

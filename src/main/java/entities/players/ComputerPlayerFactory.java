package entities.players;

import util.Utils;

public class ComputerPlayerFactory
{
    public static ComputerPlayer createRandomComputerPlayer(int money, int number)
    {
        double generatedRaiseIndex = Utils.getRandomInt(0, 20) * 0.01;
        double generatedBluffIndex = Utils.getRandomInt(0, 10) * 0.01;
        ComputerPlayer res = new ComputerPlayer(money, generatedRaiseIndex, generatedBluffIndex, 10000);
        res.setId("r:" + generatedRaiseIndex + " b:" + generatedBluffIndex + "#" + number + 1);
        return res;
    }
}

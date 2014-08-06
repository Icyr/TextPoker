package service;

import entities.players.ComputerPlayer;
import entities.players.ComputerPlayerFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComputerPlayerFactoryTest
{
    @Test
    public void createComputerPlayer()
    {
        ComputerPlayer computerPlayer = ComputerPlayerFactory.createComputerPlayer(1000);
        assertThat(computerPlayer.getCash(), equalTo(1000));
    }
}

import entities.Card;
import entities.Dealer;
import entities.Hand;
import entities.Table;
import entities.players.HumanPlayer;
import entities.players.Player;
import gui.IntroWindow;
import gui.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameTest
{
    //todo need more tests
    //@Test
    public void mainWindow()
    {
        GameView graphicalInterface = new GameView(7);
        graphicalInterface.initialize();
        List<Player> players = new ArrayList<Player>();
        Player player = new HumanPlayer(1000, graphicalInterface);
        List<Card> hand = new ArrayList<Card>(2);
        hand.add(new Card("H", 10));
        hand.add(new Card("C", 11));
        List<Card>table = new ArrayList<Card>(5);
        table.add(new Card("H", 2));
        table.add(new Card("H", 3));
        table.add(new Card("H", 4));
        table.add(new Card("H", 5));
        table.add(new Card("H", 6));
        Table table1 = new Table(new Dealer());
        table1.setCards(table);
        player.setHand(new Hand(hand));
        players.add(player);
        graphicalInterface.deal(players);
        graphicalInterface.updateTable(table1);
        graphicalInterface.zeroBets();
        //while (true) ;
    }

    //@Test
    public void introWindow()
    {
        IntroWindow introWindow = new IntroWindow();
        //while (true);
    }
}

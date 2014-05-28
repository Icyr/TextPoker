package logic;

import entities.Card;
import entities.combinations.Combination;
import entities.players.Player;

import java.util.List;

public class PlayersCardsAndCombination
{
    private Player player;
    private List<Card> cards;
    private Combination combination;

    public PlayersCardsAndCombination(Player player, List<Card> cards, Combination combination)
    {
        this.player = player;
        this.cards = cards;
        this.combination = combination;
    }

    public Player getPlayer()
    {
        return player;
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public Combination getCombination()
    {
        return combination;
    }
}

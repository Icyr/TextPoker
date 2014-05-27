package logic;

import entities.Card;
import entities.combinations.Combination;
import entities.players.Player;

import java.util.List;

public class PlayersCardsAndCombination
{
    public Player player;
    public List<Card> cards;
    public Combination combination;

    public PlayersCardsAndCombination(Player player, List<Card> cards, Combination combination)
    {
        this.player = player;
        this.cards = cards;
        this.combination = combination;
    }
}

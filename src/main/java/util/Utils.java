package util;

import entities.players.Player;
import entities.Card;

import java.util.ArrayList;
import java.util.List;

public class Utils
{
    //todo test it
    public static List<List<Card>> unitePlayersAndTableCards(List<Player> players, List<Card> cardsOnTable)
    {
        List<List<Card>> playersCards = new ArrayList<List<Card>>();
        for (Player player : players)
        {
            List<Card> temp = new ArrayList<Card>();
            temp.addAll(cardsOnTable);
            temp.addAll(player.getHand().getCards());
            temp = Utils.sortCards(temp);
            playersCards.add(temp);
        }
        return playersCards;
    }

    public static List<Card> sortCards(List<Card> cards)
    {
        List<Card> res = new ArrayList<Card>(cards.size());
        for (int i = 14; i > 1; i--)
        {
            for (Card card : cards)
            {
                if (card.getNominal() == i)
                {
                    res.add(card);
                }
            }
        }
        return res;
    }

    public static int getSameColorCount(String color, List<Card> cards)
    {
        int res = 0;
        for (Card card : cards)
        {
            if (card.getColor().equals(color)) res++;
        }
        return res;
    }

    public static boolean listContainsNominal(int nominal, List<Card> cards)
    {
        for (Card card : cards)
        {
            if (card.getNominal() == nominal) return true;
        }
        return false;
    }

    public static List<Card> getCardsWithPreferredColor(String color, List<Card> cards)
    {
        List<Card> res = new ArrayList<Card>();
        for (Card card : cards)
        {
            if (card.getColor().equals(color))
            {
                res.add(card);
            }
        }
        return res;
    }

    public static List<Card> removeCardsWithPreferredNominal(int nominal, List<Card> cards)
    {
        List<Card> res = new ArrayList<Card>();
        for (Card card : cards)
        {
            if (card.getNominal() != nominal)
            {
                res.add(card);
            }
        }
        return res;
    }

    public static List<Player> getPlayersByCards(List<List<Card>> playersCards, List<Player> players)
    {
        List<Player> res = new ArrayList<Player>();
        for (Player player : players)
        {
            for (List<Card> cards : playersCards)
            {
                if (cards.contains(player.getHand().getCards().get(0)) || cards.contains(player.getHand().getCards().get(1)))
                {
                    res.add(player);
                }
            }
        }
        //these cards were on table, so we return all players
        if (res.size() == 0) return players;
        return res;
    }

    public static Player getPlayerByCard(Card card, List<Player> players)
    {
        for (Player player : players)
        {
            if (player.getHand().getCards().contains(card))
            {
                return player;
            }
        }
        return null;
    }
}

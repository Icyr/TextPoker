package util;

import entities.Card;
import entities.players.Player;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils
{
    public static List<Card> getPlayersCards(Player player, List<Card> tableCards)
    {
        List<Card> playersCards = new ArrayList<Card>();
        playersCards.addAll(player.getHand().getCards());
        playersCards.addAll(tableCards);
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

    public static int getRandomInt(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Image getImage(final String pathAndFileName)
    {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    public static int safeParseInt(String string)
    {
        int res;
        try
        {
            res = Integer.parseInt(string);
        } catch (NumberFormatException e)
        {
            return 0;
        }
        return res;
    }
}

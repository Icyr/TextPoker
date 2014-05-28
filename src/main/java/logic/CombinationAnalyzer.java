package logic;

import entities.Card;
import entities.combinations.*;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CombinationAnalyzer
{
    private static String[] colors = {"H", "S", "C", "D"};

    public static Combination analyzeCombination(List<Card> cards)
    {
        cards = Utils.sortCards(cards);
        Combination cardsCombination = searchForStraightFlush(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForQuads(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForFullHouse(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForFlush(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForStraight(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForSet(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForTwoPairs(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = searchForOnePair(cards);
        if (cardsCombination != null)
        {
            return cardsCombination;
        }
        cardsCombination = getKicker(cards);
        return cardsCombination;
    }

    private static StraightFlush searchForStraightFlush(List<Card> cards)
    {
        String[] colors = {"H", "S", "C", "D"};
        for (String color : colors)
        {
            if (Utils.getSameColorCount(color, cards) > 4)
            {
                List<Card> cardsWithSameColor = Utils.getCardsWithPreferredColor(color, cards);
                Straight straight = searchForStraight(cardsWithSameColor);
                if (straight != null)
                    return new StraightFlush(straight.getNominal());
            }
        }
        return null;
    }

    private static Quads searchForQuads(List<Card> cards)
    {
        for (int index = 0; index < cards.size() - 3; index++)
        {
            Card card = cards.get(index);
            if (card.getNominal() == cards.get(index + 1).getNominal() &&
                    card.getNominal() == cards.get(index + 2).getNominal() &&
                    card.getNominal() == cards.get(index + 3).getNominal())
            {
                return new Quads(card.getNominal());
            }
        }
        return null;
    }

    private static FullHouse searchForFullHouse(List<Card> cards)
    {
        Set set = searchForSet(cards);
        if (set != null)
        {
            List<Card> withoutSetNominal = Utils.removeCardsWithPreferredNominal(set.getNominal(), cards);
            Pair pair = searchForOnePair(withoutSetNominal);
            if (pair != null)
                return new FullHouse(set.getNominal(), pair.getNominal());
        }
        return null;
    }

    private static Flush searchForFlush(List<Card> cards)
    {
        for (String color : colors)
        {
            if (Utils.getSameColorCount(color, cards) > 4)
            {
                for (Card card : cards)
                {
                    if (card.getColor().equals(color)) return new Flush(card.getNominal(), color);
                }
            }
        }
        return null;
    }

    private static Straight searchForStraight(List<Card> cards)
    {
        for (Card card : cards)
        {
            int currNom = card.getNominal();
            if (Utils.listContainsNominal(currNom - 1, cards) &&
                    Utils.listContainsNominal(currNom - 2, cards) &&
                    Utils.listContainsNominal(currNom - 3, cards) &&
                    Utils.listContainsNominal(currNom - 4, cards))
            {
                return new Straight(currNom - 4);
            }
        }
        //looking for ace-first straight
        if (cards.get(0).getNominal() == 14)
        {
            if (Utils.listContainsNominal(2, cards) &&
                    Utils.listContainsNominal(3, cards) &&
                    Utils.listContainsNominal(4, cards) &&
                    Utils.listContainsNominal(5, cards))
            {
                return new Straight(1);
            }
        }
        return null;
    }

    private static Set searchForSet(List<Card> cards)
    {
        for (int index = 0; index < cards.size() - 2; index++)
        {
            Card card = cards.get(index);
            if (card.getNominal() == cards.get(index + 1).getNominal() &&
                    card.getNominal() == cards.get(index + 2).getNominal())
            {
                return new Set(card.getNominal());
            }
        }
        return null;
    }

    private static TwoPairs searchForTwoPairs(List<Card> cards)
    {
        int[] pairs = getAllPairs(cards);
        if (pairs.length > 1)
        {
            return new TwoPairs(pairs[0], pairs[1]);
        }
        return null;
    }

    private static Pair searchForOnePair(List<Card> cards)
    {
        int[] pairs = getAllPairs(cards);
        if (pairs.length > 0)
        {
            return new Pair(pairs[0]);
        }
        return null;
    }

    private static Kicker getKicker(List<Card> cards)
    {
        return new Kicker(cards.get(0).getNominal());
    }

    public static Combination getHighestCombination(List<PlayersCardsAndCombination> playersCardsAndCombinations)
    {
        Combination highestCombination = new Combination()
        {
            @Override
            public int getPower()
            {
                return 0;
            }
        };
        for (PlayersCardsAndCombination playersCardsAndCombination : playersCardsAndCombinations)
        {
            if (playersCardsAndCombination.getCombination().getPower() > highestCombination.getPower())
                highestCombination = playersCardsAndCombination.getCombination();
        }
        return highestCombination;
    }

    private static int[] getAllPairs(List<Card> cards)
    {
        List<Card> pairList = new ArrayList<Card>();
        for (Card card : cards)
        {
            if (cards.indexOf(card) != cards.size() - 1)
            {
                if (card.getNominal() == cards.get(cards.indexOf(card) + 1).getNominal())
                {
                    if (!Utils.listContainsNominal(card.getNominal(), pairList)) pairList.add(card);
                }
            }
        }
        int[] res = new int[pairList.size()];
        for (int i = 0; i < pairList.size(); i++)
        {
            res[i] = pairList.get(i).getNominal();
        }
        return res;
    }
}

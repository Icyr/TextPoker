package logic;

import entities.Card;
import entities.combinations.*;
import util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CombinationAnalyzer
{
    public static Combination analyzeCombination(List<Card> cards)
    {
        //todo maybe move this logic into GameManager
        cards = Utils.sortCards(cards);
        if (isRoyalFlush(cards) != -1)
        {
            return new StraightFlush(10);
        }
        int result = isStraightFlush(cards);
        if (result != -1)
        {
            return new StraightFlush(result);
        }
        result = isQuads(cards);
        if (result != -1)
        {
            return new Quads(result);
        }
        int[] fullHouse = isFullHouse(cards);
        if (fullHouse[0] != -1)
        {
            return new FullHouse(fullHouse[0], fullHouse[1]);
        }
        result = isFlush(cards);
        if (result != -1)
        {
            return new Flush(result);
        }
        result = isStraight(cards);
        if (result != -1)
        {
            return new Straight(result);
        }
        int[] sets = getAllSets(cards);
        if (sets.length > 0)
        {
            result = sets[0];
            return new Set(result);
        }
        int pairs[] = getAllPairs(cards);
        if (pairs.length > 1)
        {
            return new TwoPairs(pairs[0], pairs[1]);
        }
        if (pairs.length == 1)
        {
            return new Pair(pairs[0]);
        }
        return new Kicker(getKicker(cards));
    }

    private static int getKicker(List<Card> cards)
    {
        return cards.get(0).getNominal();
    }

    public static int[] getAllPairs(List<Card> cards)
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

    private static int[] getAllSets(List<Card> cards)
    {
        //we get all of the sets, cause we use it in full house search
        List<Card> setList = new ArrayList<Card>();
        for (Card card : cards)
        {
            if (cards.indexOf(card) < cards.size() - 2)
            {
                if (card.getNominal() == cards.get(cards.indexOf(card) + 1).getNominal() &&
                        card.getNominal() == cards.get(cards.indexOf(card) + 2).getNominal())
                {
                    if (!Utils.listContainsNominal(card.getNominal(), setList)) setList.add(card);

                }
            }
        }
        int[] res = new int[setList.size()];
        for (int i = 0; i < setList.size(); i++)
        {
            res[i] = setList.get(i).getNominal();
        }
        return res;
    }

    private static int isStraight(List<Card> cards)
    {
        for (Card card : cards)
        {
            int currNom = card.getNominal();
            if (Utils.listContainsNominal(currNom - 1, cards) &&
                    Utils.listContainsNominal(currNom - 2, cards) &&
                    Utils.listContainsNominal(currNom - 3, cards) &&
                    Utils.listContainsNominal(currNom - 4, cards))
            {
                return currNom - 4;
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
                return 1;
            }
        }
        return -1;
    }

    private static int isFlush(List<Card> cards)
    {
        if (Utils.getSameColorCount("H", cards) > 4)
        {
            for (Card card : cards)
            {
                if (card.getColor().equals("H")) return card.getNominal();
            }
        }
        if (Utils.getSameColorCount("C", cards) > 4)
        {
            for (Card card : cards)
            {
                if (card.getColor().equals("C")) return card.getNominal();
            }
        }
        if (Utils.getSameColorCount("S", cards) > 4)
        {
            for (Card card : cards)
            {
                if (card.getColor().equals("S")) return card.getNominal();
            }
        }
        if (Utils.getSameColorCount("D", cards) > 4)
        {
            for (Card card : cards)
            {
                if (card.getColor().equals("D")) return card.getNominal();
            }
        }
        return -1;
    }

    private static int[] isFullHouse(List<Card> cards)
    {
        int sets[] = getAllSets(cards);
        if (sets.length > 0)
        {
            int pairs[] = getAllPairs(cards);
            if (sets.length == 1)
            {
                if (pairs.length == 1)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                }
                if (pairs.length == 2)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                    if (sets[0] != pairs[1]) return new int[]{sets[0], pairs[1]};
                }
                if (pairs.length == 3)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                    if (sets[0] != pairs[1]) return new int[]{sets[0], pairs[1]};
                    if (sets[0] != pairs[2]) return new int[]{sets[0], pairs[2]};
                }
            }
            if (sets.length == 2)
            {
                if (pairs.length == 1)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                    if (sets[1] != pairs[0]) return new int[]{sets[1], pairs[0]};
                }
                if (pairs.length == 2)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                    if (sets[0] != pairs[1]) return new int[]{sets[0], pairs[1]};
                    if (sets[1] != pairs[0]) return new int[]{sets[1], pairs[0]};
                    if (sets[1] != pairs[1]) return new int[]{sets[1], pairs[1]};
                }
                if (pairs.length == 3)
                {
                    if (sets[0] != pairs[0]) return new int[]{sets[0], pairs[0]};
                    if (sets[0] != pairs[1]) return new int[]{sets[0], pairs[1]};
                    if (sets[0] != pairs[2]) return new int[]{sets[0], pairs[2]};
                    if (sets[1] != pairs[0]) return new int[]{sets[1], pairs[0]};
                    if (sets[1] != pairs[1]) return new int[]{sets[1], pairs[1]};
                    if (sets[1] != pairs[2]) return new int[]{sets[1], pairs[2]};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int isQuads(List<Card> cards)
    {
        for (Card card : cards)
        {
            if (cards.indexOf(card) < cards.size() - 3)
            {
                if (card.getNominal() == cards.get(cards.indexOf(card) + 1).getNominal() &&
                        card.getNominal() == cards.get(cards.indexOf(card) + 2).getNominal() &&
                        card.getNominal() == cards.get(cards.indexOf(card) + 3).getNominal())
                {
                    return card.getNominal();
                }
            }
        }
        return -1;
    }

    private static int isStraightFlush(List<Card> cards)
    {
        if (Utils.getSameColorCount("H", cards) > 4)
        {
            List<Card> temp = Utils.getCardsWithPreferredColor("H", cards);
            return isStraight(temp);
        }
        if (Utils.getSameColorCount("S", cards) > 4)
        {
            List<Card> temp = Utils.getCardsWithPreferredColor("S", cards);
            return isStraight(temp);
        }
        if (Utils.getSameColorCount("C", cards) > 4)
        {
            List<Card> temp = Utils.getCardsWithPreferredColor("C", cards);
            return isStraight(temp);
        }
        if (Utils.getSameColorCount("D", cards) > 4)
        {
            List<Card> temp = Utils.getCardsWithPreferredColor("D", cards);
            return isStraight(temp);
        }
        return -1;
    }

    private static int isRoyalFlush(List<Card> cards)
    {
        if (Utils.getSameColorCount("H", cards) > 4)
        {
            if (cards.contains(new Card("H", 10)) &&
                    cards.contains(new Card("H", 11)) &&
                    cards.contains(new Card("H", 12)) &&
                    cards.contains(new Card("H", 13)) &&
                    cards.contains(new Card("H", 14)))
            {
                return 14;
            }
        }
        if (Utils.getSameColorCount("S", cards) > 4)
        {
            if (cards.contains(new Card("S", 10)) &&
                    cards.contains(new Card("S", 11)) &&
                    cards.contains(new Card("S", 12)) &&
                    cards.contains(new Card("S", 13)) &&
                    cards.contains(new Card("S", 14)))
            {
                return 14;
            }
        }
        if (Utils.getSameColorCount("D", cards) > 4)
        {
            if (cards.contains(new Card("D", 10)) &&
                    cards.contains(new Card("D", 11)) &&
                    cards.contains(new Card("D", 12)) &&
                    cards.contains(new Card("D", 13)) &&
                    cards.contains(new Card("D", 14)))
            {
                return 14;
            }
        }
        if (Utils.getSameColorCount("C", cards) > 4)
        {
            if (cards.contains(new Card("C", 10)) &&
                    cards.contains(new Card("C", 11)) &&
                    cards.contains(new Card("C", 12)) &&
                    cards.contains(new Card("C", 13)) &&
                    cards.contains(new Card("C", 14)))
            {
                return 14;
            }
        }

        return -1;
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
            if (playersCardsAndCombination.combination.getPower() > highestCombination.getPower())
                highestCombination = playersCardsAndCombination.combination;
        }
        return highestCombination;
    }
}
